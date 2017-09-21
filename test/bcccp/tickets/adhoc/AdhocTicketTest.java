package bcccp.tickets.adhoc;

import java.util.*;

class AdhocTicketTest {

    static IAdhocTicket testAdhoc;
    static IAdhocTicketDAO idao;
    static IAdhocTicketFactory ifactory;

    Logger logger = Logger.getLogger("Unit testing for AdHocTicket class");
    private float charge;

    @BeforeAll
    static void before() {
        testAdhoc = mock(AdhocTicket.class);
        idao = spy(new AdhocTicketDAO(new AdhocTicketFactory()));
    }

}