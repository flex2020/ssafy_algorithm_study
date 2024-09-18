import java.util.*;
class Solution {
    static int idx;
    static int[][] answer;
    static Node root;
    static List<Node> list;
    static class Node {
        int idx, x, y;
        Node left, right;
        Node (int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return this.idx + " | left: " + this.left + " | right: " + this.right + "\n";
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        list = new ArrayList<>();
        for (int i=0; i<nodeinfo.length; i++) {
            int idx = i+1;
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            list.add(new Node(idx, x, y));
        }
        list.sort((n1, n2) -> n1.y == n2.y ? n1.x - n2.x : n2.y - n1.y); // y 내림 -> x 오름 정렬
        
        root = list.get(0);
        for (int i=1; i<list.size(); i++) {
            insert(root, list.get(i));
        }
        // print();
        idx = 0;
        preorder(root); // 전위순회
        idx = 0;
        postorder(root); // 후위순회
        return answer;
    }
    public static void print() {
        for (int i=0; i<list.size(); i++) {
            Node n = list.get(i);
            System.out.println(n);
        }
    }
    public static void insert(Node p, Node c) {
        if (p.x > c.x) { // c가 왼쪽
            if (p.left == null) p.left = c;
            else insert(p.left, c);
        } else { // c가 오른쪽
            if (p.right == null) p.right = c;
            else insert(p.right, c);
        }
    }
    public static void preorder(Node node) {
        answer[0][idx++] = node.idx;
        if (node.left != null) preorder(node.left);
        if (node.right != null) preorder(node.right);
    }
    public static void postorder(Node node) {
        if (node.left != null) postorder(node.left);
        if (node.right != null) postorder(node.right);
        answer[1][idx++] = node.idx;
    }
}
