import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    // Final Variables
    static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    static final String DIGITS = "0123456789";
    static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS;
    static final int MIN_LENGTH = 10;
    static final int MAX_LENGTH = 16;
    static final String FILE_NAME = "passwords.txt";
    static final Random RANDOM = new Random();

    // Main Method
    public static void main(String[] args) {
        // Intro
        System.out.println("\n****** PASSWORD GENERATOR ******\nHello, Welcome To My Program.");
        System.out.println("The Program Will Generate 3 Sets of 20 Passwords And Save Each Set To The File: 'passwords.txt'.");
        System.out.println("Each Password Has At Least 1 Uppercase, Lowercase And Digit With No Same Consecutive Characters.");
        while (true) {
            // User Interface
            System.out.println("---------------------------\nWould You Like To Continue?\n'Y' - Run Program.\n'N' - Exit Program.");
            Scanner input = new Scanner(System.in);
            String user_input = input.nextLine();

            // Run The Program
            if (user_input.equalsIgnoreCase("yes") || (user_input.equalsIgnoreCase("y"))) {
                // Run 3 Times
                for (int i = 1; i <= 3; i++) {
                    ArrayList<String> passwords_list = new ArrayList<>();
                    // Generate 20 passwords
                    for (int n = 0; n < 20; n++) {
                        passwords_list.add(String.valueOf(genPassword()));
                    }
                    // Save To File
                    appendPasswordsToFile(passwords_list);
                    System.out.println("-----------------------------------------------------");
                    System.out.println("\n***** Set Of 20 Passwords Added To File *****\n");
                }
            } else if (user_input.equalsIgnoreCase("no") || (user_input.equalsIgnoreCase("n"))) {
                System.out.println("Thank You For Using My Program, Have A Good Day.");
                break;

            } else {
                System.out.println("Please Choose Either 'Y' To Run The Program Or 'N' To Exit.");
            }
        }

    }

    // Password Generator
    public static StringBuilder genPassword() {
        while (true) {
            int length = RANDOM.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
            StringBuilder password = new StringBuilder();

            // Generate Password
            while (password.length() < length) {
                char nextChar = ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length()));

                if (password.isEmpty() || password.charAt(password.length() - 1) != nextChar) {
                    password.append(nextChar);
                }
            }
            // Check if password is valid
            if (noConsecutiveSimilarChars(password) && isValidPassword(password)) {
                // Print Output
                System.out.println("-----------------------------------------------------");
                System.out.println(password);
                System.out.println("Length: " + length);
                System.out.println("The Password Is Valid: " + isValidPassword(password));
                System.out.println("The Password Has No Same Consecutive Characters: " + noConsecutiveSimilarChars(password));

                return password;
            }
        }
    }

    // Password Check 1: Consecutive Character Check.
    static boolean noConsecutiveSimilarChars (StringBuilder password) {
        boolean passedCheck1 = true;
        for (int i = 1; i < password.length(); i++) {
            if( Character.toLowerCase(password.charAt(i)) == Character.toLowerCase(password.charAt(i-1))){
                passedCheck1 = false;
                break;
            }
        }
        return passedCheck1;
    }

    // Password Check 2: Upper, Lower, Digit Check.
    static boolean isValidPassword(StringBuilder password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean passedCheck2 = false;

        for (int i = 0 ; i < password.length() ; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }
            else if (Character.isLowerCase(ch)) {
                hasLower = true;
            }
            else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (hasDigit && hasLower && hasUpper) {
                passedCheck2 = true;
                break;
            }
        }
        return passedCheck2;
    }

    // Password Appender
    static void appendPasswordsToFile(ArrayList<String> passwords) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            for (String password : passwords) {
                writer.write(password + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage()); // Exception handling
        }
    }
}