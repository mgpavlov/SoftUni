package p01Weekdays;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeeklyCalendar {
    List<WeeklyEntry> entries;

    public WeeklyCalendar() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(String weekday, String notes) {
        this.entries.add(new WeeklyEntry(weekday, notes));
    }

    public Iterable<WeeklyEntry> getWeeklySchedule() {
        Collections.sort(this.entries, WeeklyEntry.WEEKLY_ENTRY_COMPARATOR);
        return this.entries;
    }
}
