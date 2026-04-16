import java.util.Scanner;

public class SHA512 {

    // Convert character to 8-bit binary
    public static String charToBinary(char c) {
        String binary = Integer.toBinaryString(c);//Converts character ASCII value into binary.
        while (binary.length() < 8) { //Loop until binary becomes 8 bits.
            binary = "0" + binary;
        }
        return binary;
    }

    // Right Rotate function
    public static long rotr(long x, int n) {
        return (x >>> n) | (x << (64 - n));
    }

    // sigma0 function
    public static long sigma0(long x) {
        return rotr(x, 1) ^ rotr(x, 8) ^ (x >>> 7);
    }

    // sigma1 function
    public static long sigma1(long x) {
        return rotr(x, 19) ^ rotr(x, 61) ^ (x >>> 6);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Input message
        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        // Convert text to binary
        String binary = "";

        for (int i = 0; i < text.length(); i++) {
            binary += charToBinary(text.charAt(i));
        }

        System.out.println("Binary Message:");
        System.out.println(binary);

        int originalLength = binary.length();

        // Add 1 bit
        binary += "1";

        // Add zeros till length becomes 896 mod 1024
        while (binary.length() % 1024 != 896) {
            binary += "0";
        }

        // Append original length in 128-bit binary
        String lengthBinary = Integer.toBinaryString(originalLength);

        while (lengthBinary.length() < 128) {
            lengthBinary = "0" + lengthBinary;
        }

        binary += lengthBinary;

        System.out.println("\nPadded Message:");
        System.out.println(binary);

        // Create 80 words
        long[] words = new long[80];

        // First 16 words from padded message
        for (int i = 0; i < 16; i++) {
            String part = binary.substring(i * 64, (i + 1) * 64);
            words[i] = Long.parseLong(part, 2);
        }

        // Remaining words
        for (int i = 16; i < 80; i++) {
            words[i] = sigma1(words[i - 2]) + words[i - 7]
                    + sigma0(words[i - 15]) + words[i - 16];
        }

        // Print first few words
        System.out.println("\nFirst 20 Words:");

        for (int i = 0; i < 18; i++) {
            System.out.printf("W[%d] = %X\n", i, words[i]);
        }

        // Majority Function 
        int A = 5, B = 9, C = 12;
        int majority = (A & B) ^ (A & C) ^ (B & C);
        String bin_maj = Integer.toBinaryString(majority);
        System.out.println("\nMajority = " + bin_maj);

        // Condition / Choice Function Demo
        int condition = (A & B) ^ (~A & C);
        System.out.println("Condition = " + condition);

        sc.close();
    }
}