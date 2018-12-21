package p01Weekdays;

import p01Weekdays.Weekday;

import java.util.Comparator;

public class WeeklyEntry{
    public static final Comparator<WeeklyEntry> WEEKLY_ENTRY_COMPARATOR = getComparator();

    private static Comparator<WeeklyEntry> getComparator() {
        return (d1, d2)-> Integer.compare(d1.getWeekday().ordinal(), d2.getWeekday().ordinal());
    }

    private Weekday weekday;
    private String notes;

    public WeeklyEntry(String weekday, String notes) {
        this.weekday = Enum.valueOf(Weekday.class, weekday.toUpperCase());
        this.setNotes(notes);
    }


    private void setNotes(String notes) {
        this.notes = notes;
    }

    public Weekday getWeekday() {
        return this.weekday;
    }

    public String getNotes() {
        return this.notes;
    }

    @Override
    public String toString() {
        return this.weekday + " - " + this.getNotes();
    }

}
