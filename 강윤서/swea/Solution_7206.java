package 강윤서.swea;

import java.util.*;
import java.io.*;
class Node {
	String number;
	int depth;
	Node (String number, int depth) {
		this.number = number;
		this.depth = depth;
	}
}
public class Solution_7206 {
	static int T, answer, length, result;
    static String input, temp;
    static Queue<Node> Q;
    static List<String> L;
    static int[] value, point;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        Q = new ArrayDeque<>();
        L = new ArrayList<>();
        value = new int[100000];
        point = new int[5];
        for (int tc=1; tc<=T; tc++) {
            input = br.readLine();
            answer = 0;
            Q.clear(); // 큐 초기화
            Arrays.fill(value, 0); // 방문 배열 초기화
            Q.offer(new Node(input, 0));
            bfs();
            for (int i=1; i<10; i++) {
            	if (answer < value[i]) {
            		answer = value[i];
            	}
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
    public static void bfs() {
    	while (!Q.isEmpty()) {
    		Node cur = Q.poll();
    		length = cur.number.length();
    		if (length == 1) {
    			answer = Math.max(answer, cur.depth);
    		}
    		for (int i=1; i<=length-1; i++) {
    			makePoint(cur.number, length-1, i, 0, 0);
    		}
    	}
    }
    public static void check(int n) {
    	L.clear();
    	temp = "";
    	
        for (int i=0; i<Integer.toString(n).length(); i++) {
            if (point[i] == 1) {
                L.add(temp+Integer.toString(n).toCharArray()[i]);
                temp = "";
            } else {
                temp += Integer.toString(n).toCharArray()[i];
            }
        }
        if (temp.length() != 0) L.add(temp);
        result = 1;
        for (int i=0; i<L.size(); i++) {
        	result *= Integer.parseInt(L.get(i));
        }
        
        if (value[result] < value[n]+1) {
            value[result] = value[n]+1;
            Q.offer(new Node(Integer.toString(result), value[n]+1));
        } 
        
	}
	public static void makePoint(String input, int n, int c, int idx, int cnt) {
    	if (cnt == c) {
    		check(Integer.parseInt(input));
    		return ;
    	}
    	for (int i=idx; i<n; i++) {
    		point[i] = 1;
    		makePoint(input, n, c, i+1, cnt+1);
    		point[i] = 0;
    	}
    }
}
