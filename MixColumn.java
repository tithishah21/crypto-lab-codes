package aes;

import java.util.Scanner;

public class MixColumn {

    // Multiply by 2 in GF(2^8)
    static int mul2(int x) {

        int result = x << 1;   // left shift

        // If MSB was 1, XOR with AES polynomial
        if ((x & 0x80) != 0) {
            result = result ^ 0x1B;
        }

        return result & 0xFF; // keep 8 bits
    }

    // General multiplication for 1,2,3
    static int multiply(int a, int b) {

        if (a == 1)
            return b;

        else if (a == 2)
            return mul2(b);

        else if (a == 3)
            return mul2(b) ^ b;

        else
            return 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] constant = new int[4][4];
        int[][] input = new int[4][4];
        int[][] output = new int[4][4];

        // Input constant matrix
        System.out.println("Enter Constant Matrix (4x4) in HEX:");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                constant[i][j] = Integer.parseInt(sc.next(), 16);
            }
        }

        // Input state matrix
        System.out.println("Enter Input Matrix (4x4) in HEX:");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                input[i][j] = Integer.parseInt(sc.next(), 16);
            }
        }

        // Matrix multiplication using GF(2^8)
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                output[i][j] =
                        multiply(constant[i][0], input[0][j]) ^
                        multiply(constant[i][1], input[1][j]) ^
                        multiply(constant[i][2], input[2][j]) ^
                        multiply(constant[i][3], input[3][j]);
            }
        }

        // Print result
        System.out.println("\nOutput Matrix:");

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("%02X ", output[i][j]));
            }

            System.out.println();
        }

        sc.close();
    }
}