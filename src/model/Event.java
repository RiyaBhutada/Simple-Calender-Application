
package model;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String title, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean overlaps(Event other) {
        return start.isBefore(other.getEnd()) && end.isAfter(other.getStart());
    }

    @Override
    public String toString() {
        return "[" + title + "] from " + start + " to " + end;
    }
}
