package heap;

/**
 *  set i 为 某个节点
 *  parent:          (i-1)/2
 *  left_child:      i*2+1
 *  right_child:     i*2+2
 *  最后一个非叶子节点  (len-2)/2
 */

public class heapSortTest {

    public static void main(String[] argv){

        int[] nums = {4, 10, 3, 11, 1, 2, 1, 2, 3, 1111,1,1,1,1,1,1,1};
        heapSort(nums);
        for(var n:nums){
            System.out.print(n+" ");
        }

    }
    // 堆排序
    public static void heapSort(int[] nums){
        buildHeap(nums);
        for(int i=nums.length; i>0; i--){
            int temp = nums[i-1];
            nums[i-1] = nums[0];
            nums[0] = temp;
            heapify(nums, 0, i-1);
        }
    }
    // 构建堆
    public static void buildHeap(int[] nums){
        int n = nums.length;
        for(int i=(n-2)/2;i>=0; i--){
            heapify(nums, i, n);
        }
    }
    // 黑皮费
    public static void heapify(int[] nums, int i, int len){

        // 节点i越过最后一个非叶子节点
        if(i>(len-2)/2){
            return;
        }

        // 将最大值，交换至节点i
        int max = i;
        int left = i*2+1;
        int right = i*2+2;
        if(left<len&&nums[left]>nums[max]){
            max = left;
        }
        if(right<len&&nums[right]>nums[max]){
            max = right;
        }
        if(max!=i){
            int temp = nums[max];
            nums[max] = nums[i];
            nums[i] = temp;

            heapify(nums, max, len);
        }
    }

}
