package com.celfocus.training.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class StringUtils {

    public static String listToString(List list, String delimiter){
        if(list.isEmpty()){
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (i < list.size() - 1) {
            stringBuilder.append(list.get(i));
            stringBuilder.append(delimiter);
            i++;
        }

        return stringBuilder.append("{").append(list.get(i)).append("}").toString();
    }

}
