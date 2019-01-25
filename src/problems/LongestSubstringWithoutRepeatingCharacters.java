package problems;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
    public int longestSubstring(String s) {
        if(s == null || s.length() == 0)
            return 0;
        char[] cnt = new char[256];
        int i = 0, j = 0;
        int res = Integer.MIN_VALUE;
        while(j < s.length()) {
            // do not need to subtract null while getting the index in whole ASCII range
            // it is also wrong to do so, there will be an error
            int index = (int)s.charAt(j);
            cnt[index]++;
            if(cnt[index] == 1) {
                j++;
                res = Math.max(res, j-i);
            }
            else {
                while(i < j && cnt[index] > 1) {
                    int cur = (int)s.charAt(i++);
                    cnt[cur]--;
                }
                // do not forget to advance j
                j++;
            }
        }
        return res;
    }


    /*
     * below is a method that can save the while loop on line 20
     * pre records the last position of such character before current position
     * if pre[cur] > index, it means there is a duplicate in between (from last time)
     * update index and then compute the length
     */
    public int concise(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int[] pre = new int[256];
        Arrays.fill(pre, -1);
        int index = -1, res = Integer.MIN_VALUE;
        for(int i=0; i<s.length(); i++) {
            char cur = s.charAt(i);
            if(pre[cur] > index) {
//                res = Math.max(res, i - index);
                index = pre[cur];
            }
            res = Math.max(res, i - index);
            pre[cur] = i;
        }
        return res;
    }
}
