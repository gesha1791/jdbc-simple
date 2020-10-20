package chaplinskiy.jdbccrud.util;

import java.sql.Date;
import java.time.LocalDateTime;

public class Converters {
    public static LocalDateTime convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

}
