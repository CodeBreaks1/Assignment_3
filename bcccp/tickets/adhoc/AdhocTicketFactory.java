package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
    public IAdhocTicket make(String carparkId, int ticketNo) {

        return new AdhocTicket(carparkId, ticketNo, generateBarCode(ticketNo, entryDate()));
    }

	public String entryDate() {

        // Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");

        return formatter.format(new Date().getTime()); // the string that is encoded (to a bar code)
    }

}
