package com.ssafy.hw.step2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    long N = Integer.parseInt(br.readLine());
	    System.out.println(fact(N));	
	}
	
	private static long fact(long n) {
		if(n==0) return 1;
		return n * fact(n-1);
	}
}
