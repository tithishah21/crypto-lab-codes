import java.util.*;

public class vigenere {
    public static String encrypt(String text, String key) {
        String result = "";

        for(int i = 0; i < text.length(); i++) {

            char p = text.charAt(i); // plaintext character
            char k = key.charAt(i % key.length()); // repeat key automatically

            char c = (char)((p - 'A' + k - 'A') % 26 + 'A');

            result += c; // append encrypted character
        }

        return result;
    }

    public static String decrypt(String cipher, String key) {
        String result = "";

        for(int i = 0; i < cipher.length(); i++) {

            char c = cipher.charAt(i);
            char k = key.charAt(i % key.length());

            char p = (char)((c-'A' - (k-'A') + 26) % 26 + 'A');

            result += p;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter key: ");
        String key = sc.nextLine().toUpperCase();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}