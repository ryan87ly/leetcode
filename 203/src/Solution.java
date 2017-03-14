public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null, cur = head;
        while(cur != null) {
            if (cur.val == val) {
                if (prev != null) prev.next = cur.next;
                if (cur == head) head = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}