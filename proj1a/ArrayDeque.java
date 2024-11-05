public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int iniNum = 8;

    public ArrayDeque() {
        items = (T[]) new Object[iniNum];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    private int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private void resize() {
        if (size == items.length) {
            resizeHelper(items.length * 2);
        }
        if (size < items.length / 4 && items.length > 8) {
            resizeHelper(items.length / 2);
        }
    }

    private void resizeHelper(int capacity) {
        T[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;

        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; plusOne(index)) {
            System.out.println(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        resize();
        T rmv = items[plusOne(nextFirst)];
        nextFirst = plusOne((nextFirst));
        items[nextFirst] = null;
        size -= 1;
        return rmv;
    }

    public T removeLast() {
        resize();
        T rmv = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size -= 1;
        return rmv;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        index = Math.floorMod(plusOne((nextFirst) + index), items.length);
        return items[index];
    }
}
