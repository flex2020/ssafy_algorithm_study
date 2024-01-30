package com.ssafy.hw.step2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[] sel;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr=new int[N];
		sel=new int[M];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=i+1;
		}
		
		recursive(0);
		System.out.println(sb);
	}
	
	private static void recursive(int k) {
		if(k==sel.length) {
			for (int i = 0; i < sel.length; i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i]=true;
				sel[k]=arr[i];
				recursive(k+1);
				visited[i]=false;
			}
		}
		
	}
    
}
