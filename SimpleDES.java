import java.util.*;

public class SimpleDES {

    static String xor(String a, String b){
        String result="";
        for(int i=0;i<a.length();i++){
            result += (a.charAt(i)==b.charAt(i))?'0':'1';
        }
        return result;
    }

    static String leftShift(String s){
        return s.substring(1)+s.charAt(0);
    }

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String pt=sc.next();

        System.out.print("Enter key: ");
        String key=sc.next();

        String left = pt.substring(0,pt.length()/2);
        String right = pt.substring(pt.length()/2);

        for(int i=0;i<2;i++){

            String temp = right;

            right = xor(left,key);

            left = temp;

            key = leftShift(key);
        }

        String cipher = left + right;

        System.out.println("Cipher Text: "+cipher);

        sc.close();
    }
}