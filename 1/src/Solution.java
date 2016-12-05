import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, LinkedList<Integer>> m = new HashMap<>();
        for(int i = 0; i < nums.length; ++i)
        {
            int num = nums[i];
            if (m.containsKey(num))
            {
                m.get(num).add(i);
            }
            else
            {
                LinkedList<Integer> l = new LinkedList<>();
                l.add(i);
                m.put(num, l);
            }
        }
        for(HashMap.Entry<Integer, LinkedList<Integer>> entry : m.entrySet())
        {
            int r = target - entry.getKey();
            if (m.containsKey(r))
            {
                if (entry.getKey().equals(r))
                {
                    if (entry.getValue().size() > 1)
                    {
                        return new int[]{entry.getValue().get(0), entry.getValue().get(1)};
                    }
                }
                else
                {
                    return new int[]{entry.getValue().getFirst(), m.get(r).getFirst()};
                }
            }
        }
        return null;
    }
}