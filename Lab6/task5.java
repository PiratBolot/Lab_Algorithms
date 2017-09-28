import java.io.*;
import java.util.Arrays;
 
public class Shortest_path {
 
    boolean[] used;
    ArrayList<Integer>[] lists;
    Queue queue;
    int[] distance;
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("pathbge1.in"));
        PrintWriter writer = new PrintWriter(new File("pathbge1.out"));
 
        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);
        used = new boolean[vertexCount];
        lists = new ArrayList[vertexCount];
        queue = new Queue(vertexCount);
        distance = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < vertexCount; i++) {
            lists[i] = new ArrayList();
        }
        for (int i = 0; i < edgeCount; i++) {
            data = reader.readLine().split(" ");
            lists[Integer.parseInt(data[0]) - 1].add(Integer.parseInt(data[1]) - 1);
            lists[Integer.parseInt(data[1]) - 1].add(Integer.parseInt(data[0]) - 1);
        }
        BSF(0);
        for (int i = 0; i < vertexCount; i++) {
            writer.write(Integer.toString(distance[i]) + " ");
        }
        writer.close();
        reader.close();
    }
 
    public void BSF(int s) {
        if (used[s] == true) {
            return;
        }
        queue.insert(s);
        used[s] = true;
        distance[s] = 0;
        while (!queue.empty()) {
            int vertex = queue.remove();
            for (int i = 0; i < lists[vertex].size(); i++) {
                if (used[lists[vertex].get(i)] == false) {
                    used[lists[vertex].get(i)] = true;
                    distance[lists[vertex].get(i)] = distance[vertex] + 1;
                    queue.insert(lists[vertex].get(i));
                }
            }
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
 
        public void clear() {
            for (int i = 0; i < length; i++) {
                array[i] = null;
            }
            length = 0;
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
 
    static class Queue {
 
        private int[] queue;
        private int maxSize;
        private int front;
        private int rear;
 
        public Queue(int maxSize) {
            this.maxSize = maxSize;
            queue = new int[maxSize];
            rear = -1;
            front = 0;
        }
 
        public void insert(int elem) {
            queue[++rear] = elem;
        }
 
        public int remove() {
            int temp = queue[front++];
            return temp;
        }
 
        public boolean empty() {
            return (front == rear + 1);
        }
    }
 
    public static void main(String[] args) throws IOException {
        new Shortest_path().run();
    }
}
