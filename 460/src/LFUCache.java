import java.util.HashMap;

public class LFUCache {
    private HashMap<Integer, KeyNode> keyMap;
    private HashMap<Integer, FreqNode> freqMap;
    private FreqNode headFreq;
    private final int capacity;
    private int count;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyMap = new HashMap<>(capacity);
        freqMap = new HashMap<>(16);
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        KeyNode node = keyMap.get(key);
        if (node == null) {
            return -1;
        }
        removeKeyNode(node);
        increaseCount(node);
        return node.value;
    }

    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }
        KeyNode node = keyMap.get(key);
        if (node == null) {
            node = new KeyNode(key, value);
            keyMap.put(key, node);
            if (count >= capacity) {
                removeFirst();
            }
        } else {
            node.value = value;
            removeKeyNode(node);
        }
        increaseCount(node);
    }

    private void increaseCount(KeyNode node) {
        FreqNode freqNode = freqMap.get(node.count);
        ++node.count;

        FreqNode nextNode = freqNode.nextNode;
        if (nextNode == null) {
            nextNode = new FreqNode(node.count);
            freqMap.put(nextNode.count, nextNode);
            freqNode.nextNode = nextNode;
        } else if (nextNode.count != node.count) {
            nextNode = new FreqNode(node.count);
            freqMap.put(nextNode.count, nextNode);
            nextNode.nextNode = freqNode.nextNode;
            freqNode.nextNode.prevNode = nextNode;
            freqNode.nextNode = nextNode;
        }
        insertNode(nextNode, node);
    }

    private void insertNode(FreqNode freqNode, KeyNode node) {
        if (freqNode.first == null) {
            freqNode.first = node;
        }
        if (freqNode.last != null) {
            freqNode.last.nextNode = node;
            node.prevNode = freqNode.last;
        }
        freqNode.last = node;
    }

    private void removeKeyNode(KeyNode node) {
        node.remove();
        FreqNode freqNode = freqMap.get(node.count);
        if (freqNode.first == node) {
            freqNode.first = node.nextNode;
        }
        if (freqNode.last == node) {
            freqNode.last = node.prevNode;
        }
    }

    private void removeFirst() {
        if (headFreq == null) {
            return;
        }
        KeyNode first = headFreq.first;
        keyMap.remove(first.key);
        if (first.nextNode != null) {
            first.nextNode.prevNode = null;
            headFreq.first = first.nextNode;
        }
        if (headFreq.last == first) {
            headFreq.last = null;
        }
        freqMap.remove(headFreq.count);
        headFreq = headFreq.nextNode;
    }

    private void removeFreqNode(FreqNode freqNode) {
        if (freqNode.first == null && freqNode.last == null) {
            freqNode.remove();
            freqMap.remove(freqNode.count);
            if (headFreq == freqNode) {
                headFreq = freqNode.nextNode;
            }
        }
    }

    private static class KeyNode {
        private final int key;

        private KeyNode prevNode;
        private KeyNode nextNode;
        private int value;
        private int count;

        private KeyNode(int key, int value) {
            this.key = key;
            this.value = value;
            count = 0;
        }

        private void remove() {
            if (prevNode != null) {
                prevNode.nextNode = nextNode;
            }
            if (nextNode != null) {
                nextNode.prevNode = prevNode;
            }
        }
    }

    private static class FreqNode {
        private FreqNode prevNode;
        private FreqNode nextNode;
        private KeyNode first;
        private KeyNode last;
        private final int count;
        private FreqNode(int count) {
            this.count = count;
        }

        private void remove() {
            if (prevNode != null) {
                prevNode.nextNode = nextNode;
            }
            if (nextNode != null) {
                nextNode.prevNode = prevNode;
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */

