import java.util.PriorityQueue;

public class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Integer> profileQueue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        PriorityQueue<int[]> capitalQueue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        int length = Profits.length;
        for(int i = 0; i < length; ++ i) {
            capitalQueue.add(new int[]{Profits[i], Capital[i]});
        }
        int totalProfit = W;
        for(int i = 0; i < k; ++ i) {
            while(!capitalQueue.isEmpty() && capitalQueue.peek()[1] <= totalProfit) {
                profileQueue.add(capitalQueue.poll()[0]);
            }
            if (profileQueue.isEmpty()) {
                break;
            }
            totalProfit += profileQueue.poll();
        }
        return totalProfit;
    }
}