package com.sample;

import java.io.*;

public class EncryptDecrypt {

    public static void main(String[] args) {
        // Get the file path from the user
        String filePath = getUserInput("Enter the file path: ");

        // Get the encryption key from the user
        int key = Integer.parseInt(getUserInput("Enter the encryption key: "));

        // Encrypt the file
        encryptFile(filePath, key);
        System.out.println("File encrypted successfully.");

        // Decrypt the file
        int decryptionKey = Integer.parseInt(getUserInput("Enter the decryption key: "));
        decryptFile(filePath, decryptionKey);
        System.out.println("File decrypted successfully.");
    }

    private static void encryptFile(String filePath, int key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_" + filePath))) {

            int ch;
            while ((ch = reader.read()) != -1) {
                char encryptedChar = encrypt((char) ch, key);
                writer.write(encryptedChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(String filePath, int key) {
        try (BufferedReader reader = new BufferedReader(new FileReader("encrypted_" + filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted_" + filePath))) {

            int ch;
            while ((ch = reader.read()) != -1) {
                char decryptedChar = decrypt((char) ch, key);
                writer.write(decryptedChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char encrypt(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base + key) % 26 + base);
        }
        return ch;
    }

    private static char decrypt(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch;
    }

    private static String getUserInput(String prompt) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
