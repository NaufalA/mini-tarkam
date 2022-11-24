package com.skuymaen.shared.utils;

public class StringHelper {
    public static void printHeader(String title) {
        printSeparator("=", 100);
        System.out.println(title);
        printSeparator("-", 100);
    }

    public static void printInputPrompt(String prompt) {
        if (!prompt.equals("")) {
            System.out.print(prompt + "\n> ");
        } else {
            System.out.print("> ");
        }
    }

    public static void printSeparator(String separatorString, Integer size) {
        String output = "";
        for (int i = 0; i < size; i++) {
            output = output.concat(separatorString);
        }

        System.out.println(output);
    }
}
