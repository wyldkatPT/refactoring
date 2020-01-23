package com.celfocus.training.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public final class DateUtils {

    public static String DATE_FORMAT_PATTERN = "d/MM/yyyy";
    private DateUtils() {}


    public static DateTimeFormatter getSimpleDateFormat(){
        return DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    }

    public static LocalDate toDate(String date, DateTimeFormatter format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);

        return LocalDate.parse(date, format);
    }

    public static String toString(LocalDate date, DateTimeFormatter format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);
        return date.format(format);
    }

    public static LocalDate getNow() {
        return LocalDate.now();
    }
}
