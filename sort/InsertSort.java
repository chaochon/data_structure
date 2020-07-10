package sort;

public class InsertSort {

    public static void main(String[] args){
        // nums[0] 为哨兵
        int[] nums = new int[]{0, 38, 49, 65, 97, 76, 13, 27, 49};


        System.out.print("original nums: ");
        print(nums);
        System.out.print('\n');

//        simpleInsertSort(nums);
//        BinaryInsertSort(nums);
        shellInsertSort(nums, new int[]{3, 2, 1});


        System.out.print("  sorted nums: ");
        print(nums);

    }

    // 简单插入排序
    public  static void simpleInsertSort(int[] nums){

        for(int i=2; i<nums.length; i++){
            if(nums[i]<nums[i-1]){
                nums[0] = nums[i];
                nums[i] = nums[i-1];

                int j=i-2;
                while(nums[j]>nums[0]) {
                    nums[j + 1] = nums[j];
                    j--;
                }
                nums[j+1] = nums[0];
            }
        }
    }

    // 折半插入排序
    public static void BinaryInsertSort(int[] n){

        for(int i=2; i<n.length; i++){
            if(n[i]<n[i-1]){
                n[0] = n[i];
                int low = 1, high = i-1;
                while(low<=high){
                    int mid = (low+high)/2;
                    if(n[0]<n[mid])
                        high = mid-1;
                    else
                        low = mid+1;
                }
                // 循环移位
                for(int j=i-1; j>=high+1; j--)
                    n[j+1] = n[j];
                // 插入检查值
                n[high+1] = n[0];
            }
        }
    }

    // 希尔排序
    public static void shell(int[] n, int dk){
        for(int i=1+dk; i<n.length; i++){
            if(n[i]<n[i-dk]){
                n[0] = n[i];
                int j=0;
                for(j=i-dk; j>0&&n[j]>n[0]; j=j-dk)
                    n[j+dk] = n[j];
                n[j+dk] = n[0];
            }
        }
    }

    public static void shellInsertSort(int[] n, int[] dks){

        // 增量序列
        for(int d: dks){
            shell(n, d);
        }

    }


    // print
    public static void print(int[] n){
        for(int num: n){
            System.out.print(num+" ");
        }
    }


}
