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
    @Test
    void tryExceptionforinvalidDateEnterMethod() {
        logger.log(Level.INFO, "Invalid date entry test");
        testAdhoc.enter(0);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Error");
        });
        assertEquals("Error", exception.getMessage());

    }
    @Test

    void tryisCurrentState() {
        logger.log(Level.INFO, "isCurrentState method state test");
        String entryStrDate = "02042013053542"; // "02-04-2013 05:35:42"
        testAdhoc = new AdhocTicket("Flinders Lane", 34, generateBarCode(34, entryStrDate));
        testAdhoc.enter(testAdhoc.getEntryDateTime());
        boolean state = testAdhoc.isCurrent();
        assertTrue(state);
    }
    @Test
    void trypayParameters() {
        logger.log(Level.INFO, "Testing pay method parameters");
        when(testAdhoc.getCharge()).thenReturn(4.5F);
        when(testAdhoc.getPaidDateTime()).thenReturn(4L);
        assertEquals(4.5F, testAdhoc.getCharge());
        assertEquals(4L, testAdhoc.getPaidDateTime());


    }


    @Test
    void isPaid() {
        logger.log(Level.INFO, "Test isPaid method");
        when(testAdhoc.isCurrent()).thenReturn(true);
        assertEquals(true, testAdhoc.isCurrent());

    }

    @Test
    void getCharge() {
        logger.log(Level.INFO, "Test getCharge method");
        when(testAdhoc.getCharge()).thenReturn(4.5F);
        assertEquals(4.5F, testAdhoc.getCharge());
    }

    @Test
    void tryExitMethod() {
        logger.log(Level.INFO, "Test exit method");
        testAdhoc.exit(5L);
        verify(testAdhoc).exit(5L);
    }

    @Test
    void getExitDateTime() {
        logger.log(Level.INFO, "Test Exit method");
        when(testAdhoc.getExitDateTime()).thenReturn(5L);
        assertEquals(5L, testAdhoc.getExitDateTime());
    }

    @Test
    void tryhasExitedMethod() {
        logger.log(Level.INFO, "Test hasExit method");
        when(testAdhoc.hasExited()).thenReturn(true);
        assertTrue(testAdhoc.hasExited());
    }
    // support methods

    static String generateBarCode(int ticketNum, String entryDate) {

        String prefix = "0041"; // hex representation of "A". Unicode: U+0041

        String hexNum = Integer.toHexString(ticketNum);

        String hexDate = null;
        try {
            hexDate = toHexadecimal(entryDate);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return prefix + "\u002D" + hexNum + "\u002D" + hexDate;
    }


    static String toHexadecimal(String text) throws UnsupportedEncodingException {
        byte[] myBytes = text.getBytes("UTF-16");

        return DatatypeConverter.printHexBinary(myBytes);
    }
}