import java.util.Scanner;

public class Palindrome {
    public static boolean isPalindrome(int x) {
        
        if (x < 0) return false;
        
        int original = x;
        int reversed = 0;
        
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            
            if (reversed > Integer.MAX_VALUE / 10) {
                return false;
            }
            
            reversed = reversed * 10 + digit;
        }
        
        return original == reversed;
    }
    
    public static void main(String[] args) {
        Scanner v = new Scanner(System.in);
        
        System.out.print("Enter an integer to check if it's a palindrome: ");
        
        if (v.hasNextInt()) {
            int x = v.nextInt();
            boolean result = isPalindrome(x);
            
            System.out.println(x + " is " + (result ? "" : "not ") + "a palindrome");
        } else {
            System.out.println("Invalid input! Please enter an integer.");
        }
        
        v.close();
    }
}