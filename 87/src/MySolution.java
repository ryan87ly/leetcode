import java.util.ArrayList;

public class MySolution {
    public int largestRectangleArea(int[] heights) {
        CustomList l = new CustomList();
        int removedMaxScore = 0;
        for (int i = 0, length = heights.length; i < length; ++ i) {
            int height = heights[i];
            int index = l.getIndex(height);
            boolean isInsert = l.insert(height, index, i);
            int currentRemoveMaxScore = l.removeLarger(index, isInsert, i);
            removedMaxScore = Math.max(removedMaxScore, currentRemoveMaxScore);
        }
        return Math.max(l.maxScore(heights.length - 1), removedMaxScore);
    }

    private static class CustomList {
        private ArrayList<Node> sortedList = new ArrayList<>();
        private CustomList() {

        }

        private int getIndex(int height) {
            int length = sortedList.size();
            if (length == 0 || height <= sortedList.get(0).height) return 0;
            if (height > sortedList.get(length - 1).height) return length;
            int firstIndex = 0, lastIndex = length - 1;
            while(true) {
                int current = (firstIndex + lastIndex) / 2;
                int next = current + 1;
                if (height == sortedList.get(current).height) {
                    return current;
                }
                if (height > sortedList.get(current).height && height <= sortedList.get(next).height) {
                    return next;
                }
                if (height > sortedList.get(next).height) {
                    firstIndex = next;
                    continue;
                }
                lastIndex = current - 1;
            }
        }

        private boolean insert(int height, int index, int currentRound) {
            if (sortedList.size() > index && sortedList.get(index).height == height) {
                return false;
            }
            sortedList.add(index, new Node(height, currentRound));
            return true;
        }

        private int removeLarger(int index, boolean isInsert, int currentRound) {
            int length = sortedList.size();
            if (index >= length) return 0;
            Node node = sortedList.get(index);
            int minStartRound = node.insertRound, removedMaxScore = 0;
            for (int i = index + 1; i < length; ++i) {
                Node removedNode = sortedList.remove(index + 1);
                minStartRound = Math.min(minStartRound, removedNode.insertRound);
                removedMaxScore = Math.max(removedMaxScore, removedNode.score(currentRound - 1));
            }
            if (isInsert) node.insertRound = minStartRound;
            return removedMaxScore;

        }

        private int maxScore(int round) {
            int max = 0;
            for (Node node : sortedList) {
                int score = node.score(round);
                if (score >= max) max = score;
            }
            return max;
        }
    }

    private static class Node {
        private int height;
        private int insertRound;

        private Node (int height, int insertRound) {
            this.height = height;
            this.insertRound = insertRound;
        }

        private int score(int currentRound) {
            return height * (currentRound - insertRound + 1);
        }
    }
}