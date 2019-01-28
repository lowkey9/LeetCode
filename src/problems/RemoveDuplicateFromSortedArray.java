package problems;

import java.util.Arrays;

public class RemoveDuplicateFromSortedArray {
    public int[] solution(int[] nums) {
        if(nums == null || nums.length == 0)
            return nums;
        int i = 0, j = 0;
        while(j < nums.length) {
            if(j < nums.length - 1) {
                if(nums[j] != nums[j+1])
                    nums[i++] = nums[j++];
                else
                    j++;
            }
            else {
                if(nums[i] != nums[j])
                    nums[i++] = nums[j];
                else
                    i++;
                j++;
            }
        }
        return Arrays.copyOf(nums, i);
    }


    /*
     * the code can be more concise
     */
    public int[] concise(int[] nums) {
        if(nums == null || nums.length == 0)
            return nums;
        int i = 0;
        for(int j=0; j<nums.length; j++) {
            if(j == 0 || nums[j] != nums[j-1])
                nums[i++] = nums[j];
        }
        return Arrays.copyOf(nums, i);
    }
}
