import java.io.*;
import java.util.*;

class Solution {

    static int[][] answer;
    static Node[] nodeArr;
    static BinaryTree binaryTree;

    static class Node{
        int num;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString(){
            return "Node=" + this.num + " " + this.x + " " + this.y;
        }

    }

    static class BinaryTree{
        Node root;

        public BinaryTree(Node root){
            this.root = root;
        }

        public void insertNode(Node parent, Node child){
            // 부모의 x가 자식의 x보다 크다면 왼쪽 자식으로 배치
            if(parent.x > child.x){
                if(parent.left==null) parent.left=child; // 왼쪽 자식이 없다면 거기에 배치(정렬했기 때문에 거기가 자리가 맞음)
                else insertNode(parent.left, child); // 있다면 걔 타고 들어가기
            }else{
                if(parent.right==null) parent.right=child;
                else insertNode(parent.right, child);
            }
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        nodeArr = new Node[nodeinfo.length];

        initSetting(nodeinfo);

        preorder(binaryTree.root, 0);
        postorder(binaryTree.root, 0);

        return answer;
    }

    private static void initSetting(int[][] nodeinfo){

        for(int i=0;i<nodeinfo.length;i++){ // nodeArr 만들기
            nodeArr[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodeArr, (a, b) -> { // 정렬하기, y축 값이 같으면 x 오름차순, y는 내림차순
            if(a.y==b.y) return a.x-b.x;
            return b.y-a.y;
        });

        // System.out.println(Arrays.toString(nodeArr));

        binaryTree = new BinaryTree(nodeArr[0]); // 이진트리 루트 만들어두기

        for(int i=1;i<nodeArr.length;i++){ // 하나씩 돌아가면서 트리 만들기
            binaryTree.insertNode(binaryTree.root, nodeArr[i]);
        }

    }

    // 중왼오
    private static int preorder(Node node, int idx){
        answer[0][idx] = node.num;
        if(node.left!=null) idx = preorder(node.left, idx+1);
        if(node.right!=null) idx = preorder(node.right, idx+1);

        return idx;

    }

    // 왼오중
    private static int postorder(Node node, int idx){
        if(node.left!=null) idx = postorder(node.left, idx);
        if(node.right!=null) idx = postorder(node.right, idx);
        answer[1][idx] = node.num;

        return idx+1;
    }
}
