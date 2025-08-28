package course2;

// fight ticket booking example
public class L6_StaticDemo {
    // Instance variables (unique for each ticket)
    public int flightNumber;
    public int seatNumber;
    public String ticketCategory;
    public int ticketID;

    // Static variable (common for all tickets)
    public static int availableSeats = 3; // Initially only 3 seats for demo

    // Constructor to initialize ticket details
    public L6_StaticDemo(int flightNumber, int seatNumber, String ticketCategory, int ticketID) {
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.ticketCategory = ticketCategory;
        this.ticketID = ticketID;
    }

    // Static method to create ticket safely
    public static void createTicket(int flightNumber, int seatNumber, String category, int ticketID) {
        if (availableSeats > 0) {
            L6_StaticDemo ticket = new L6_StaticDemo(flightNumber, seatNumber, category, ticketID);
            availableSeats--; // Decrement seat count
            System.out.println("Ticket Created → Flight: " + ticket.flightNumber +
                    ", Seat: " + ticket.seatNumber +
                    ", Category: " + ticket.ticketCategory +
                    ", TicketID: " + ticket.ticketID);
            System.out.println("Remaining Seats: " + availableSeats);
        } else {
            System.out.println("❌ Sorry, Ticket not available.");
        }
    }

    // Static blocks
    static {
        System.out.println("⚡ Static Block 1 executed");
    }

    static {
        System.out.println("⚡ Static Block 2 executed");
    }

    // Static nested class
    public static class TicketHelper {
        public static void printWelcome() {
            System.out.println("Welcome to Flight Booking System (Static Nested Class)");
        }
    }

    // Main method
    public static void main(String[] args) {
        // Accessing static nested class
        TicketHelper.printWelcome();

        // Creating tickets
        L6_StaticDemo.createTicket(1122, 1, "Economy", 12345);
        L6_StaticDemo.createTicket(1122, 2, "Business", 12346);
        L6_StaticDemo.createTicket(1122, 3, "Business", 12347);

        // Trying to book when no seats left
        L6_StaticDemo.createTicket(1122, 4, "Economy", 12348);

        // Accessing static variable directly via class name
        System.out.println("Final Seats Left: " + L6_StaticDemo.availableSeats);

        // Accessing instance variable (requires object)
        L6_StaticDemo sample = new L6_StaticDemo(1122, 10, "Economy", 99999);
        System.out.println("Sample Ticket Seat: " + sample.seatNumber);
    }
}
