package boj;

import java.io.*;
import java.util.*;

public class Main2295 {
	private static int N, answer;
	private static int[] numbers;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		for (int i=0; i<N; i++) numbers[i] = Integer.parseInt(br.readLine());
		Arrays.sort(numbers);
		for (int i=numbers.length-1; i>=0; i--) {
			int targetNumber = numbers[i];
			if (check(targetNumber, i)) {
				answer = targetNumber;
				break;
			}
		}
		System.out.println(answer);
	}
	
	private static boolean check(int target, int idx) {
		for (int i=0; i<=idx; i++) {
			int start = i;
			int end = idx - 1;
			int t = target - numbers[i];
			while (start <= end) {
				int sum = numbers[start] + numbers[end];
				if (sum == t) return true;
				if (sum < t) {
					start += 1;
				} else {
					end -= 1;
				}
			}
		}
		
		return false;
	}
}
