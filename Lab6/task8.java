import java.io.*;
import java.util.Arrays;
 
public class Cycle_Graph {
 
    private ArrayList[] lists; // Список смежности
    private int color[]; // Цвет вершин: 0 - белый, 1 - серый, 2 - черный
    private int pred[]; // Массив предков. По массиву восстанавливаем цикл
    private boolean cyclic = false; // Флаг наличия цикла в графе
    ArrayList<Integer> cycle = new ArrayList<>();
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cycle.in"));
        PrintWriter writer = new PrintWriter(new File("cycle.out"));
        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);
        lists = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            lists[i] = new ArrayList();
        }
        color = new int[vertexCount];
        pred = new int[vertexCount];
        Arrays.fill(pred, -1);
        for (int i = 0; i < edgeCount; i++) {
            data = reader.readLine().split(" ");
            lists[Integer.parseInt(data[0]) - 1].add(Integer.parseInt(data[1]) - 1);
        }
        for (int v = 0; v < vertexCount; v++) {
            dfsCycle(v);
        }
        if (cyclic) {
            writer.println("YES");
            for (int i = 1; i < cycle.size(); i++) {
                writer.print((cycle.get(i) + 1) + " ");
            }
            writer.println();
        } else {
            writer.println("NO");
        }
        reader.close();
        writer.close();
    }
 
    private void dfsCycle(int v) {
        if (color[v] == 2) {
            return;
        }
        if (cyclic) {
            return;
        }
        if (color[v] == 1) {
            cyclic = true;
            cycle.add(v);
            for (int ver = pred[v]; ver != v; ver = pred[ver]) {
                cycle.add(ver);
            }
            cycle.add(v);
            for (int i = 0; i < cycle.size() / 2; ++i) {
                int temp = cycle.get(i);
                cycle.set(i, cycle.get(cycle.size() - 1 - i));
                cycle.set(cycle.size() - 1 - i, temp);
            }
            return;
        }
        color[v] = 1; //помечаем вершину как серую
        //запускаем обход из всех вершин, смежных с вершиной v
        for (int i = 0; i < lists[v].size(); ++i) {
            int w = (int) lists[v].get(i);
            //вершина v - это предок вершины w при обходе
            pred[w] = v;
            //вызов обхода от вершины w, смежной с вершиной v
            dfsCycle(w);
            if (cyclic) {
                return;
            }
        }
        color[v] = 2; //помечаем вершину как черную
    }
 
    class ArrayList<E> {
        int length;
        int sizeOf;
        E[] array;
 
        ArrayList() {
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
 
        boolean add(E e) {
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
 
        void ensureCapacity() {
            if (length + 1 == sizeOf) {
                Object[] oldData = array;
                sizeOf = (sizeOf * 3) / 2 + 1;
                array = (E[]) new Object[sizeOf];
                System.arraycopy(oldData, 0, array, 0, length);
            }
        }
 
        void set(int i, E value) {
            array[i] = value;
        }
    }
 
    public static void main(String[] args) throws IOException {
        new Cycle_Graph().run();
    }
}
