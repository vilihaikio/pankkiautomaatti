package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public String readString(String question) {
        System.out.print(question + " : ");
        String input = scanner.nextLine();
        return input;
    }

    public long readLong(String question) {

        long inputLong = 0;
        while (true) {
            System.out.print(question + " : ");
            try {
                inputLong = scanner.nextLong();
                scanner.nextLine();
                break;
            } catch (InputMismatchException mismatchException) {
                System.out.println("Must be a number.");
            }
            scanner.nextLine();

        }
        return inputLong;
    }
}
