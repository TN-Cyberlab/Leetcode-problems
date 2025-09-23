import java.util.Scanner;

public class Reverse{
    public static int rev(int x) {
    int reversed = 0;
    
    while (x != 0) {
        int digit = x % 10;
        x /= 10;
        
        // Check for overflow
        if (reversed > Integer.MAX_VALUE / 10 || 
            (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
            return 0;
        }
        if (reversed < Integer.MIN_VALUE / 10 || 
            (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
            return 0;
        }
        
        reversed = reversed * 10 + digit;
    }
    
    return reversed;
}
    public static void main(String[] args){

        Scanner v = new Scanner (System.in);

        System.out.println("enter number to be reversed");
        int x = v.nextInt();
        
        System.out.println(rev(x));
        v.close();
    }
}