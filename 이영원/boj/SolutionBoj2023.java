package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2023 신기한 소수
public class Question6my {

    static int answer=0;
    static int[] arr= {1,2,3,5,7,9};
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        check("2", 1);
        check("3", 1);
        check("5", 1);
        check("7", 1);

    }

    private static void check(String s, int num) {
        int n = Integer.parseInt(s);
        for(int i=2;i*i<=n;i++) {
            if(n%i==0) return;
        }
        if(num==N) {
            System.out.println(n);
            return;
        }
        for(int i=0;i<arr.length;i++) {
            check(s+arr[i], num+1);
        }

    }
}
