import java.util.*;
public class DES_SBOX {
    static int[][] sbox = {
        {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
        {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
        {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
        {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 6-bit input: ");
        String input = sc.next();
        String rowBits = "" + input.charAt(0) + input.charAt(5);
        int row = Integer.parseInt(rowBits,2);
        String colBits = input.substring(1,5);
        int col = Integer.parseInt(colBits,2);
        int value = sbox[row][col];
        System.out.println("Row: " + row);
        System.out.println("Column: " + col);
        System.out.println("SBOX Output (decimal): " + value);
        String binary = String.format("%4s", Integer.toBinaryString(value)).replace(' ', '0');
        System.out.println("SBOX Output (4-bit binary): " + binary);
        sc.close();
    }
}