package sectionLockingVisualMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        TicketManager ticketManager = new TicketManager();
        ExecutorService executor = Executors.newFixedThreadPool(TicketConstants.THREAD_POOL_SIZE);

        for (int i = 1; i <= TicketConstants.TOTAL_USERS; i++) {
            executor.execute(new BookingTask(ticketManager, "User-" + i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ticketManager.printFinalReport();
        System.out.println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
