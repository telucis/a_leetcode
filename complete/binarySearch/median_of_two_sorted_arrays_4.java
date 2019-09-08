package complete.binarySearch;

/**
 * @author karl.wy
 * @date 2019/05/30
 *
 * 寻找两个有序数组的中位数
 *
    给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

    请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

    你可以假设 nums1 和 nums2 不会同时为空。

    示例 1:

    nums1 = [1, 3]
    nums2 = [2]

    则中位数是 2.0
    示例 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    则中位数是 (2 + 3)/2 = 2.5

 */
public class median_of_two_sorted_arrays_4 {

    /**
     * binarySearch
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length+nums2.length;
        if (n%2==0) {
            return (findKth(nums1, nums2, n/2) + findKth(nums1, nums2, n/2+1))/2.0;
        }
        return findKth(nums1, nums2, n/2+1);
    }
    private int findKth(int[] A, int[] B, int k) {
        if (A.length==0) return B[k-1];
        if (B.length==0) return A[k-1];

        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length-1], B[B.length-1]);

        while (start+1<end) {
            int mid = start+(end-start)/2;
            System.out.println(mid);
            if (countSmallerOrEqual(A, mid)+countSmallerOrEqual(B, mid)<k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k) {
            return start;
        }
        return end;
    }
    private int countSmallerOrEqual(int[] arr, int number) {
        int start = 0, end = arr.length-1;
        while (start+1<end) {
            int mid = start+(end-start)/2;
            if (arr[mid]<=number) start=mid;
            else end=mid;
        }
        if (arr[start]>number) return start;
        if (arr[end]>number) return end;
        return arr.length;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0)
            return (nums2.length % 2 == 1)?nums2[nums2.length / 2]:((nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0);
        if (nums2.length == 0)
            return (nums1.length % 2 == 1)?nums1[nums1.length / 2]:((nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0);
        int len = nums1.length + nums2.length;
        if(nums1.length>nums2.length)
        {
            int[] tmp=nums1;
            nums1=nums2;
            nums2=tmp;
        }
        int low=0,high=nums1.length-1,m1=0,n1=0,max_left=0,min_right=0;
        while(true)
        {
            m1=(low>high)?-1:((low+high)/2);
            n1=(len%2==0)?(len/2-2-m1):((len-1)/2-2-m1);
            max_left=Math.max(m1<0?nums2[n1]:nums1[m1],n1<0?nums1[m1]:nums2[n1]);
            min_right=Math.min(m1+1>=nums1.length?nums2[n1+1]:nums1[m1+1],n1+1>=nums2.length?nums1[m1+1]:nums2[n1+1]);
            if(max_left<=min_right)
                return (len%2==0)?((max_left+min_right)/2.0):min_right;
            if((m1<0 && nums1[0]>min_right)||(m1>=0 && nums1[m1]>min_right))
                high=m1-1;
            if((m1<0 && nums1[0]<=min_right)||(m1>=0 && nums1[m1]<=min_right))
                low=m1+1;
        }
    }

    /**
     * divide conquer
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length+nums2.length;
        if (n%2==0) {
            return (findK(nums1, 0, nums2, 0, n/2)+
                    findK(nums1, 0, nums2, 0, n/2+1))/2;
        }
        return findK(nums1, 0, nums2, 0, n/2+1);
    }
    private int findK(int[] A, int startA, int[] B, int startB, int k) {
        if (startA>=A.length) return B[startB+k-1];
        if (startB>=B.length) return A[startA+k-1];
        if (k==1) return Math.min(A[startA], B[startB]);

        int halfKthOfA = startA+k/2-1 < A.length ? A[startA+k/2-1] : Integer.MAX_VALUE;
        int halfKthOfB = startB+k/2-1 < B.length ? B[startB+k/2-1] : Integer.MAX_VALUE;

        if (halfKthOfA<halfKthOfB) {
            return findK(A, startA+k/2, B, startB, k-k/2);
        } else {
            return findK(A, startA, B, startB+k/2, k-k/2);
        }
    }
}
