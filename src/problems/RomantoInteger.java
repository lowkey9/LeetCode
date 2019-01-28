package problems;

import java.util.HashMap;
import java.util.Map;

public class RomantoInteger {
    private Map<Character, Integer> map;

    public RomantoInteger() {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }


    /*
     * this is my solution
     * using map and iterating the string from behind
     * check whether the previous character is smaller than current character
     */
    public int convert(String str) {
        if(str == null || str.length() == 0)
            return 0;
//        Integer pre = null;
        int i = str.length() - 1;
        int res = 0;    // assume there will not be overflow
        while(i >= 0) {
            Character cur = str.charAt(i);
            Character pre = i - 1 > 0 ? str.charAt(i-1) : null;
            if(pre != null && pre <= cur) {
                res += map.get(cur) - map.get(pre);
                i--;
            }
            else {
                res += map.get(cur);
            }
            i--;
        }
        return res;
    }


    /*
     * another approach is to iterate the string from the beginning
     * and we can also using several conditions corresponding to different characters
     * instead of using Map
     */
    public int fromBeginning(String str) {
        if(str == null || str.length() == 0)
            return 0;
        char[] ca = str.toCharArray();
        int res = 0, pre = 0;
        for(char c : ca) {
            res += map.get(c);
            if(pre != 0 && pre < map.get(c))
                res -= 2 * pre;
            pre = map.get(c);
        }
        return res;
    }
}
