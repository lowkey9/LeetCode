package problems;

public class LongestPalindormicString {

    /*
     * below is my solution with O(n^2) time complexity
     * the idea is to start with the position that can be the center of a palindromic string
     */
    public String mySolution(String s) {
        if(s == null || s.length() == 0)
            return s;
        int len = s.length();
        String res = "";
        for(int i=0; i<=2*len-2; i++) {
            int left = i/2;
            int right = left + i % 2;
            while(left >= 0 && right < len) {
                if(s.charAt(left) != s.charAt(right))
                    break;
                int tmp = right - left + 1;
                if(tmp > res.length())
                    res = s.substring(left, right+1);
                left--;
                right++;
            }
        }
        return res;
    }


    /*
     * other solutions have similar idea but faster run time
     */
    public String others(String s) {
        if(s == null || s.length() == 0)
            return s;
        int max = 0, left = 0, right = 0;
        for(int i=0; i<s.length(); i++) {
            int expandLeft = helper(i, i, s);
            int expandRight = helper(i, i+1, s);
            int len = Math.max(expandLeft, expandRight);
            if(len > right - left) {
                left = i - (len-1)/2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    private int helper(int i, int j, String s) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}
