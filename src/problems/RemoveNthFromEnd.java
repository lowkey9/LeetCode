package problems;

public class RemoveNthFromEnd {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int n) {
            val = n;
        }
    }

    ListNode head;
    int n;

    public RemoveNthFromEnd(int[] nums, int n) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        head = dummy.next;
        this.n = n;
    }


    /*
     * we need to find the n+1th node from the end
     * in order to remove the nth node
     * the gap between the n+1th node and the end is n-1
     * the first loop is to make the n-1 gap
     * the second loop is to find the n+1th node from the end
     */
    public ListNode solution() {
        if(head == null)
            return head;
        ListNode tmp = head, tmp1 = head;
        while(n > 0) {
            tmp1 = tmp1.next;
            n--;
        }
        if(tmp1 == null)
            head = head.next;
        else {
            // this loop
            while(tmp1.next != null) {
                tmp = tmp.next;
                tmp1 = tmp1.next;
            }
            tmp.next = tmp.next.next;
        }
        return head;
    }
}
