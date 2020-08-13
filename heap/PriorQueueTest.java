package heap;

import java.util.PriorityQueue;

public class PriorQueueTest {

    public static void main(String[] argv) {

        int[] nums = {4, 5, 3, 2};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0; i<nums.length; i++){
            queue.add(nums[i]);
        }

        // 每次给最小值增加x
        int x = 10;
        for(int i=0; i<2; i++){
            int temp = queue.poll();
            temp += x;
            queue.offer(temp);
        }
        for(var e: queue){
            System.out.print(e+ " ");
        }
    }
}
