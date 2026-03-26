import java.util.*;

public class PlayfairCipher {

    // 5x5 matrix used for Playfair cipher
    static char[][] matrix = new char[5][5];

    // Function to generate the 5x5 key matrix
    static void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.replace("J", "I"); // Playfair combines I and J

        boolean[] used = new boolean[26];
        int index = 0;

        // Fill matrix with characters from key
        for(int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if(!used[c - 'A']) {
                matrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        // Fill remaining letters of alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // skip J
            if (!used[c - 'A']) {
                matrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }
    }

    // Function to find position of character in matrix
    static int[] findPosition(char c) {
        if (c == 'J') c = 'I';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Prepare plaintext into digraphs
    static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        text = text.replace("J", "I");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);

            if (i + 1 < text.length()) {
                char b = text.charAt(i + 1);

                // If letters are same, insert X
                if (a == b) {
                    result.append(a).append('X');
                } else {
                    result.append(a).append(b);
                    i++;
                }
            } else {
                result.append(a).append('X');
            }
        }

        return result.toString();
    }

    // Encryption function
    static String encrypt(String text) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            int r1 = posA[0], c1 = posA[1];
            int r2 = posB[0], c2 = posB[1];

            // Rule 1: Same row
            if (r1 == r2) {
                cipher.append(matrix[r1][(c1 + 1) % 5]);
                cipher.append(matrix[r2][(c2 + 1) % 5]);
            }
            // Rule 2: Same column
            else if (c1 == c2) {
                cipher.append(matrix[(r1 + 1) % 5][c1]);
                cipher.append(matrix[(r2 + 1) % 5][c2]);
            }
            // Rule 3: Rectangle
            else {
                cipher.append(matrix[r1][c2]);
                cipher.append(matrix[r2][c1]);
            }
        }

        return cipher.toString();
    }

    // Decryption function
    static String decrypt(String text) {
        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            int r1 = posA[0], c1 = posA[1];
            int r2 = posB[0], c2 = posB[1];

            // Rule 1: Same row
            if (r1 == r2) {
                plain.append(matrix[r1][(c1 + 4) % 5]);
                plain.append(matrix[r2][(c2 + 4) % 5]);
            }
            // Rule 2: Same column
            else if (c1 == c2) {
                plain.append(matrix[(r1 + 4) % 5][c1]);
                plain.append(matrix[(r2 + 4) % 5][c2]);
            }
            // Rule 3: Rectangle
            else {
                plain.append(matrix[r1][c2]);
                plain.append(matrix[r2][c1]);
            }
        }

        return plain.toString();
    }

    // Main function
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        // Generate Playfair matrix
        generateMatrix(key);

        System.out.println("\nPlayfair Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nEnter plaintext: ");
        String text = sc.nextLine();

        String prepared = prepareText(text);
        System.out.println("Prepared Text: " + prepared);

        String encrypted = encrypt(prepared);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}