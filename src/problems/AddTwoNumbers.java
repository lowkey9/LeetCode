package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AddTwoNumbers {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int num) {val = num;}
    }

    private ListNode l1;
    private ListNode l2;

    public AddTwoNumbers(ListNode l1, ListNode l2) {
        this.l1 = l1;
        this.l2 = l2;
    }


    /*
     * do not forget to use carry and add the heading digit if carry is not 0
     * the code can be more concise
     * time and space complexity O(n)
     */
    public ListNode solution() {
        if(l1 == null && l2 == null)
            return null;
        if(l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        int carry = 0;
        while(l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + carry;
            carry = tmp / 10;
            tmp %= 10;
            head.next = new ListNode(tmp);
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode rest = l1 == null ? l2 : l1;
        while(rest != null) {
            int tmp = rest.val + carry;
            carry = tmp / 10;
            tmp %= 10;
            head.next = new ListNode(tmp);
            rest = rest.next;
            head = head.next;
        }
        if(carry != 0)
            head.next = new ListNode(carry);
        return dummy.next;
    }


    public ListNode conciseSolution() {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
            cur = cur.next;
        }
        if(carry == 1)
            cur.next = new ListNode(carry);
        return dummy.next;
    }


    /*
     * this is the solution for lc 445 add two numbers II
     * two lists are not reversed
     * if we can modify lists, we can reverse them and do the similar thing as in I to get the result
     * but now let's assume we cannot do that
     */
    public ListNode notReversed() {
        ListNode res1 = mySolution();
        ListNode res2 = usingStack();
        return res1;
    }


    /*
     * time and space complexity O(M+N)
     */
    private ListNode mySolution() {
        // since i assume i cannot reverse the linked list
        // i use additional list to store numbers in reverse order and add them
        // then create result linked list in reverse order
        // but actually i am reversing the linked list (in different way)
        if(l1 == null && l2 == null)
            return null;
        if(l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        ListNode i = l1, j = l2;
        List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        while(i != null) {
            n1.add(0, i.val);
            i = i.next;
        }
        while(j != null) {
            n2.add(0, j.val);
            j = j.next;
        }
        List<Integer> resL = new ArrayList<>();
        int tmp = 0, carry = 0;
        while(tmp < n1.size() || tmp < n2.size()) {
            int x = tmp < n1.size() ? n1.get(tmp) : 0;
            int y = tmp < n2.size() ? n2.get(tmp) : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            resL.add(sum % 10);
            tmp++;
        }
        if(carry > 0)
            resL.add(carry);
        ListNode res = createList(resL);
        return res;
    }


    private ListNode createList(List<Integer> resL) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i=resL.size()-1; i>=0; i--) {
            int num = resL.get(i);
            ListNode tmp = new ListNode(num);
            cur.next = tmp;
            cur = cur.next;
        }
        return dummy.next;
    }


    /*
     * when we need to do something in reverse way
     * we need to consider Stack
     */
    private ListNode usingStack() {
        Stack<Integer> st1 = new Stack<>(), st2 = new Stack<>();
        while(l1 != null) {
            st1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            st2.push(l2.val);
            l2 = l2.next;
        }
//        Stack<Integer> nums = new Stack<>();
//        int carry = 0;
//        while(!st1.isEmpty() || !st2.isEmpty()) {
//            int x = st1.isEmpty() ? 0 : st1.pop();
//            int y = st2.isEmpty() ? 0 : st2.pop();
//            int sum = x + y + carry;
//            carry = sum / 10;
//            nums.push(sum % 10);
//        }
//        if(carry > 0)
//            nums.push(carry);
//        ListNode dummy = new ListNode(0);
//        ListNode cur = dummy;
//        while(!nums.isEmpty()) {
//            cur.next = new ListNode(nums.pop());
//            cur = cur.next;
//        }
//        return dummy.next;
        // the following is better
        int carry = 0;
        ListNode last = null;
        while (!st1.isEmpty() || !st2.isEmpty() || carry != 0) {
            int left = st1.isEmpty() ? 0 : st1.pop();
            int right = st2.isEmpty() ? 0 : st2.pop();
            int val = (left + right + carry) % 10;
            carry = (left + right + carry) / 10;

            ListNode node = new ListNode(val);
            node.next = last;
            last = node;
        }
        return last;
    }
}
