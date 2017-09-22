package bcccp.tickets.adhoc;
import org.junit.jupiter.api*;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdhocTicketFactoryTest {

    static AdhocTicketFactory adhocTicketFactory;
    static IAdhocTicket iadhocTicket;
    static AdhocTicketDAO adhocTicketDAO;

    Logger logger = Logger.getLogger("Unit testing of AdhocTicketFactory class");

    @BeforeAll
    static void before(){
        adhocTicketFactory = mock(AdhocTicketFactory.class);
        iadhocTicket = mock(AdhocTicket.class);
    }

    @Test
    void testBarcodeGeneation() {
        logger.log(Level.INFO,"barcode generation test");
        AdhocTicketFactory ad = new AdhocTicketFactory();
        IAdhocTicket ticket = ad.make("123",123);
        assertEquals(ad.generateBarCode(123,ad.entryDate()),ticket.getBarcode());
    }


    @Test
    void testMakeMethod(){
        logger.log(Level.INFO,"make method test");
        when(adhocTicketFactory.make("123",123)).thenReturn(iadhocTicket);
        adhocTicketFactory.make("123",123);
        verify(adhocTicketFactory).make("123",123);
    }

}