import java.util.*;

public class hillcipher {

    // Function to encrypt plaintext
    public static String encrypt(String text, int[][] key) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        //this replaces "Hello World 123" to "HELLOWORLD"

        // If length is odd, add X cause hill cipher encrypts letters in pairs
        if (text.length() % 2 != 0) {
            text += "X";
        }

        String result = "";

        //Instead of reading one letter at a time, we read two letters at a time.
        for (int i = 0; i < text.length(); i += 2) {

            // Convert letters to numbers (A=0, B=1...)
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            // Matrix multiplication
            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;

            // Convert numbers back to letters
            result += (char)(c1 + 'A');
            result += (char)(c2 + 'A');
        }

        return result;
    }

    // find modular inverse
    public static int modInverse(int det){

        det = det % 26;

        for(int i=1;i<26;i++){
            if((det*i)%26 == 1){
                return i;
            }
        }

        return -1;
    }

    // find inverse matrix
    public static int[][] inverseMatrix(int[][] key){

        int a = key[0][0];
        int b = key[0][1];
        int c = key[1][0];
        int d = key[1][1];

        int det = (a*d - b*c);
        int invDet = modInverse(det);

        int[][] inv = new int[2][2];

        inv[0][0] = (d * invDet) % 26;
        inv[0][1] = (-b * invDet) % 26;
        inv[1][0] = (-c * invDet) % 26;
        inv[1][1] = (a * invDet) % 26;

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                if(inv[i][j] < 0){
                    inv[i][j] += 26;
                }
            }
        }

        return inv;
    }

    public static String decrypt(String cipher, int[][] key){

        int[][] inv = inverseMatrix(key);

        String result = "";

        for(int i=0;i<cipher.length();i+=2){

            int a = cipher.charAt(i) - 'A';
            int b = cipher.charAt(i+1) - 'A';

            int p1 = (inv[0][0]*a + inv[0][1]*b) % 26;
            int p2 = (inv[1][0]*a + inv[1][1]*b) % 26;

            result += (char)(p1 + 'A');
            result += (char)(p2 + 'A');
        }

        return result;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int[][] key = new int[2][2];

        System.out.println("Enter key matrix:");

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                key[i][j] = sc.nextInt();
            }
        }

        sc.nextLine();

        System.out.println("Enter plaintext:");
        String text = sc.nextLine();

        String cipher = encrypt(text,key);
        System.out.println("Encrypted Text: " + cipher);

        String plain = decrypt(cipher,key);
        System.out.println("Decrypted Text: " + plain);

        sc.close();
    }

} 
