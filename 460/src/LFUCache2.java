import java.util.HashMap;

public class LFUCache2 {
    private final long startTime;
    private final Heap heap;
    private final HashMap<Integer, Node> map;
    private final int capacity;

    public LFUCache2(int capacity) {
        heap = new Heap(capacity);
        map = new HashMap<>();
        this.startTime = System.nanoTime();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        node.accessTimes += 1;
        node.lastUpdateTime = System.nanoTime();
        heap.evaluateDown(node.index);
        return node.value;
    }

    public void set(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        Node node = map.get(key);
        boolean exist = true;
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            exist = false;
        }
        node.accessTimes += 1;
        node.lastUpdateTime = System.nanoTime();
        node.value = value;
        if (exist) {
            heap.evaluateDown(node.index);
        } else {
            if (heap.count >= capacity) {
                Node firstNode = heap.nodes[0];
                map.remove(firstNode.key);
                heap.replaceFirst(node);
            } else {
                heap.insertLast(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Heap: " + heap; // \n Map: " + map;
    }

    private static class Node implements Comparable<Node>{
        private int key, value, accessTimes = 0, index;
        private long lastUpdateTime = System.nanoTime();
        //private boolean isActive = false;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if (accessTimes < o.accessTimes) {
                return -1;
            } else if (accessTimes > o.accessTimes) {
                return 1;
            } else {
                return lastUpdateTime < o.lastUpdateTime ? -1 : 1;
            }
        }

        @Override
        public String toString() {
            return "Key: " + key + " ,Value: " + value + " ,AccessTimes: " + accessTimes + " ,Index: " + index + " ,LastUpdateTime: " + lastUpdateTime;
        }
    }

    private static class Heap {
        private final Node[] nodes;
        private int count;
        private Heap(int capacity) {
            nodes = new Node[capacity];
            count = 0;
        }

        private void evaluateDown(int index) {
            int leftDownIndex = (index << 1) + 1;
            if (leftDownIndex >= count) {
                return;
            }
            int rightDownIndex = (index << 1) + 2;
            if (!isLess(index, leftDownIndex) || !isLess(index, rightDownIndex)) {
                if (isLess(leftDownIndex, rightDownIndex)) {
                    swap(index, leftDownIndex);
                    evaluateDown(leftDownIndex);
                } else {
                    swap(index, rightDownIndex);
                    evaluateDown(rightDownIndex);
                }
            }
        }

        private void evaluateUp(int index) {
            if (index == 0) {
                return;
            }
            int upIndex =  (index - 1) >> 1;
            if (isLess(index, upIndex)) {
                swap(index, upIndex);
                evaluateUp(upIndex);
            }
        }

        private void insertLast(Node node) {
            node.index = count;
            nodes[count] = node;
            count ++;
            evaluateUp(node.index);
        }

        private void replaceFirst(Node node) {
            node.index = 0;
            nodes[0] = node;
            evaluateDown(0);
        }

        private boolean isLess(int index1, int index2) {
            if (index2 >= count) {
                return true;
            }
            return nodes[index1].compareTo(nodes[index2]) < 0 ? true : false;
        }

        private void swap(int index1, int index2) {
            Node first = nodes[index1];
            Node second = nodes[index2];
            first.index = index2;
            second.index = index1;
            nodes[index1] = second;
            nodes[index2] = first;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < count; ++ i){
                sb.append("[" + i + "] : " + nodes[i] + "\n");
            }
            return sb.toString();
        }

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */