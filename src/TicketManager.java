public class TicketManager {
    private int availableTickets;

    public TicketManager(int initialTickets) {
        this.availableTickets = initialTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    // Attempts to book tickets for a user. Uses synchronized block to ensure thread safety.
    public void bookTickets(int requested, String userName) {
        synchronized (this) {
            if (requested <= availableTickets) {
                availableTickets -= requested;
                System.out.println(userName + " requested " + requested + " tickets → Booked → Remaining: "
                        + availableTickets);
            } else {
                System.out.printf(userName + " requested " + requested + " tickets → Failed (Not enough tickets)");
            }
        }
    }
}
