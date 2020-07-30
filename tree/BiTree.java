package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BiTree {

    public static void main(String[] args){

        BiTree tree = new BiTree();  // 定义一个树对象
        TreeNode tree_root = tree.createTree();  // 创建一个树，并返回root节点

        if(tree_root != null){
            System.out.println("a tree created sucessfully!");
        }

        // 先序遍历 打印输出
        System.out.println("先序遍历:");
        tree.print_preOrder(tree_root);
        System.out.println();
        System.out.println("先序遍历（非递归算法）:");
        tree.preOrderStack(tree_root);
        System.out.println("\n");

        System.out.println("中序遍历：");
        tree.print_interOrder(tree_root);
        System.out.println();
        System.out.println("中序遍历（非递归算法）:");
        tree.interOrderStack(tree_root);
        System.out.println("\n");

        System.out.println("后序遍历：");
        tree.print_afterOrder(tree_root);
        System.out.println("\n");

        System.out.println("层次遍历：");
        tree.print_levelOrder(tree_root);
        System.out.println("\n");
    }

    /**
     * 按照先序遍历的顺序 打印节点值
     * @param root 树的根节点
     */
    public void print_preOrder(TreeNode root){

        if(root == null){
            return;
        }else {

            System.out.print(root.val + " ");
            print_preOrder(root.left);
            print_preOrder(root.right);
        }
    }

    /**
     * 非递归算法 先序遍历
     * @param root
     */
    public void preOrderStack(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val+" ");
            if(node.right!=null)
                stack.push(node.right);
            if(node.left!=null)
                stack.push(node.left);
        }
    }




    public void print_interOrder(TreeNode root){

        if(root == null){
            System.out.print("null ");
        }else {
            print_interOrder(root.left);
            System.out.print(root.val + " ");
            print_interOrder(root.right);
        }
    }

    /**
     * 中序遍历非递归算法
     * @param root
     */
    public void interOrderStack(TreeNode root){

        TreeNode p=root;
        Stack<TreeNode> stack = new Stack<>();
        while(p!=null||!stack.isEmpty()){
            // 找到最左边的子树
            if(p!=null){
                stack.push(p);
                p = p.left;
            }
            else{
                p = stack.pop();
                System.out.print(p.val + " ");
                p = p.right;  // 向右跨一步
            }
        }


    }


    public void print_levelOrder(TreeNode root){

        if(root==null){
            System.out.println("this is null tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size()>0){

            TreeNode node = queue.poll();
            System.out.print(node.val+" ");

            if(node.left!=null) queue.add(node.left);
            if(node.right!=null) queue.add(node.right);
        }

    }


    public void print_afterOrder(TreeNode root){

        if(root == null){
            System.out.print("null ");
        }else {
            print_afterOrder(root.left);
            print_afterOrder(root.right);
            System.out.print(root.val + " ");
        }
    }


    /**
     * 以先序遍历顺序创建一个树,以递归法
     * @return 返回root
     * 输入例子： 1 2 4 0 0 5 0 0 3 6 0 0 7 0 0
     */
    public TreeNode createTree(){

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        TreeNode node;
        if(a == 0){
            node = null;
        }else {
            node = new TreeNode();
            node.val = a;
            node.left = createTree();
            node.right = createTree();
        }
        return node;
    }

}

class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){
        val = 0;
    }
}
