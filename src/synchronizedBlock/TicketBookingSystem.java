package synchronizedBlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    static void main() {
        long startTime = System.currentTimeMillis();

        TicketManager ticketManager = new TicketManager(TicketConstants.INITIAL_TICKETS);
        ExecutorService executor = Executors.newFixedThreadPool(TicketConstants.THREAD_POOL_SIZE);

        for (int i = 1; i <= TicketConstants.TOTAL_USERS; i++) {
            executor.execute(new BookingTask(ticketManager, "User-" + i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution interrupted.");
        }

        long endTime = System.currentTimeMillis();

        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("----------------------------------");
        System.out.println("Initial Tickets: " + TicketConstants.INITIAL_TICKETS);
        System.out.println("Total Tickets Sold: " + (TicketConstants.INITIAL_TICKETS -
                ticketManager.getAvailableTickets()));
        System.out.println("Remaining Tickets: " + ticketManager.getAvailableTickets());
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }
}
