import java.util.*;

public class CustomStack {
    private int maxSize;
    private int[] stack;
    private int[] inc;
    private int top;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.inc = new int[maxSize];
        this.top = -1;
    }

    public void push(int x) {
        if (top < maxSize - 1) {
            stack[++top] = x;
        }
    }

    public int pop() {
        if (top == -1) return -1;

        int res = stack[top] + inc[top];
        if (top > 0) {
            inc[top - 1] += inc[top];  // propagate increment downwards
        }
        inc[top] = 0;  // reset increment for current element
        top--;
        return res;
    }

    public void increment(int k, int val) {
        int limit = Math.min(k - 1, top);
        if (limit >= 0) {
            inc[limit] += val;
        }
    }

    public static void main(String[] args) {
        CustomStack cs = new CustomStack(5);
        cs.push(1);
        cs.push(2);
        cs.push(3);
        System.out.println(cs.pop());       // returns 3
        cs.increment(2, 100);            // increment bottom 2 elements by 100
        System.out.println(cs.pop());       // returns 102 (2 + 100)
        System.out.println(cs.pop());       // returns 101 (1 + 100)
        System.out.println(cs.pop());       // returns -1 (empty stack)
    }
}
