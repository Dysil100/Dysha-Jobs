package duo.cmr.dysha.boundedContexts.generalhelpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String dateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

    static public LocalDateTime stringToDate(String stringDatetime){
        return LocalDateTime.parse(stringDatetime, formatter);
    }
}
