package com.Vishvash.library.exception;

public class ExceptionHandler {

    public static void handleException(Exception e) {
        if (e instanceof BookNotFoundException) {
            System.out.println("❌ Error: " + e.getMessage());
        } else if (e instanceof DatabaseException) {
            System.out.println("❌ Database Error: " + e.getMessage());
        } else if (e instanceof InvalidInputException) {
            System.out.println("❌ Invalid Input: " + e.getMessage());
        } else {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
