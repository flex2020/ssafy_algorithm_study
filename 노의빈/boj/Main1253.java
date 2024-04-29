package boj;

import java.io.*;
import java.util.*;

public class Main1253 {
	private static int N, answer;
	private static int[] numbers;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		answer = 0;
		for (int i=0; i<N; i++) {
			if (check(i)) answer += 1;
		}
		
		System.out.println(answer);
	}
	
	private static boolean check(int idx) {
		int targetSum = numbers[idx];
		for (int i=0; i<N; i++) {
			if (i == idx) continue;
			int target = targetSum - numbers[i];
			if (binarySearch(0, N-1, target, idx, i)) return true;
		}
		return false;
	}
	
	private static boolean binarySearch(int start, int end, int target, int idx1, int idx2) {
		if (start > end) return false;
		
		int mid = (start + end) / 2;
		if (mid != idx1 && mid != idx2 && target == numbers[mid]) return true;
		else if (target < numbers[mid]) return binarySearch(start, mid - 1, target, idx1, idx2);
		return binarySearch(mid + 1, end, target, idx1, idx2);
	}
}
