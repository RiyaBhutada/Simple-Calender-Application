
package service;

import model.Event;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarManager {
    private List<Event> events = new ArrayList<>();

    public boolean addEvent(Event newEvent) {
        for (Event event : events) {
            if (event.overlaps(newEvent)) {
                return false;
            }
        }
        events.add(newEvent);
        return true;
    }

    public List<Event> getEventsForDay(LocalDate date) {
        return events.stream()
                .filter(e -> e.getStart().toLocalDate().equals(date))
                .sorted(Comparator.comparing(Event::getStart))
                .collect(Collectors.toList());
    }

    public List<Event> getRemainingEvents(LocalDate date, LocalTime now) {
        return events.stream()
                .filter(e -> e.getStart().toLocalDate().equals(date) && e.getStart().toLocalTime().isAfter(now))
                .sorted(Comparator.comparing(Event::getStart))
                .collect(Collectors.toList());
    }

    public Optional<LocalTime> getNextAvailableSlot(LocalDate date, int slotMinutes) {
        List<Event> dayEvents = getEventsForDay(date);
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(18, 0);

        for (Event e : dayEvents) {
            LocalTime eventStart = e.getStart().toLocalTime();
            if (Duration.between(start, eventStart).toMinutes() >= slotMinutes) {
                return Optional.of(start);
            }
            start = e.getEnd().toLocalTime();
        }

        if (Duration.between(start, end).toMinutes() >= slotMinutes) {
            return Optional.of(start);
        }

        return Optional.empty();
    }
}
