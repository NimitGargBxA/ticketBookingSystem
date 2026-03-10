package sectionLockingVisualMap;

public enum Section {
    VIP(20, "\u001B[35m"),
    GOLD(30, "\u001B[33m"),
    SILVER(50, "\u001B[37m");

    public final int capacity;
    public final String color;

    Section(int capacity, String color) {
        this.capacity = capacity;
        this.color = color;
    }
}