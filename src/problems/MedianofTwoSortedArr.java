package problems;

import java.util.Arrays;

public class MedianofTwoSortedArr {
    /*
     * RT: O(log(min(m, n)))
     */

    private int[] nums1;
    private int[] nums2;

    public MedianofTwoSortedArr(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
    }


    /*
     * this solution is based on binary search
     * it is more like divide the whole numbers into two parts
     * where the left part is smaller than the right part
     * what the fuck is k mean?
     */
    public double solution() {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int k = (len1 + len2) / 2;
        // if the total length is even
        // median will be (nums[k] + nums[k+1]) / 2
        if((len1 + len2) % 2 == 0) {
            double l = helper(nums1, nums2, 0, 0, k);
            double r = helper(nums1, nums2, 0, 0, k+1);
            return (l + r) / 2;
        }
        // otherwise, it will be nums[k]
        // why i wrote k+1 here
        else
            return helper(nums1, nums2, 0, 0, k+1);
    }

    /*
     * s1 and s2 indicate start positions in two arrays
     */
    private double helper(int[] nums1, int[] nums2, int s1, int s2, int k) {
        if(s1 >= nums1.length)
            return nums2[s2 + k - 1];
        if(s2 >= nums2.length)
            return nums1[s1 + k - 1];
        // what does this mean
        if(k == 1)
            return Math.min(nums1[s1], nums2[s2]);

        double mid1 = s1 + k/2 - 1 < nums1.length ? nums1[s1 + k/2 - 1] : Double.MAX_VALUE;
        double mid2 = s2 + k/2 - 1 < nums2.length ? nums2[s2 + k/2 - 1] : Double.MAX_VALUE;
        if(mid1 <= mid2)
            return helper(nums1, nums2, s1 + k/2, s2, k-k/2);
        else
            return helper(nums1, nums2, s1, s2 + k/2, k-k/2);
    }


    /*
     * median is used to divide numbers into two parts with equal length and one part is smaller than the other
     * below is another solution trying to do so
     * we need to ensure m is smaller than n
     * because we need to make sure split is not negative
     * since 0 <= mid <= m, split = (m + n + 1) / 2 - mid
     */
    public double anotherSolution() {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
            m = nums1.length;
            n = nums2.length;
        }
        int len = (m + n + 1) / 2;
        int left = 0, right = m, split;
        double res = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;        // possible left part in the first array
            split = len - mid;                          // possible left part in the second array
            int L1 = mid != 0 ? nums1[mid-1] : Integer.MIN_VALUE;
            int L2 = split != 0 ? nums2[split-1] : Integer.MIN_VALUE;
            int R1 = mid != m ? nums1[mid] : Integer.MAX_VALUE;
            int R2 = split != n ? nums2[split] : Integer.MAX_VALUE;
            // since arrays are sorted, we only need to compare *1 and *2
            // in this case, there should be more numbers involved in the second array, decrease right
            if(L1 > R2) {
                right = mid - 1;
            }
            // in this case, there should be more numbers involved in the first array, increase left
            else if(L2 > R1) {
                left = mid + 1;
            }
            // left part is smaller than right part, compute the median
            else {
                int max = Math.max(L1, L2);
                if((m + n) % 2 != 0)
                    res = max;
                else
                    res = (max + Math.min(R1, R2)) / 2.0;
            }
        }
        return res;
    }
}
