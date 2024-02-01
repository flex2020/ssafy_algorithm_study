package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 12891 DNA 비밀번호
public class Question5my {

    static int P;
    static int answer=0;
    static StringBuilder input;
    static int[] cur = {0,0,0,0}; // A(0) C(1) G(2) T(3)
    static int[] cut = {0,0,0,0}; // A(0) C(1) G(2) T(3)
    static int[] arr;

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int S = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    arr = new int[S];
    
    input = new StringBuilder(br.readLine());
    
    st = new StringTokenizer(br.readLine());
    for(int i=0;i<4;i++) { // 컷 넣기
        cut[i] = Integer.parseInt(st.nextToken());
    }
    
    for(int i=0;i<arr.length;i++) { // arr 넣어주기
        switch(input.charAt(i)) {
            case 'A':
                arr[i]=0;
                break;
            case 'C':
                arr[i]=1;
                break;
            case 'G':
                arr[i]=2;
                break;
            case 'T':
                arr[i]=3;
                break;
        }
    }
    
    for(int i=0;i<P;i++) { // 초기값 넣어주기
        cur[arr[i]]++;
    }
    
    if(check()) {
        answer++;
    }
    what(1);
    
    System.out.println(answer);

}

private static void what(int idx) {
    if(idx+P==input.length()+1) return;
    cur[arr[idx-1]]--;
    cur[arr[idx+(P-1)]]++;
    if(check()) {
        answer++;
    }
    what(idx+1);
}

private static boolean check() {
//        System.out.println(Arrays.toString(cur));
//        System.out.println(Arrays.toString(cut));
        for(int i=0;i<4;i++) {
            if(cur[i]<cut[i]) return false;
        }
        return true;
    }

}
