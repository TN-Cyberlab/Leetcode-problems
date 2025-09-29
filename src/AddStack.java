/* import java.util.Stack;

public class AddStack {

public static void main(String[] args) {
Stack <Integer> m = new Stack <> ();

 m.push(10);
  m.push(20);
   m.push(60);
    m.push(96);
    
    System.out.println("the elements in the stack are : " + m);
}
}
*/
import java.util.Scanner;
import java.util.Stack;

public class AddStack {

public static void main(String[] args) {
Scanner v = new Scanner(System.in);
Stack <Integer> m = new Stack <> ();

System.out.println("enter the elements :");

for(int i=0;i<5;i++) {
  int num = v.nextInt();
  m.push(num);
} 
System.out.println("the elements of the stack are : " + m);
System.out.println("the top element is : " + m.peek());
v.close();
}
}