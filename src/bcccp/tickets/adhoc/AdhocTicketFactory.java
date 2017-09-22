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

    public String generateBarCode(int ticketNum, String entryDate) {

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
    public static String toHexadecimal(String text) throws UnsupportedEncodingException {
        byte[] myBytes = text.getBytes("UTF-16");

        return DatatypeConverter.printHexBinary(myBytes);
    }

    // Fixed some codes before final test

}
