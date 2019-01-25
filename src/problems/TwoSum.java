package problems;

import java.util.*;

public class TwoSum {
    private int[] nums;
    private int target;

    public TwoSum(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
    }

    /*
     * solution for original problem, where there are only one result, numbers cannot be used more than once
     */
    public int[] original() {
        if(nums == null || nums.length <= 1)
            return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            int rest = target - num;
            if(map.containsKey(num)) {
                return new int[]{map.get(num), i};
            }
            map.put(rest, i);
        }
        return null;
    }

    /*
     * some possible follow-up questions
     * more than one result: (assume there are no duplicate numbers)
     * the solution should be basically the same with original one,
     * simply use a list to store results
     * duplicate numbers:
     * there can be two return values: return the number of distinct pair, return a list of distinct pair
     * solution is given below
     */
    public List<int[]> duplicate() {
        if(nums == null || nums.length <= 1)
            return null;
        Map<Integer, Integer> map = new HashMap<>();    // key: number, value: count
        List<int[]> res = new ArrayList<>();
        for(int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            map.put(num, cnt);
        }
        // half of target
        if(target % 2 == 0 && map.containsKey(target/2)) {
            if(map.get(target/2) >= 2)
                res.add(new int[]{target/2, target/2});
            map.remove(target/2);
        }
//        // this is the wrong way to do such operation
//        // remove key while using foreach loop on keyset will cause concurrent modification exception
//        for(Integer key : map.keySet()) {
//            int rest = target - key;
//
//        }
        for(int num : nums) {
            int rest = target - num;
            if(map.containsKey(rest)) {
                res.add(new int[]{num, rest});
                map.remove(num);
                map.remove(rest);
            }
        }
        return res;
    }

    /*
     * input array is sorted
     * there can be duplicate numbers
     */
    public int[] sortedInput() {
        Arrays.sort(nums);
        if(nums == null || nums.length <= 1)
            return null;
        int lo = 0, hi = nums.length-1;
        while(lo < hi) {
            int sum = nums[lo] + nums[hi];
            if(sum == target)
                return new int[]{lo, hi};
            else if(sum < target) {
                while(lo < hi && nums[lo] == nums[lo+1])
                    lo++;
            }
            else {
                while(lo < hi && nums[hi] == nums[hi-1])
                    hi--;
            }
            lo++;
            hi--;
        }
        return null;
    }

    /*
     * input is a BST
     */
    public boolean bst() {
        // check leetcode 653 for the code
        return false;
    }
}
