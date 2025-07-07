import model.Event;
import service.CalendarManager;
import util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalendarManager manager = new CalendarManager();

        while (true) {
            System.out.println("\nMy Calendar:");
            System.out.println("*************************************");
            System.out.println("1. Add Event");
            System.out.println("2. List Today's Scheduled Events");
            System.out.println("3. List Remaining Events Today");
            System.out.println("4. List Events for Specific Date");
            System.out.println("5. Find Next Free Slot Today");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Start (yyyy-MM-dd HH:mm): ");
                    String startStr = scanner.nextLine();
                    System.out.print("End (yyyy-MM-dd HH:mm): ");
                    String endStr = scanner.nextLine();

                    Event event = new Event(title, DateTimeUtil.parse(startStr), DateTimeUtil.parse(endStr));
                    if (manager.addEvent(event)) {
                        System.out.println("Event added.");
                    } else {
                        System.out.println("Event overlaps with existing one!");
                    }
                }

                case 2 -> {
                    LocalDate today = LocalDate.now();
                    List<Event> events = manager.getEventsForDay(today);
                    events.forEach(System.out::println);
                }

                case 3 -> {
                    LocalDate today = LocalDate.now();
                    LocalTime now = LocalTime.now();
                    List<Event> events = manager.getRemainingEvents(today, now);
                    if(!events.isEmpty())
                        events.forEach(System.out::println);
                    else
                        System.out.println("\nYou have nothing scheduled for the day!");
                }

                case 4 -> {
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    LocalDate queryDate = LocalDate.parse(date);
                    List<Event> events = manager.getEventsForDay(queryDate);
                    events.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.print("Enter duration in minutes: ");
                    int mins = scanner.nextInt();
                    LocalDate today = LocalDate.now();
                    Optional<LocalTime> slot = manager.getNextAvailableSlot(today, mins);
                    slot.ifPresentOrElse(
                            s -> System.out.println("Next slot at: " + s),
                            () -> System.out.println("No available slot.")
                    );
                }

                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}
