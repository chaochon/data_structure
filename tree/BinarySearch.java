package tree;

import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args){

        int[] nums = new int[]{5, 7, 8, 8, 8, 9};
        int target = 8;

        System.out.print("input a num：");
        Scanner in = new Scanner(System.in);

        int key = 0;
        if(in.hasNextInt())
            key = in.nextInt();

        var res = findKey(nums, key);
        if(res!=-1){
            System.out.printf("the index of %d in the nums is %d", key, res);
        }
        else{
            System.out.printf("%d is not in the nums", key);
        }

        // 求左边界
        System.out.printf("the left bound of %d in nums is %d\n", target, findLeftBound(nums, target));


        // 求右边界
        System.out.printf("the right bound of %d in nums is %d: ", target, findRightBound(nums, target));


    }

    private static int findLeftBound(int[] nums, int target){

        int i=0;
        int j=nums.length-1;

        while(i<=j){
            int mid = (i+j)/2;

            if(nums[mid]==target)
                j = mid -1;
            else if(nums[mid]<target)
                i = mid + 1;
            else
                j = mid - 1;
        }
        return j;
    }

    private static int findRightBound(int[] nums, int target){

        int i = 0;
        int j = nums.length-1;

        while( i<= j ){

            int mid = (i+j)/2;

            if(nums[mid]==target)
                i = mid+1;
            else if(nums[mid]<target)
                i = mid+1;
            else
                j = mid - 1;
        }

        return i;
    }


    /**
     * 查询数组中是否存在target值，如果存在则返回其索引，如果不存在在返回-1
     */
    private static int findKey(int[] nums, int target){

        int i=0;
        int j=nums.length-1;

        while(i<=j){
            int mid = i + (j-i)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target){
                i = mid+1;
            }
            else if(nums[mid]>target){
                j = mid-1;
            }
        }
        return -1;
    }





}
