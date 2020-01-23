package com.celfocus.training.logging;

public class ConsoleLogger {

    public static final String ERROR_LABEL = "ERROR: ";

    public ConsoleLogger() {
    }

    public void writeInfo(String message){
        System.out.println(message);
    }

    public void writeError(String message){
        System.err.println(ERROR_LABEL + message);
    }
}
