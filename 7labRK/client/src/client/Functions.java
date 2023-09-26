package client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Functions {
    public static String hashPasswordMD2(String password) {

        try {
            // Create an MD2 message digest instance
            MessageDigest md2Digest = MessageDigest.getInstance("MD2");

            // Convert the password string to bytes
            byte[] passwordBytes = password.getBytes();

            // Update the message digest with the password bytes
            md2Digest.update(passwordBytes);

            // Get the hash bytes
            byte[] hashBytes = md2Digest.digest();

            // Convert the hash bytes to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexString.append(String.format("%02x", hashByte));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
}
