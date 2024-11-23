package strukturdata.linear;

public class Stack {
    private int capacity;
    private Object[] data;
    private int top;
    private int count;


    public Stack(int size) {
        top = -1;
        data = new Object[size];
        capacity = size;
        count = 0;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(Object x) {
        if (isFull()) {
            System.out.println("\nStack Penuh!!!");
        } else {
            System.out.println("\nMemasukkan " + x + " Ke Dalam Stack");
            data[++top] = x;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            System.out.println("\nStack Kosong!!!");
            return 0;
        }
        System.out.println("\nMengeluarkan " + data[top] + " Dari Stack");
        return data[top--];
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("\nStack Kosong!!!");
        } else {
            System.out.println("\nData Teratas : " + data[top]);
        }
    }

    public void printStack() {
        System.out.print("\nData Dalam Stack : ");
        if (isEmpty()) {
            System.out.println("Stack Kosong!!!");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }
    }

    public void clear() {
        if (isEmpty()) {
            System.out.println("\nStack Kosong!!!");
        } else {
            top = -1;
        }
    }

    public void count() {
        System.out.println("\nJumlah Data Dalam Stack : " + (top + 1));
    }
}
