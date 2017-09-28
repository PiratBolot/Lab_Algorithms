import java.io.*;
 
import static java.lang.Integer.sum;
 
class Stack {
    private int top;
    private int[] stackArray;
 
    public Stack(int size) {
        stackArray = new int[size];
        top = -1;
    }
 
    public void push(int number) {
        stackArray[++top] = number;
    }
 
    public int pop() {
        return stackArray[top--];
    }
}
 
public class ReversePolishNotation {
 
    final int SIZE = 100;
 
    public boolean checkString(String string) {
        if (string == null || string.length() == 0) return false;
 
        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }
 
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("postfix.in"));
        PrintWriter writer = new PrintWriter(new File("postfix.out"));
        Stack stack = new Stack(SIZE);
        String[] strings;
        strings = reader.readLine().split(" ");
        for (String str : strings) {
            if (checkString(str)) {
                stack.push(Integer.parseInt(str));
                continue;
            }
            int number2 = stack.pop();
            int number1 = stack.pop();
            stack.push(getResult(number1, number2, str));
        }
        writer.write(Integer.toString(stack.pop()));
        writer.close();
        reader.close();
    }
 
    private int getResult(int number1, int number2, String str) {
        if (str.equals("+")) {
            return sum(number1, number2);
        } else if (str.equals("-")) {
            return subtraction(number1, number2);
        } else {
            return multiplication(number1, number2);
        }
    }
 
    private int multiplication(int number1, int number2) {
        return number1 * number2;
    }
 
    private int subtraction(int number1, int number2) {
        return number1 - number2;
    }
 
    public static void main(String[] args) throws IOException {
        new ReversePolishNotation().run();
    }
}
