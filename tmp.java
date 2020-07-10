import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class tmp{
    public static void main(String[] argv){
        int num = 0;
        Scanner in = new Scanner(System.in);

        if(in.hasNextInt()){
            num = in.nextInt();
        }
        int[] nums = new int[num];
        for(int i=0; i<num; i++){
            if(in.hasNextInt())
                nums[i] = in.nextInt();
        }
        int[] dp = new int[nums.length];
        for(int i=0; i<dp.length; i++){
            dp[i] = 1;
        }
        int max = 0;
        for(int i=1; i<dp.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i]>nums[j]&&(dp[j]+1>dp[i])){
                    dp[i] = dp[j]+1;
                }
            }
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}