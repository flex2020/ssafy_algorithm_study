import java.util.*;
import java.io.*;

public class Solution24511 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] types = new int[N];
		
        // 자료 구조 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < types.length; i++) {
			types[i] = Integer.parseInt(st.nextToken());
		}
		
        // 자료 구조가 큐인 것만 숫자 입력받아서 deque에 넣기
		Deque<Integer> deque = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		int num;
		for (int i = 0; i < types.length; i++) {
			num = Integer.parseInt(st.nextToken());
			
			if (types[i] == 0) {  // 자료 구조가 큐인 경우
				deque.addLast(num);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			deque.addFirst(Integer.parseInt(st.nextToken()));
			sb.append(deque.pollLast() + " ");
		}
		
		System.out.println(sb);
	}

}
