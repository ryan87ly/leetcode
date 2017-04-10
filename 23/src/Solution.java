/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int first, int last) {
        if (first + 1 < last) {
            int mid = (first + last) / 2;
            return merge(mergeLists(lists, first, mid), mergeLists(lists, mid + 1, last));
        }
        else if (first + 1 == last) {
            return merge(lists[first], lists[last]);
        }
        else {
            return lists[first];
        }
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode root = null, current = null;
        while(true) {
            if (list1 == null) {
                if (current == null) current = list2;
                else current.next = list2;
                if (root == null) root = current;
                break;
            }
            if (list2 == null) {
                if (current == null) current = list1;
                else current.next = list1;
                if (root == null) root = current;
                break;
            }
            if (list1.val < list2.val) {
                if (current != null) current.next = list1;
                current = list1;
                list1 = list1.next;
            } else {
                if (current != null) current.next = list2;
                current = list2;
                list2 = list2.next;
            }
            if (root == null) root = current;
        }
        return root;
    }

}