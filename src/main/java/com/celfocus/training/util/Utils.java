package com.celfocus.training.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public final class Utils {

    public static Date toDate(String date, DateFormat format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}