public class Solution {

    public static void main(String[] argv){


        String str = "10*0";

        try{
            int num = Integer.parseInt(str);
        }catch (NumberFormatException e ){
            System.out.println("不是数字");
        }
        System.out.println("是数字");
    }


}