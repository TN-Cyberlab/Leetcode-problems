

import java.util.Scanner;

public class Demo{
public static void main(String[] args){
Scanner v = new Scanner(System.in);

System.out.println("enter the celsius value");
int c = v.nextInt();

final int x =32;
final float y = 9/5;

float f = c*y+x;

System.out.println("the farenheit value is : " + f);
}
}