import java.util.*;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}



public class tmp {

    public static void main(String[] argv){

        tmp t = new tmp();
        boolean[] temp = new boolean[0];
        temp[0] = false;
        int res = t.LastRemaining_Solution(6, 6);

        System.out.println(res);
    }

    public int LastRemaining_Solution(int n, int m) {

        boolean[] out =  new boolean[n];
        int counter = 0;
        int index = 0;
        while(true){

            if(!out[index])
                counter++;

            if(counter==m){
                out[index] = true;
                if(isOne(out))
                    break;
                counter = 0;
            }

            while(out[(index+1)%n])
                index = (index+1)%n;
            index = (index+1)%n;
        }

        for(int i=0; i<out.length; i++){
            if(!out[i])
                return i;
        }
        return 0;
    }

    boolean isOne(boolean[] out){
        int len = out.length;
        int cnt = 0;
        for(int i=0; i<len; i++){
            if(out[i])
                cnt++;
        }
        if(cnt==len-1)
            return true;
        else
            return false;
    }

}