public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node((T) "null", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node((T) "null", null, null);
        sentinel.next = new Node(item, sentinel, sentinel);
        sentinel.prev = sentinel;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        Node list = sentinel;
        while (list.next != sentinel) {
            list = list.next;
            System.out.print(list.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rmvitem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return rmvitem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rmvitem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return rmvitem;
    }

    public T get(int index) {
        Node list = sentinel.next;
        if (index > size - 1 || index < 0) {
            return null;
        }
        while (index > 0) {
            list = list.next;
            index -= 1;
        }
        return list.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node list = sentinel.next;
        return getRecursiveHelper(index, list);
    }

    private T getRecursiveHelper(int index, Node list) {
        if (index == 0) {
            return list.item;
        }
        return getRecursiveHelper(index - 1, list.next);
    }
}
