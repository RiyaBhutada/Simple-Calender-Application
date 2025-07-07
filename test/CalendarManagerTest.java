package test;
import org.junit.*;
import model.Event;
import service.CalendarManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class CalendarManagerTest {

    CalendarManager manager = new CalendarManager();

    @Test
    public void testAddEventSuccess() {
        Event event = new Event("Meeting",
                LocalDateTime.of(2025, 7, 4, 10, 0),
                LocalDateTime.of(2025, 7, 4, 11, 0));
        Assert.assertTrue(manager.addEvent(event));
    }

    @Test
    public void testAddOverlappingEventFails() {
        Event e1 = new Event("Meeting1", LocalDateTime.of(2025, 7, 4, 10, 0), LocalDateTime.of(2025, 7, 4, 11, 0));
        Event e2 = new Event("Meeting2", LocalDateTime.of(2025, 7, 4, 10, 30), LocalDateTime.of(2025, 7, 4, 11, 30));
        manager.addEvent(e1);
        Assert.assertFalse(manager.addEvent(e2));
    }

    @Test
    public void testGetEventsForDay() {
        Event e = new Event("Test Event", LocalDateTime.of(2025, 7, 4, 12, 0), LocalDateTime.of(2025, 7, 4, 13, 0));
        manager.addEvent(e);
        List<Event> result = manager.getEventsForDay(LocalDate.of(2025, 7, 4));
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Test Event", result.get(0).getTitle());
    }

    @Test
    public void testGetNextAvailableSlot() {
        Event e = new Event("Early Meeting", LocalDateTime.of(2025, 7, 4, 8, 0), LocalDateTime.of(2025, 7, 4, 9, 0));
        manager.addEvent(e);
        Optional<LocalTime> slot = manager.getNextAvailableSlot(LocalDate.of(2025, 7, 4), 30);
        Assert.assertTrue(slot.isPresent());
        Assert.assertEquals(LocalTime.of(9, 0), slot.get());
    }
}
