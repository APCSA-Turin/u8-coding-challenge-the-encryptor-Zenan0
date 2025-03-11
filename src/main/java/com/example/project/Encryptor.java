package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int columns = 1;
        while (messageLen > rows * columns) {
            columns++;
        }
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encryptArray = new String[rows][determineColumns(message.length(), rows)];
        int idx = 0;
        for (int i = 0; i < encryptArray.length; i++) {
            for (int j = 0; j < encryptArray[0].length; j++) {
                if (idx < message.length()) {
                    encryptArray[i][j] = message.substring(idx, idx + 1);
                    idx++;
                } else {
                    encryptArray[i][j] = "=";
                }
            }
        }
        return encryptArray;
    }

    public static String encryptMessage(String message, int rows){
        String[][] encryptArray = generateEncryptArray(message, rows);
        String encryptedMessage = "";
        for (int i = encryptArray[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < encryptArray.length; j++) {
                encryptedMessage += encryptArray[j][i];
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] decryptedArray = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        int idx = 0;
        for (int i = decryptedArray[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < decryptedArray.length; j++) {
                if (idx < encryptedMessage.length()) {
                    decryptedArray[j][i] = encryptedMessage.substring(idx, idx + 1);
                    idx++;
                }
            }
        }
        String originalMessage = "";
        for (int i = 0; i < decryptedArray.length; i++) {
            for (int j = 0; j < decryptedArray[0].length; j++) {
                if (decryptedArray[i][j] != null && !decryptedArray[i][j].equals("=")) {
                    originalMessage += decryptedArray[i][j];
                }
            }
        }
        return originalMessage;
    }
}