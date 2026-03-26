public class RSA {

    // Find modular inverse (d)
    public static int modInverse(int e, int phi) {
        for (int d = 1; d < phi; d++) {
            if ((d * e) % phi == 1) {
                return d;
            }
        }
        return -1;
    }

    // Fast modular exponentiation
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp = exp / 2;
        }
        return result;
    }   

    public static void rsa(int p, int q, int e, int M) {
        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int d = modInverse(e, phi);

        System.out.println("p = " + p + ", q = " + q);
        System.out.println("n = " + n);
        System.out.println("phi = " + phi);
        System.out.println("e = " + e);
        System.out.println("d = " + d);

        int C = modPow(M, e, n);
        System.out.println("Encrypted (C) = " + C);

        int decrypted = modPow(C, d, n);
        System.out.println("Decrypted (M) = " + decrypted);

        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        rsa(3, 7, 5, 10);
        rsa(5, 13, 5, 8);
        rsa(7, 17, 11, 11);
        rsa(7, 13, 11, 2);
        rsa(17, 23, 9, 7);
    }
}