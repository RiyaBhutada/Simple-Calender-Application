
package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
