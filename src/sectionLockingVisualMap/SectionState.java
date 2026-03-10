package sectionLockingVisualMap;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class SectionState {
    public final char[] seats;
    public int remaining;
    public final ReentrantLock lock = new ReentrantLock();

    public SectionState(int capacity) {
        this.seats = new char[capacity];
        Arrays.fill(seats, '.');
        this.remaining = capacity;
    }
}
