package synchronizedBlock;

import java.util.concurrent.ThreadLocalRandom;

// Represents a user attempting to book tickets.
public record BookingTask(TicketManager inventory, String userName) implements Runnable {
    @Override
    public void run() {
        int requested = ThreadLocalRandom.current()
                .nextInt(TicketConstants.MIN_REQUEST, TicketConstants.MAX_REQUEST + 1);
        inventory.bookTickets(requested, userName);
    }
}
