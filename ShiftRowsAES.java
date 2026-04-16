import java.util.*;

public class ShiftRowsAES {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] a = new int[4][4];

        System.out.println("Enter 4x4 AES Matrix (hex):");

        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                a[i][j] = Integer.parseInt(sc.next(),16);

        // Shift Rows
        for(int i=1;i<4;i++) {   // row 1,2,3
            for(int k=1;k<=i;k++) {   // shift i times
                int first = a[i][0];

                for(int j=0;j<3;j++)
                    a[i][j] = a[i][j+1];

                a[i][3] = first;
            }
        }

        System.out.println("After Shift Rows:");

        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                System.out.print(String.format("%02x ", a[i][j]));
            }
            System.out.println();
        }
    }
}