package synchronizedBlock;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketManager {
    private final AtomicInteger availableTickets;

    public TicketManager(int initialTickets) {
        this.availableTickets = new AtomicInteger(initialTickets);
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }

    public void bookTickets(int requested, String userName) {
        while (true) {
            int current = availableTickets.get();

            if (requested > current) {
                System.out.println(userName + " requested " + requested +
                        " tickets → Failed (Not enough tickets)");
                return;
            }

            int updated = current - requested;
            if (availableTickets.compareAndSet(current, updated)) {
                System.out.println(userName + " requested " + requested +
                        " tickets → Booked → Remaining: " + updated);
                return;
            }
        }
    }
}
