package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hw2_0131 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N+1];
		int[] adds = new int[N+1]; // 과거 데이터를 갖고있는 배열(이게 dp인가?)
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<nums.length;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
			adds[i]=nums[i]+adds[i-1];
		}
		
		for(int i=0;i<M;i++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			answer = adds[end]-adds[start-1];
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
