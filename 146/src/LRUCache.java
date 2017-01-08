import java.util.HashMap;

public class LRUCache {
    private final int capacity;
    private HashMap<Integer, Node> map;
    private DoublyLinkedList list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new DoublyLinkedList();
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        list.moveToLast(node);
        return node.value;
    }

    public void set(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (capacity == list.count) {
                Node removed = list.removeHead();
                map.remove(removed.key);
            }
            list.insertLast(node);
            map.put(key, node);
        } else {
            node.value = value;
            list.moveToLast(node);
        }
    }

    private static class DoublyLinkedList {
        private int count;
        private Node head;
        private Node tail;

        private DoublyLinkedList() {
            this.count = 0;
        }

        private void insertLast(Node node) {
            node.prev = null;
            node.next = null;
            if (count == 0) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            ++ count;
        }

        private Node removeHead() {
            Node toBeRemoved = head;
            Node next = head.next;
            if (next != null) next.prev = null;
            head = next;
            if (count == 1) tail = null;
            -- count;
            return toBeRemoved;
        }

        private void moveToLast(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            if (head == node) head = next;
            if (tail == node) tail = prev;
            -- count;
            insertLast(node);
        }
    }

    private static class Node {
        private final int key;
        private int value;

        private Node next;
        private Node prev;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}