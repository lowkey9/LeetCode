package problems;

import java.util.*;

public class ThreeSum {
    // find out all unique triple set that sum to 0

    private int[] nums;

    public ThreeSum(int[] nums) {
        this.nums = nums;
    }

    public List<List<Integer>> original() {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return res;
        /*
         * since there should not be duplicate triple set in the result
         * but there might be duplicate numbers in the array
         * so it is not appropriate to use solution that is similar to two sum
         */
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++) {
            int left = i+1, right = nums.length-1, target = 0 - nums[i];
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(nums[left] == nums[left-1]) left++;
                    while(nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }
                else if(sum < target)
                    left++;
                else
                    right--;
            }
        }
        return res;
    }

    /*
     * return the sum of a triple set that is closest to target
     * assume there are only one solution
     */
    public int closest(int target) {
        if(nums == null || nums.length <= 2)
            return 0;
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int res = 0;
        for(int i=0; i<nums.length-2; i++) {
            int left = i+1, right = nums.length-1, rest = target - nums[i];
            while(left < right) {
                int sum = nums[left] + nums[right];
                int tmp = rest - sum;
                if(tmp == 0)
                    return target;
                else {
                    if(Math.abs(tmp) < diff) {
                        diff = Math.abs(tmp);
                        res = nums[i] + sum;
                    }
                    if(tmp < 0) {
                        while(nums[left] == nums[left-1]) left++;
                        left++;
                    }
                    if(tmp > 0) {
                        while(nums[right] == nums[right-1]) right--;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    /*
     * find the number of triple set where i < j < k
     * and nums[i] + nums[j] + nums[k] < target
     */
    public int smaller(int target) {
        if(nums == null || nums.length <= 2)
            return 0;

    }
}
