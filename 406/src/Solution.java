import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int count = people.length;
        if (count == 0) return people;
        List<Node> l = new ArrayList<>(count);
        for (int i = 0; i < count; ++ i){
            l.add(new Node(people[i][0], people[i][1]));
        }
        Collections.sort(l);
        Node root = l.get(0);
        for (int i = 1; i < count; ++ i) {
            Node n = l.get(i);
            Node currentNode = root;
            Node prevNode = root;
            int currentK = n.k;
            while(true) {
                if (currentNode == null) {
                    prevNode.next = n;
                    break;
                } else if(currentNode.h < n.h) {
                    prevNode = currentNode;
                    currentNode = currentNode.next;
                } else {
                    if (currentK > 0) {
                        currentK --;
                        prevNode = currentNode;
                        currentNode = currentNode.next;
                    } else {
                        prevNode.next = n;
                        n.next = currentNode;
                        break;
                    }
                }
            }
        }
        int[][] result = new int[count][2];
        Node currentNode = root;
        for (int i = 0; i < count; ++ i) {
            result[i][0] = currentNode.h;
            result[i][1] = currentNode.k;
            currentNode = currentNode.next;
        }
        return result;
    }

    private static class Node implements Comparable<Node>{
        public final int h;
        public final int k;
        public Node next;
        private Node(int h, int k) {
            this.h = h;
            this.k = k;
        }

        @Override
        public int compareTo(Node o) {
            if (k < o.k) return -1;
            else if (k > o.k) return 1;
            return Integer.valueOf(h).compareTo(o.h);
        }
    }
}