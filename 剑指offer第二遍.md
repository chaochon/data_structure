

# 1.剪绳子

```java
public class Solution {
    public int cutRope(int target) {
        int[] memory = new int[target+1];
        return dfs(target, memory);
    }
    
    public int dfs(int n, int[] m){
        if(n==2){
            return 1;
        }
        // 判断记忆字典
        if(m[n]!=0){
            return m[n];
        }
        // 搜索本层最大剪绳子结果
        int res = -1;
        for(int i=1; i<=n-2; i++){
            res = Math.max(res, Math.max(i*(n-i), i*dfs(n-i, m)));
        }
        
        m[n] = res;  // 记录本层的结果
        return res;
    }
}
```

https://blog.csdn.net/achong_2050/article/details/105955658?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522159525002119724835826545%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=159525002119724835826545&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v1~rank_blog_v1-1-105955658.pc_v1_rank_blog_v1&utm_term=%E5%89%AA%E7%BB%B3%E5%AD%90



# 2.机器人运动范围

## 思路

1. 使用dfs深度优先搜索所有可达路径
2. 判断当前坐标数位和是否比target值要大，如果小于等于target则让solution的字段res++
3. 注意矩阵的搜索与二叉树搜索的区别，矩阵的dfs，需要使用used来记录走过的单元格；二叉树则不需要，因为二叉树没有重复的节点。

## 代码

```java
public class Solution {

    public static void main(String[] argv){

        Solution s = new Solution();
        int res = s.movingCount(3, 4, 4 );
        System.out.println(res);
    }
    private int res = 0;
    public int movingCount(int threshold, int rows, int cols)
    {
        boolean[][] used = new boolean[rows][cols];
        dfs(rows, cols, 0, 0, threshold, used);
        return res;
    }

    public void dfs(int row, int col, int i, int j, int thres, boolean[][] used){
        if(i>=row||j>=col||(calDigtalSum(i, j)>thres)){
            return;
        }
        if(!used[i][j]) {
            res++;
            used[i][j] = true;
            dfs(row, col, i + 1, j, thres, used);
            dfs(row, col, i, j + 1, thres, used);
        }
    }

    public int calDigtalSum(int i, int j){
        int sum = 0;
        while(i!=0){
            sum += i%10;
            i /= 10;
        }
        while(j!=0){
            sum += j%10;
            j /= 10;
        }
        return sum;
    }
}
```

# 3.矩阵中的路径

## 思路

dfs深度优先搜索

## 代码

```java
public class Solution {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        boolean res = false;
        boolean[][] used = new boolean[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                res = dfs(matrix, rows, cols, i, j, 0, str.length, str, used);
                if(res)
                    break;
            }
            if(res){
                break;
            }
        }
        return res;
    }

    public boolean dfs(char[] matrix, int row, int col, int i, int j, int k, int len, char[] str, boolean[][] used){
        if(i<0||i>=row||j<0||j>=col||str[k]!=matrix[i*col+j]||used[i][j]){
            return false;
        }
        if((k==len-1)&&(str[k]==matrix[i*col+j]))
            return true;

        used[i][j] = true;

        boolean up = dfs(matrix, row, col, i-1, j, k+1, len, str, used);
        boolean down = dfs(matrix, row, col, i+1, j, k+1, len, str, used);
        boolean left = dfs(matrix, row, col, i, j-1, k+1, len, str, used);
        boolean right = dfs(matrix, row, col, i, j+1, k+1, len, str, used);

        used[i][j] = false;

        return up||down||left||right;
    }
}
```

# 3.相邻数值不等于1

## 3.1题目

题目：有n个物品编号为1-n,现将其重新排列,但要求相邻两物品的编号差值的绝对值不等于1，按字典输出所有方案。
例如：输入n=4，输出的方案有：{2,4,1,3},{3,1,4,2}。

## 3.2思路

1. 使用dfs搜索所有排列，由于题目给定数值各不相同，所有不需要去重
2. 比较麻烦的在于如何剪纸，去掉相邻数值为1的情况

## 3.3代码

```java
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] argv){

        Solution s = new Solution();

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();


        int[] nums = new int[]{1, 2, 3, 4};
        boolean[] used = new boolean[nums.length];

        s.dfs(nums, nums.length, 0, used, path, res);

        for(List r:res){
            for(Object rr:r){
                System.out.print((int)(rr)+" ");
            }
            System.out.println();
        }
    }

    public void dfs(int[] nums, int len, int i, boolean[] used, List<Integer> path, List<List<Integer>> res){

        if(i==len){
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for(int k=0; k<len; k++){
            if(!used[k]){
				// 剪纸,去掉相邻数值为1的情况
                if(!path.isEmpty()&&Math.abs(nums[k]-path.get(path.size()-1))==1){
                    continue;
                }

                used[k] = true;
                path.add(nums[k]);
                dfs(nums, len ,i+1,  used, path, res);
                used[k] = false;
                path.remove(path.size()-1);
            }
        }

    }
}
```

# 4.滑动窗口的最大值

## 4.1 单调双端队列

代码

```java
import java.util.*;

public class Solution {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();  // 记录结果
        Deque<Integer> deque = new ArrayDeque<>();   // 单调双端队列
        if(size==0||num.length<size)
            return res;
        // 搜索第一个滑动窗口
        for (int i = 0; i < size; i++) {
            if ((!deque.isEmpty()) && num[i] > num[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        res.add(num[deque.getFirst()]);
        // 每个循环向右滑动一个单元
        for (int i = size; i < num.length; i++) {

            // 判断当前最大值是否任然在滑动窗口的区间
            if ((!deque.isEmpty()) && deque.getFirst() <= (i - size)) {
                deque.removeFirst();
            }
            // 维护单调双端队列
            while ((!deque.isEmpty()) && num[i] >= num[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            res.add(num[deque.getFirst()]);
        }
        return res;
    }
}
```

# 5. 二叉树的下一个节点

## 5.1思路

![image-20200726120514788](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200726120514788.png)

主要分成两种情况：

1. 当前节点存在右子树：此时 只需要找出其右子树的最左边的节点
2. 当前节点不存在右子树：首先判断当前节点是否存在父节点，如在，判断当前节点是否为父节点的左节点，如果是，则其父节点即为下一个节点，如不是则，继续向上寻找。

## 5.2代码

```java
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode==null)
            return null;
        // 判断是否存在右子树
        if(pNode.right!=null){
            pNode = pNode.right;
            while(pNode.left!=null){
                pNode = pNode.left;
            }
            return pNode;
        }else{
            // 判断是否存在父节点
            while(pNode.next!=null){
                // 找出当前节点为父节点的左子树的情况
                if(pNode.next.left==pNode)
                    return pNode.next;
                pNode = pNode.next;
            }
            return null;
        }
    }
}
```

# 6.删除链表中重复节点

```java
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public ListNode deleteDuplication(ListNode pHead)
    {
        // 只有一个节点的情况
        if(pHead==null||pHead.next==null){
            return pHead;
        }
        // 给链表添加一个额外节点
        ListNode temp = new ListNode(0);
        temp.next = pHead;
        pHead = temp;
        
        ListNode pre = pHead;  // 前一个节点
        ListNode cur = pHead.next;  // 当前节点
        while(cur!=null){
            int count = 0;
            // 判断当前节点与后一个节点是否相等
            while(cur.next!=null&&cur.val==cur.next.val){
                cur = cur.next;
                count++;
            }
            // 出现重复节点的情况
            if(count>0){
                pre.next = cur.next;
                cur = cur.next;
            // 没有出现重复节点的情况
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return pHead.next;
    }
}
```

# 7.构建乘积数组

### 代码

```java
import java.util.ArrayList;
public class Solution {
    public int[] multiply(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        res[0] = 1;
        // 填下三角
        for(int i=1; i<len; i++){
            res[i] = res[i-1]*A[i-1];
        }
        // 填上三角
        int temp = 1;
        for(int i = len-2; i>=0; i--){
            temp *= A[i+1];
            res[i] *= temp;
        }
        return res;
    }
}
```

# 8.把字符串转换成整数

```java
public class Solution {
    public int StrToInt(String str) {

        // 判断是否为null
        if(str==null)
            return 0;

        char[] strs = str.toCharArray();
        int len = strs.length;

        // 跳过开头的空字符
        int startIndex = 0;
        for(int i=0; i<len; i++){
            if(strs[i]==' '){
                startIndex++;
            }else{
                break;
            }
        }

        // 空串
        if(startIndex==len)
            return 0;

        // sign 0 1表示正数， -1表示负数
        int sign = 0;
        if(strs[startIndex] == '+') {
            sign = 1;
        }
        else if(strs[startIndex] == '-') {
            sign = -1;
        }
        if(sign>0||sign<0){
            startIndex++;
        }

        // 迭代其后每个字符
        int num = 0;
        for(int i=startIndex; i<len; i++){

            // 判断第一个字符是否为0
            if(i==startIndex&&strs[i]=='0'){
                return 0;
            }

            if(strs[i]>='0'&&strs[i]<='9'){
                num = 10*num+(strs[i]-'0');
            }else {
                return 0;
            }
        }
        // 输出
        if((sign==-1&&-1*num>=Integer.MIN_VALUE)||((sign==1||sign==0)&&(num<=Integer.MAX_VALUE))){
            if(sign<0){
                return -1*num;
            }else{
                return num;
            }
        }else{
            return 0;
        }
    }
}
```

# 9.和为S的连续正数序列

## 9.1 枚举法

```java
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int num = sum/2;
        for(int i=1; i<=num; i++){
            int start = i;  // 记录开始数值
            int n = i;      // 增长的元素值
            int temp = sum; // 求和值
            while(temp>0){
                temp -= n++;
            }
            if(temp==0){
                // 将连续正数序列写入结果中
                ArrayList<Integer> element = new ArrayList<>();
                for(int ii=start; ii<n; ii++){
                    element.add(ii);
                }
                res.add(element);
            }
        }
        return res;
    }
```

## 9.2双指针

**思路**

1. 由于是连续正整数，所以可以使用等差数列求和$S_N=(a_1+a_n)*(n/2)$
2. 从[1, 2]区间开始搜索，采用双指针，如果区间内数值等于目标值，则将结果添加到输出；小于目标值，则右端点右移(缩小$S_N$)；如果大于目标值，则左端点右移（放大$S_N$）

**代码**

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        int begin = 1;
        int end = 2;
        while(begin<end){
            int cur = (begin+end)*(end-begin+1)/2;
            // 前n项和等于目标值
            if(cur==sum){
                ArrayList<Integer> r = new ArrayList<>();
                for(int i=begin; i<=end; i++){
                    r.add(i);
                }
                res.add(r);
                begin++;
            // 前n项和小于目标值，右端点右移，增大前n项和
            }else if(cur<sum){
                end++;
            // 前n项和大于目标值，左短点左移，缩小前n项和
            }else{
                begin++;
            }
        }
        return res;
    }
}
```

# 10 数组中只出现一次的数字

```java
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        
        int temp = 0;
        for(int i=0; i<array.length; i++){
            temp ^= array[i];
        }
        
        int index = findBit1(temp);
        
        for(int i=0; i<array.length; i++){
            if(isBit1(array[i], index)){
                num1[0] ^= array[i];
            }else{
                num2[0] ^= array[i];
            }
        }
    }
    
    public int findBit1(int temp){
        int index = 0;
        while((temp&1)==0){
            index++;
            temp >>= 1;
        }
        return index;
    }
    
    public boolean isBit1(int num, int index){
        if(((num>>index)&1)==1)
            return true;
        else
            return false;
    }
}
```

# 11.平衡二叉树

**思路**

1. 采用自顶向下的递归，首先判断当前树的左子树和右子树的深度之差是否大于1，如果在1范围之内，则递归地分别判断其左子树、右子树是否为平衡二叉树

**求解一个树的深度的思路**：自底向上地递归 回溯。

- 返回条件，`root==null`越过叶子节点时，返回0
- 递归

$$
max(depth(root.left), depth(root.right))+1
$$

**代码**

```java
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root==null){
            return true;
        }
        // 自顶上下
        if(Math.abs(depth(root.left)-depth(root.right))>1){
            return false;
        }
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
    }
    
    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        // 自底向上 回溯
        return Math.max(depth(root.left), depth(root.right))+1;
    }
}
```

# 12.数字在排序数组中出现的次数

## 12.1 插入法

思路：找出较目标值稍大一点的位置，找出较目标值稍小一点的位置。两个位置之差即为结果。

```java
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
       return binarySearch(array, k+0.5)-binarySearch(array, k-0.5);
    }
    // 二分查找
    // 查找给定值的下标，如果没有给定值，则表示要插入的位置
    public int binarySearch(int[] nums, double num){
        int begin = 0;
        int end = nums.length-1;
        while(begin<=end){
            int mid = (end-begin)/2+begin;
            if(nums[mid]<num){
                begin = mid+1;
            }else if(nums[mid]>num){
                end = mid-1;
            }else{
                end = mid-1;
            }
        }
        return end;
    }
}
```
## 12.2 边界法

思路： 找出目标值的左、右边界，两者之差-1即为结果（左边界：稍小于目标值，右边界：稍大于目标值）

**要掌握的是求左、右边界的函数**

```java
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
       return binarySearchRight(array, k)-binarySearchLeft(array, k)-1;
    }
    
    public int binarySearchLeft(int[] nums, int num){
        int begin = 0;
        int end = nums.length-1;
        while(begin<=end){
            int mid = (end-begin)/2+begin;
            if(nums[mid]<num){
                begin = mid+1;
            }else if(nums[mid]>num){
                end = mid-1;
            }else{
                end = mid-1;
            }
        }
        return end;
    }
    
        public int binarySearchRight(int[] nums, int num){
        int begin = 0;
        int end = nums.length-1;
        while(begin<=end){
            int mid = (end-begin)/2+begin;
            if(nums[mid]<num){
                begin = mid+1;
            }else if(nums[mid]>num){
                end = mid-1;
            }else{
                begin = mid+1;
            }
        }
        return begin;
    }
}
```

# 13归并两个排序的链表

```java
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }
        // 创建一个辅助头节点
        ListNode temp = new ListNode(0);
        ListNode head = temp;
        // 依次将两个链表中较小值，加入归并列表中
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                temp.next = list1;
                list1 = list1.next;
            }else{
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        // 将剩余的一个非空链表加入归并链表中
        if(list1!=null)
            temp.next = list1;
        if(list2!=null)
            temp.next = list2;
        return head.next;
    }
}
```

# 14.树的子结构

**思路：**

1. 写一个递归函数recur用来判断左右两棵树，右树是左树的部分或者全部。
2. 使用先序遍历或者称自顶向下的递推，遍历以每个节点为子树，用recur判断是否满足**相等**关系

```java
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        
        if(root1==null||root2==null)
            return false;
        
        return recur(root1, root2)||HasSubtree(root1.left, root2)||HasSubtree(root1.right, root2);
    }
    
    // 判断node2是否是node1的一部分
    public boolean recur(TreeNode node1, TreeNode node2){
        if(node2==null)
            return true;  // 说明node2子树是node1子树的一部分
        if(node1==null||(node1.val!=node2.val))
            return false;  // 说明node2不是node1树的局部
        return recur(node1.left, node2.left)&&recur(node1.right, node2.right);
    }
}
```

# 15.二叉树镜像

思路：自底向上 回溯递归构造二叉树镜像

- 递归条件 `root==null`越过叶子节点则返回null
- 递推：先找出 左、右子树的镜像子树，在本层递归函数中交换左、右子树

```java
public class Solution {
    public void Mirror(TreeNode root) {
        recur(root);
    }
    public TreeNode recur(TreeNode node){
        if(node==null)
            return null;
        // 自顶向上地构造镜像二叉树
        TreeNode leftNode = recur(node.right);
        TreeNode rightNode = recur(node.left);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }
}
```

# 16 顺时针打印矩阵

输入一个矩阵，按照从外向里，顺时针打印每个数值。

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        
       ArrayList<Integer> res = new ArrayList<Integer>();
       int row = matrix.length;
       if(row==0)
           return res;
       int col = matrix[0].length;
       
       int top = 0, bottom = row-1, left = 0, right = col-1;
       while( top<=bottom && left<=right ){
           // 打印顶行
           for(int i=left; (top<=bottom && left<=right)&&i<=right; i++){
               res.add(matrix[top][i]);
           }
           top++;
           // 打印最右列
           for(int i=top; (top<=bottom && left<=right)&&i<=bottom; i++){
               res.add(matrix[i][right]);
           }
           right--;
           // 打印最底行
           for(int i=right; (top<=bottom && left<=right)&&i>=left; i--){
               res.add(matrix[bottom][i]);
           }
           bottom--;
           // 打印最左列
           for(int i=bottom; (top<=bottom && left<=right)&&i>=top; i--){
               res.add(matrix[i][left]);
           }
           left++;
       }
      return res;
    }
}
```

# 17 二叉搜索树的后序遍历序列

**思路：** 采用自顶向下的递归，分治。首先判断当前区间(i, j)数组是否满足二叉搜索树定义的必要条件，再判断对左、右子树进行递归地分治，判断之。

![image-20200811220718020](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200811220718020.png)

**代码**

```java
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        
        if(sequence.length==0)
            return false;
        return recur(sequence, 0, sequence.length-1);
        
    }
    
    public boolean recur(int[] nums, int i, int j){
        if(i>=j)
            return true;
        int iter=0, mid=0;
        // 先找出左子树、右子树的划分边界（中间值mid）
        while(nums[iter]<nums[j])
            iter++;
        mid = iter;
        // 判断右子树是否满足大于根节点的必要条件
        while(iter<=j&&nums[iter]>nums[j])
            iter++;
        boolean res = false;
        if(j==iter)
            res = true;
        return res&&recur(nums, i, mid-1)&&recur(nums, mid, j-1);
    }
}
```

# 18 二叉树中和为某一个值的路径

![image-20200812112420233](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812112420233.png)

```java
public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        dfs(root, target, path, res);
        return res;
    }
    
    public void dfs(TreeNode root, int curValue, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res){
        // 越过叶子节点直接返回
        if(root==null){
            return;
        }
        // 当前函数的操作的定义是：
        curValue = curValue - root.val;
        path.add(root.val);
        
        // 判断当前节点为叶子节点，并且路径和等于target
        if(root.left==null&&root.right==null&&curValue==0){
            res.add(new ArrayList<>(path));
        }
        // 先序遍历
        dfs(root.left, curValue, path, res);
        dfs(root.right, curValue, path, res);
        
        // 恢复现场
        path.remove(path.size()-1);
    }
}
```

# 19 复杂链表的复制

![image-20200812145401644](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812145401644.png)

## 19.1 映射法

**思路：** 使用map， 创建一个由原链表各个节点->复制的新链表的各个节点的映射。利用这个映射来建立每个节点之间的关联关系。

```java
import java.util.HashMap;

public class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        
        RandomListNode cur = pHead;
        // 创建深拷贝的节点，并保存新旧节点的映射
        while(cur!=null){
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        // 拷贝链表建立关联关系
        cur = pHead;
        while(cur!=null){
            // 建立next的关系
            map.get(cur).next = map.get(cur.next);
            // 建立random的关联关系
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(pHead);
    }
}
```

## 19.2 原地操作

**思路：**将问题的求解分成三个步骤

1. 复制链表，在每个节点后面拷贝一个新的节点，并且保持前后连接关系
2. 关联random关系
3. 差分链表，从大的链表中拆分出克隆的链表

```java
public class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        // 复制链表
        copyNodes(pHead);
        // 复制random关联关系
        randomCopy(pHead);
        // 拆分链表
        return reListBuild(pHead);
    }


    /**
     * 为每个节点紧接着复制一个相同的节点
     * @param cur
     */
    public void copyNodes(RandomListNode cur){
        while(cur!=null){
            RandomListNode node = new RandomListNode(cur.label);
            RandomListNode temp = cur.next;
            cur.next = node;
            node.next = temp;
            cur = temp;
        }
    }
    /*
    复制原链表的random关联关系
     */
    public void randomCopy(RandomListNode cur){
        while(cur!=null){
            RandomListNode curB = cur.next;
            curB.random = cur.random!=null?cur.random.next:null;
            cur = curB.next;
        }
    }
    
    /*
    原链表-拷贝链表进行差分
     */
    public RandomListNode reListBuild(RandomListNode cur){
        if(cur==null)
            return null;
        RandomListNode res = cur.next;
        while(cur!=null){
            
            RandomListNode curB = cur.next;
            RandomListNode temp = curB.next;
            
            cur.next = temp;
            curB.next = temp!=null?temp.next:null;
            
            cur = temp;
        }
        return res;
    }
}
```

# 20 二叉搜索树与双向链表

![image-20200812170703861](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812170703861.png)

**思路：**中序遍历二叉搜索树的结果就是递增序列，维护一个全局变量pre保持前一个节点，用于前节点与当前节点建立前驱和后继。head用于寻找、保存首节点的引用。

```java
public class Solution {
    
    public TreeNode head, pre;
    
    public TreeNode Convert(TreeNode pRootOfTree) {
        dfs(pRootOfTree);
        return head;
    }
    
    public void dfs(TreeNode node){
        if(node==null)
            return;
        dfs(node.left);
        
        // ---对前一个节点和当前节点建立前驱和后继---
        if(pre!=null){
            pre.right = node;
        }else{
            head = node;
        }
        node.left = pre;
        pre = node; // 更新前一个节点
        // ---------
        dfs(node.right);
    }
}
```

# 21. 字符串的排序

![image-20200812170518605](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812170518605.png)

**思路：**

1. 字符串转数组
2. 对字符串数组进行排序
3. 带有去除判断的暴力变量

```java
import java.util.ArrayList;
import java.util.Arrays;
public class Solution {
    public ArrayList<String> Permutation(String str) {

        char[] chars = str.toCharArray();

        ArrayList<String> res = new ArrayList<>();
        if(chars.length==0)
            return res;
        StringBuilder path = new StringBuilder();
        boolean[] used = new boolean[chars.length];

        Arrays.sort(chars);

        dfs(chars, 0, str.length(), path, used, res);
        return res;
    }

    public static void dfs(char[] chars, int i, int len, StringBuilder path, boolean[] used, ArrayList<String> res){

        if(i>=len){
            res.add(path.toString());
        }

        for(int ii=0; ii<chars.length; ii++){
            if(!used[ii]){
                if(ii>0&&used[ii-1]==false&&chars[ii]==chars[ii-1])
                    continue;

                path.append(chars[ii]);
                used[ii] = true;

                dfs(chars, i+1, len, path, used, res);

                path.deleteCharAt(path.length()-1);
                used[ii] = false;
            }
        }
    }
}
```

# 22.数组中出现次数超过一半的数字

![image-20200812194842643](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812194842643.png)

## 22.1 排序法（时间复杂度nlogn)

**思路：** 先排序，在取中间值

```java
import java.util.Arrays;

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        Arrays.sort(array);
        int len = array.length;
        if(len==0)
            return 0;
        int midValue = 0;
        int mid = 0;
        if(len%2==0){
            mid = len/2-1;
            midValue = array[mid];
        }else{
            mid = len/2;
            midValue = array[mid];
        }
        
        int cnt = 0;
        // 往后计数
        int p1 = mid;
        while(p1<len&&array[p1]==midValue){
            cnt++;
            p1++;
        }
        // 往前计数
        p1 = mid-1;
        while(p1>=0&&array[p1]==midValue){
            cnt++;
            p1--;
        }
        if(cnt>len/2){
            return midValue;
        }else{
            return 0;
        }
    }
}
```

## 22.2 摩尔投票法（时间复杂度n)

**思路** 相互抵消

1. 定义了两个变量，major: 假定当前众数，count：计数出现的次数
2. 一个重要的规则，如果当前major，与当前值不等，则相互抵消；如果相等，count加1。
3. 找出众数后，再判断其出现的次数是否大于数组长度的一半

通过这种方法似乎可以从一个数组中找出其**众数**

参考链接：https://www.zhihu.com/question/49973163

```java
import java.util.Arrays;

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int len = array.length;
        int count = 0, major=0;
        for(int i=0; i<len; i++){
            if(count==0){
                major = array[i];
            }
            if(major==array[i]){
                count++;
            }else{
                count--;
            }
        }
        // 判断众数出现的次数是否大于数组元素的一半
        count = 0;
        for(int i=0; i<len; i++){
            if(array[i]==major){
                count++;
            }
        }
        return count>len/2?major:0;
    }
}
```

# 23整数中1出现的次数（从1到n的整数）

![image-20200812215843573](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200812215843573.png)

```java
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        
        int digit = 1;    // 位数
        int high = n/10;  // 高位
        int low = 0;      // 低位
        int cur = n%10;   // 当前位数值
        int count = 0;    // 累计结果
        while(high!=0||cur!=0){
            // 当前位数在2-9的区间
            if(cur>=2&&cur<=9){
                count += (high+1)*digit;
            // 当前位数为0
            }else if(cur==0){
                count += high*digit;
            // 当前位数为1
            }else if (cur==1){
                count += high*digit+low+1;
            }
            // --移动到下一位数--
            low += cur*digit;
            digit*=10;
            cur = high%10;
            high /=10;
        }
        return count;
    }
}
```

# 24把数组排成最小的数

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        // 转成字符串数组
        String[] numberStr = new String[numbers.length];
        for(int i=0; i<numberStr.length; i++){
            numberStr[i] = String.valueOf(numbers[i]);
        }
        // 排序
        Arrays.sort(numberStr, (String a, String b)->{
            return Integer.parseInt(a+b)-Integer.parseInt(b+a);
        });
            
        // 拼接成一个字符串
        StringBuilder res = new StringBuilder();
        for(int i=0; i<numberStr.length; i++){
            res.append(numberStr[i]);
        }
        return res.toString();
    }
}
```

# 25 丑数

![image-20200813111059736](C:\Users\曹冲\AppData\Roaming\Typora\typora-user-images\image-20200813111059736.png)

## 25.1 小顶堆法
**思路：**
- 存在规律：丑数分别乘以2,3,5。仍然是丑数
- 初始值： 1

步骤：
1. 维护一个小顶堆，用于存储丑数集合
2. 每次从小顶堆中，取出最小的值
3. 然后分别将该值乘以2,3,5。添加小顶堆中
4. 直到找出第index个丑数为止

```java
import java.util.PriorityQueue;

public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index<=0)
            return 0;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        int[] nums = new int[]{2, 3, 5};
        queue.add(1L);
        while(!queue.isEmpty()){
            Long res = 0L;
            res = queue.poll();
            if(--index<=0){
                return res.intValue(); // 注意将返回值，转换为int
            }
            for(int i=0; i<nums.length; i++){
                if(!queue.contains(res*nums[i])){
                    queue.add(res*nums[i]);
                }
            }
        }
        return -1;
    }
}
```

## 25.2 动态规划

```java
import java.util.PriorityQueue;

public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index==0)
            return 0;
        
        int[] dp = new int[index];
        dp[0] = 1;
        int p2=0, p3=0, p5=0;
        
        for(int i=1; i<index; i++){
            dp[i] = Math.min(Math.min(dp[p2]*2, dp[p3]*3), dp[p5]*5);
            if(dp[i]==dp[p2]*2){
                p2++;
            }
            if(dp[i]==dp[p3]*3){
                p3++;
            }
            if(dp[i]==dp[p5]*5){
                p5++;
            }
        }
        return dp[index-1];
    }
}
```



# todo

四道难题：数组中逆序对，正则表达式，表示数值的字符串，序列化二叉树 太难了 放弃了。

