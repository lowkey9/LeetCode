package problems;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    private int[] nums;
    private int k;

    public SubarraySumEqualsK(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
    }

    /*
     * this is my solution, quiet slow, only beats ~6% with 264ms
     * time complexity is O(n^2)
     * space complexity is O(n)
     */
    public int mySolution() {
        if(nums == null || nums.length == 0)
            return 0;
        int[] sums = new int[nums.length];
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
        int res = 0;
        for(int i=0; i<sums.length; i++) {
            for(int j=i; j<sums.length; j++) {
                if(i == j) {
                    if(sums[i] == k)
                        res++;
                }
                else {
                    int diff = sums[j] - sums[i];
                    if(diff == k)
                        res++;
                }
            }
        }
        return res;
    }

    /*
     * this solution if faster than mine with 143ms, which confuses me
     * because the time complexity should be the same in terms of O()
     * is that because of assigning values and branches ?
     */
    public int faster() {
        int count = 0;
        for(int start=0; start < nums.length; start++) {
            int sum = 0;
            for(int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    /*
     * this a solution most people use and it is O(n)
     */
    public int moreFaster() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int cnt = 0;
        for(int num : nums) {
            sum += num;
            if(map.containsKey(sum - k))
                cnt += map.get(sum - k);
            int tmp = map.getOrDefault(sum, 0) + 1;
            map.put(sum, tmp);
        }
        return cnt;
    }
}
