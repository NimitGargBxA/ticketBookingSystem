package sectionLockingVisualMap;

import java.util.concurrent.ThreadLocalRandom;

// Represents a user attempting to book tickets.
public record BookingTask(TicketManager ticketManager, String userName) implements Runnable {
    @Override
    public void run() {
        Section[] sections = Section.values();
        Section randomSection = sections[ThreadLocalRandom.current().nextInt(0, sections.length)];

        int requested = ThreadLocalRandom.current().nextInt(TicketConstants.MIN_REQUEST,
                TicketConstants.MAX_REQUEST + 1
        );

        ticketManager.book(userName, randomSection, requested);
    }
}