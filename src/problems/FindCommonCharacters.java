package problems;

//0315

import java.util.ArrayList;
import java.util.List;

public class FindCommonCharacters {
    /*
     * this is my solution
     * similar alternative way is to use a 2d array,
     * each row is at the length of 26, representing a String in A
     * find the min value in column and add to list.
     */
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if(A == null || A.length == 0)
            return res;
        int[] cnt = new int[26];
        for(int i=0; i<A[0].length(); i++) {
            int index = A[0].charAt(i) - 'a';
            cnt[index]++;
        }
        for(int i=1; i<A.length; i++) {
            char[] str = A[i].toCharArray();
            int[] tmp = new int[26];
            for(char c : str) {
                tmp[c - 'a']++;
            }
            for(int j=0; j<26; j++) {
                if(cnt[j]>0) {
                    cnt[j] = Math.min(cnt[j], tmp[j]);
                }
            }
        }
        for(int k=0; k<26; k++) {
            while(cnt[k]>0) {
                res.add(Character.toString((char)('a'+k)));
                cnt[k]--;
            }
        }
        return res;
    }
}
