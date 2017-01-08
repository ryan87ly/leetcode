import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    private static final String[] THOUSANDS = new String[] {"", "Thousand", "Million", "Billion"};
    private static final String[] TENS      = new String[] {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] ONE_TEN   = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] SINGLE    = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String   HUNDRED   = "Hundred";
    private static final String   SPACE     = " ";

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        ArrayList<Integer> content = new ArrayList<>();
        int remaining = num;
        while(remaining > 0) {
            content.add(remaining % 1000);
            remaining = remaining / 1000;
        }
        LinkedList<String> result = new LinkedList<>();
        for(int i = content.size() - 1; i >=0; --i){
            int block = content.get(i);
            if (block > 0) {
                printBlock(result, block);
                result.add(THOUSANDS[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : result) {
            if (s != "") {
                sb.append(s).append(SPACE);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void printBlock(LinkedList<String> result, int num) {
        if (num >= 100) {
            result.add(SINGLE[num / 100]);
            result.add(HUNDRED);
        }
        int lastTwoDigit = num % 100;
        if (lastTwoDigit >= 10 && lastTwoDigit < 20) result.add(ONE_TEN[lastTwoDigit - 10]);
        else {
            result.add(TENS[lastTwoDigit / 10]);
            result.add(SINGLE[lastTwoDigit % 10]);
        }
    }


}