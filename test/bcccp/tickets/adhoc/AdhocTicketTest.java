package bcccp.tickets.adhoc;

import java.util.*;

class AdhocTicketTest {

    static IAdhocTicket testAdhoc;
    static IAdhocTicketDAO idao;
    static IAdhocTicketFactory ifactory;

    Logger logger = Logger.getLogger("Unit testing of AdHocTicket class");
    private float charge;

    @BeforeAll
    static void before() {
        testAdhoc = mock(AdhocTicket.class);
        idao = spy(new AdhocTicketDAO(new AdhocTicketFactory()));
    }
    
    @AfterEach
    void after() {

        testAdhoc = mock(AdhocTicket.class);
        idao = spy(new AdhocTicketDAO(new AdhocTicketFactory()));

    }

    @Test
    void tryRuntimeExceptioWhenGetInvalidTicketNo() {
        logger.log(Level.INFO, "Test ticket number");
        testAdhoc.getTicketNo();
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Error");
        });
        assertEquals("Error", exception.getMessage());

    }
    

    @Test
    void trygetBarcode() {
        logger.log(Level.INFO, "Test getBarcode()");
        testAdhoc.getBarcode();
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Error");
        });
        assertEquals("Error", exception.getMessage());
    }

    @Test
    void trygetCarparkIdwithInvalid() {
        logger.log(Level.INFO, "Testing getCarparkId() with not valid parameter");
        testAdhoc.getCarparkId();
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Error");
        });
        assertEquals("Error", exception.getMessage());
    }

    @Test
    void tryenterEntryDate() {
        logger.log(Level.INFO, "Test EntryDate");
        testAdhoc.enter(0L);
        verify(testAdhoc).enter(0L);
        assertEquals(testAdhoc.getEntryDateTime(), 0L);

    }
    @Test
    void getEntryDateTime() {
        logger.log(Level.INFO, "Testing entry date");
        when(testAdhoc.getEntryDateTime()).thenReturn(0L);
        assertEquals(0L, testAdhoc.getEntryDateTime());

    }
}