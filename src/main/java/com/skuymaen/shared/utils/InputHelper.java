package com.skuymaen.shared.utils;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);
    public static String inputString(boolean allowEmpty) {
        String input = "";
        boolean inputValid;
        do {
            try {
                input = scanner.nextLine();
                if (!allowEmpty && input.equals("")) {
                    System.out.println("Invalid Input");
                    inputValid = false;
                    StringHelper.printInputPrompt("");
                    continue;
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
                StringHelper.printInputPrompt("");
            }
        } while (!inputValid);

        return input;
    }

    public static Integer inputInt() {
        int input = -1;
        boolean inputValid;
        do {
            try {
                String inputString = scanner.nextLine();
                if (!inputString.equals("")) {
                    input = Integer.parseInt(inputString);
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
                StringHelper.printInputPrompt("");
            }
        } while (!inputValid);

        return input;
    }

    public static Integer inputInt(int min, int max) {
        int input = -1;
        boolean inputValid;
        do {
            try {
                String inputString = scanner.nextLine();
                if (!inputString.equals("")) {
                    input = Integer.parseInt(inputString);
                }
                if (input < min || input > max) {
                    System.out.println("Please input value between " + min + " and " + max);
                    inputValid = false;
                    StringHelper.printInputPrompt("");
                    continue;
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
                StringHelper.printInputPrompt("");
            }
        } while (!inputValid);

        return input;
    }

    public static Float inputFloat(Scanner scanner) {
        float input = -1f;
        boolean inputValid;
        do {
            try {
                String inputString = scanner.nextLine();
                if (!inputString.equals("")) {
                    input = Float.parseFloat(inputString);
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
            }
        } while (!inputValid);

        return input;
    }

    public static Date inputDate() {
        Date input = null;
        boolean inputValid;
        do {
            try {
                String inputString = scanner.nextLine();
                if (!inputString.equals("")) {
                    input = java.sql.Date.valueOf(inputString);
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
                StringHelper.printInputPrompt("");
            }
        } while (!inputValid);

        return input;
    }

    public static Time inputTime() {
        Time input = null;
        boolean inputValid;
        do {
            try {
                String inputString = scanner.nextLine();
                if (!inputString.equals("")) {
                    input = java.sql.Time.valueOf(inputString);
                }
                inputValid = true;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                inputValid = false;
                StringHelper.printInputPrompt("");
            }
        } while (!inputValid);

        return input;
    }

    public static Boolean confirmation(String message) {
        StringHelper.printInputPrompt(message + " (y/n)");
        String restartInput;
        do {
            restartInput = InputHelper.inputString(false).toLowerCase();
            switch (restartInput) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid Input");
                    StringHelper.printInputPrompt("");
                    break;
            }
        } while (true);
    }
}
