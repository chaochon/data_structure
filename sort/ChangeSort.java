package sort;

public class ChangeSort {

    public static void main(String[] args){

        int[] nums = new int[]{38, 49, 65, 97, 76, 13, 27, 49};


        System.out.print("original nums: ");
        print(nums);
        System.out.print('\n');

//        long startTime = System.nanoTime();
//        bubbleSort(nums);
//        long endTime1 = System.nanoTime();
//        fastSort(nums, 0, nums.length-1);
//        long endTime2 = System.nanoTime();
//
//        System.out.println("bubble sort time:"+(endTime1-startTime));
//        System.out.println("fast sort time:"+(endTime2-endTime1));

//        HeapSort(nums);
        fastSort(nums, 0, nums.length-1);

        System.out.print("  sorted nums: ");
        print(nums);
    }

    // 冒泡排序
    public static void bubbleSort(int[] n){

        for(int i=0; i<n.length-1; i++) {
            boolean isChanged = false;
            for (int j = 0; j < n.length - 1 - i; j++) {
                if (n[j] > n[j + 1]) {
                    isChanged = true;
                    int tmp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = tmp;
                }
            }
            if (!isChanged) break;
        }
    }

    // 快速排序
    public static void fastSort(int[] n, int low, int high){

        if(low>=high)
            return;

        // 自顶向下的递归，排序
        int pivotkey_loc = partition(n, low, high);
        fastSort(n, low, pivotkey_loc-1);
        fastSort(n, pivotkey_loc+1, high);

    }

    public static int partition(int[] n, int low , int high){

        // 默认区间首元素为分割值
        int pivotkey = n[low];

        while(low<high){
            // 扫描右半区间
            while(low<high&&n[high]>=pivotkey) high--;
            n[low] = n[high];

            // 扫描左半区间
            while(low<high&&n[low]<=pivotkey) low++;
            n[high] = n[low];
        }

        // 将分割值放入正确位置
        n[low] = pivotkey;
        return low;
    }


    public static void HeapSort(int[] nums){

        // 构建一个堆
        for(int i = nums.length/2-1; i>=0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        // 堆排序
        for(int j=nums.length-1; j>0; j--){

            int temp = nums[0];
            nums[0] = nums[j];
            nums[j] = temp;

            adjustHeap(nums, 0, j);
        }



    }


    // 整堆函数
    public static void adjustHeap(int[] nums, int i, int length){

        // 假设nums是基本满足堆定义的，除了对顶i需要调整
        int temp = nums[i];
        for(int k = i*2+1; k < length; k = k*2+1){
            if(k+1<length&&nums[k]<nums[k+1])
                k++;
            if(nums[k]>temp){
                nums[i] = nums[k];
                i = k;
            }
            else break;
        }
        nums[i] = temp;
    }





    // print
    public static void print(int[] n){
        for(int num: n){
            System.out.print(num+" ");
        }
    }

}
