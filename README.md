# 🗓️ Simple Calendar Application

A simple command-line calendar and appointment manager built in Java.
This application allows users to add events, list events, and find the next available time slot. All is stored in memory.

---

## ✅ Features

- Add events with title, start time, and end time
- Prevent overlapping/conflicting events
- List all events for **today** or any **specific date**
- Show only **remaining** events for today
- Find the **next free time slot** of a given duration for today
- In-memory data storage

---

## 🧠 Design and Approach

- `Event.java`: Represents a calendar event (title, start, end)
- `CalendarManager.java`: Business logic for storing and managing events
- `DateTimeUtil.java`: Handles date/time formatting and parsing
- `Main.java`: CLI interface for user input/output
- All data is stored in-memory in a `List<Event>` within `CalendarManager`

---

## 🗂️ Folder Structure

```
SimpleCalendarApp/
├── src/
│   ├── model/
│   │   └── Event.java
│   ├── service/
│   │   └── CalendarManager.java
│   ├── util/
│   │   └── DateTimeUtil.java
│   └── Main.java
├── test/
│   └── CalendarManagerTest.java
├── lib/               # For JUnit .jar files
├── README.md          # This file
```

---

## ▶️ How to Compile and Run

### Manual Compilation (No IDE)

```
Pre-installation:
JDK - https://www.oracle.com/java/technologies/downloads/
JRE - https://www.java.com/en/
VSCode (Optional)
```

1. Open terminal in project root

2. **Compile:**
```bash
javac -d output src\model\Event.java src\service\CalendarManager.java src\util\DateTimeUtil.java src\Main.java
```

3. **Run:**
```bash
java -cp output Main
```
---

## 🧪 Sample Usage

```
Calendar Menu:
1. Add Event
2. List Events Today
3. List Remaining Events Today
4. List Events for Specific Date
5. Find Next Free Slot Today
6. Exit

Enter choice: 1
Title: Appointment with Recruiter
Start (yyyy-MM-dd HH:mm): 2025-07-07 14:00
End (yyyy-MM-dd HH:mm): 2025-07-07 14:30
Event added.
```

---

## 🧠 Input Format

| Input Type | Format |
|------------|--------|
| Date + Time | `yyyy-MM-dd HH:mm` |

Example:  
```
2025-07-04 14:00
```

Invalid:  
```
2025-07004 15:30
```

---

## 🔧 Optional Enhancements

- Save and load events from file (JSON or CSV)
- GUI
- Weekly/monthly calendar views

---

## 👨‍💻 Tools

- Built using Java 24+
- IDE used: Visual Studio Code (VS Code)
