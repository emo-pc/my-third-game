import java.util.ArrayList;

public class Queue<T> {
    private T[] list;
    private int waiting;
    private int next;
    private int size;
    private static final int DEFAULT=10;
    @SuppressWarnings("unchecked")
    public Queue(){
        this.list=(T[]) new Object[DEFAULT];
        this.waiting = 0;
        this.next = 0;
        this.size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void enqueue(T item) {
        //resizing in case of overflow
        if (size == list.length) {
            resize();
        }
        //placing the item and updating next and size
        list[next] = item;
        next=(next + 1) % list.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        //saving item and updating waiting and size
        T item = list[waiting];
        //updating list
        list[waiting] = null;
        waiting=(waiting + 1) % list.length;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        //doubling size
        T[] newList = (T[]) new Object[list.length * 2];
        //replacing old items
        for (int i = 0; i < size; i++) {
            newList[i] = list[(waiting+i)%list.length];
        }
        list=newList;
        waiting=0;
        next=size;
    }
}
