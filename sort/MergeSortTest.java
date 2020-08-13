package sort;

public class MergeSortTest {

    public static void main(String[] args){

        int[] nums = new int[]{38, 49, 65, 97, 76, 13, 27, 49};

        System.out.print("original nums: ");
        print(nums);
        System.out.print('\n');

        MSort(nums, 0, nums.length-1);

        System.out.print("  sorted nums: ");
        print(nums);

    }

    public static void MSort(int[] nums, int s, int e){

        if(s==e){
            return;
        }

        int m = (s+e)/2;
        MSort(nums, s, m);
        MSort(nums, m+1, e);
        merge(nums, s, m, e);

    }

    // 归并函数
    public static void merge(int[] nums, int i, int m, int n){

        int[] temp =  new int[nums.length];
        int t=i;

        int j=m+1, k=i;
        while(i<=m&&j<=n) temp[k++] = (nums[i]<nums[j])?nums[i++]:nums[j++];
        while(i<=m) temp[k++] = nums[i++];
        while(j<=n) temp[k++] = nums[j++];

        for(; t<=n; t++) nums[t] = temp[t];

    }

    // print
    public static void print(int[] n){
        for(int num: n){
            System.out.print(num+" ");
        }
    }


}
