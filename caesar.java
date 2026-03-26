import java.util.*;

public class caesar {

    // Method to encrypt text
    public static String encrypt(String text, int shift) {
        String result = "";
        //will store the final encrytped message

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            //caesar cipher only changes letters, numbers and spaces stay the same
            if (Character.isLetter(ch)) {

                if (Character.isUpperCase(ch)) { 
                    //c is the number from 0 to 25
                    char c = (char) ((ch - 'A' + shift) % 26 + 'A');
                    result += c;
                }
                else {
                    char c = (char) ((ch - 'a' + shift) % 26 + 'a');
                    result += c;
                }

            } else {
                result += ch; //if character is space, number or symbol, we dont change it
            }
        }

        return result;
    }

    // Method to decrypt text
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(message, shift);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}