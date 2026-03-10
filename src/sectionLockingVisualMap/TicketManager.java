package sectionLockingVisualMap;

import java.util.EnumMap;
import java.util.Map;

public class TicketManager {
    private final Map<Section, SectionState> sections = new EnumMap<>(Section.class);

    public TicketManager() {
        for (Section sec : Section.values()) {
            sections.put(sec, new SectionState(sec.capacity));
        }
    }

    public void book(String user, Section section, int requested) {
        SectionState state = sections.get(section);
        state.lock.lock();

        try {
            if (state.remaining <= 0) {
                System.out.printf("%s requested %d tickets in %s → Failed (Sold Out)%n",
                        user, requested, section);

                return;
            }

            if (state.remaining < requested) {
                System.out.printf("%s requested %d tickets in %s → Failed (Not enough tickets)%n",
                        user, requested, section);
                return;
            }

            int filled = 0;
            for (int i = 0; i < state.seats.length && filled < requested; i++) {
                if (state.seats[i] == '.') {
                    state.seats[i] = 'X';
                    filled++;
                }
            }

            state.remaining -= requested;
            System.out.printf("%s requested %d tickets in %s → Booked → Remaining: %d%n", user, requested,
                    section, state.remaining);

//            renderVenue("Milestone Update");

        } finally {
            state.lock.unlock();
        }
    }

    private void renderVenue(String label) {
        System.out.println("\n--- VENUE MAP (" + label + ") ---");
        for (Section s : Section.values()) {
            SectionState state = sections.get(s);
            String map = String.valueOf(state.seats);
            System.out.printf("%s%s: [%s]%s%n", s.color, s.name(), map, TicketConstants.RESET);
        }
        System.out.println();
    }

    public void printFinalReport() {
        renderVenue("Final State");
        System.out.println("================ FINAL SUMMARY ================");
        for (Section s : Section.values()) {
            SectionState state = sections.get(s);
            int sold = s.capacity - state.remaining;
            String status = (state.remaining == 0) ? " (SOLD OUT)" : "";
            System.out.println(s.name() + " Section: " + sold + "/" + s.capacity + " sold" + status + ".");
        }
        System.out.println("===============================================");
    }
}