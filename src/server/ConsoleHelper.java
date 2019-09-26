package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String line = null;
        while (line == null) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                writeMessage("An error occurred during entering text. Try again.");
            }
        }
        return line;
    }

    public static int readInt() {
        int number = 0;
        while (true) {
            try {
                number = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                writeMessage("An error occurred during entering a number. Try again.");
            }
        }
        return number;
    }
}

