import java.io.*;
import java.util.List;
 
public class Topological_Sorting {
 
    private Stack stack;
    private int[] color;
    private int[] numbers;
    private ArrayList[] lists;
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("topsort.in"));
        PrintWriter writer = new PrintWriter(new File("topsort.out"));
 
        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);
        stack = new Stack(vertexCount);
        color = new int[vertexCount];
        numbers = new int[vertexCount];
        lists = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            lists[i] = new ArrayList();
 
        }
        for (int i = 0; i < edgeCount; i++) {
            data = reader.readLine().split(" ");
            lists[Integer.parseInt(data[0]) - 1].add(Integer.parseInt(data[1]) - 1);
        }
        if (topological_sort(vertexCount)) {
            for (int i = 0; i < vertexCount; i++) {
                writer.write(Integer.toString(numbers[i]) + " ");
            }
        } else {
            writer.write(Integer.toString(-1));
        }
        writer.close();
        reader.close();
    }
 
    boolean topological_sort(int vertexCount){
        boolean Cycle;
        for(int i = 0; i < vertexCount; i++){
            Cycle = dfs(i);
            if(Cycle) {
                return false;
            }
        }
        for(int i = 0; i < vertexCount; i++){
            numbers[i] = stack.pop() + 1;
        }
        return true;
    }
 
    boolean dfs(int v){
        if(color[v] == 1) return true;
        if(color[v] == 2) return false;
        color[v] = 1;
        for(int i = 0; i < lists[v].size(); i++){
            if(dfs((Integer) lists[v].get(i))) return true;
        }
        stack.push(v);
        color[v] = 2;
        return false;
    }
 
    class Stack {
        private int top;
        private int[] stackArray;
 
        Stack(int size) {
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
 
 
    class ArrayList<E> {
        int length;
        int sizeOf;
        E[] array;
 
        public ArrayList() {
            array = (E[]) new Object[10];
            length = 0;
            sizeOf = 10;
        }
 
 
        public int size() {
            return length;
        }
 
 
        public E get(int index) {
            return array[index];
        }
 
 
        public boolean add(E e) {
            ensureCapacity();
            array[length++] = e;
            return false;
        }
 
        public void ensureCapacity() {
            if (length + 1 == sizeOf) {
                Object[] oldData = array;
                sizeOf = (sizeOf * 3) / 2 + 1;
                array = (E[]) new Object[sizeOf];
                System.arraycopy(oldData, 0, array, 0, length);
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        new Topological_Sorting().run();
    }
}
