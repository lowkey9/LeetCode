package problems;

// 0315

import java.util.Stack;

public class ValidAfterSubstitution {
    /*
     * this is lc1003
     */

    /*
     * this is my solution
     * first check the index of "abc", if there are no "abc" return false
     * then concatenate left and right parts, check "abc" again
     * do the above recursively.
     * stop on S.length() == 0 or S.equals("abc")
     */
    public boolean isValid(String S) {
        if(S.length() == 0 || S.equals("abc"))
            return true;
        if(S.length() <= 2)
            return false;
        int index = S.indexOf("abc");
        if(index == -1)
            return false;
        String left = S.substring(0, index);
        String right = S.substring(index + 3, S.length());
        String tmp = left + right;
        if(tmp.indexOf("abc") == -1)
            return false;
        // boolean l = isValid(S.substring(0, index));
        // boolean r = isValid(S.substring(index + 3, S.length()));
        // return l && r;
        return isValid(tmp);
    }


    /*
     * another solution is to keep removing "abc" in the string, until there is no more
     * check the length of what is left
     * if the length is greater than 0, return false, otherwise, return true
     */
    public boolean isValidAnother(String S) {
        while(S.contains("abc")) {
            S = S.replaceAll("abc", "");
        }
        if(S.length() > 0)
            return false;
        return true;
    }


    /*
     * another solution is to use stack
     * visit every character in the string
     * if the character is 'c', check the size of the stack and the previous two characters.
     * if the size is smaller than 2 or the previous two characters is not 'a' and 'b', return false
     * if the character is not 'c', push it into stack
     * finally check whether the stack is empty
     */
    public boolean isValidStack(String S) {
        if (null == S || S.isEmpty() || S.length() % 3 != 0) {
            return false;
        }
        Stack<Character>  stack = new Stack<>();
        for (char ch : S.toCharArray()) {
            if (ch == 'c') {
                if (stack.size() < 2) {
                    return false;
                }
                char second = stack.pop();
                char first = stack.pop();
                if (first != 'a' || second != 'b') {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
