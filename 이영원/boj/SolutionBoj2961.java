package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gredient{
    int sin;
    int ssun;
}

// 백준 2961 도영이가 만든 맛있는 음식
public class Question4my {

    static Gredient[] greds;
    static int N;
    static int answer=Integer.MAX_VALUE;

public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    greds = new Gredient[N];
    
    for(int i=0;i<N;i++) {
        st = new StringTokenizer(br.readLine());
        greds[i] = new Gredient();
        greds[i].sin = Integer.parseInt(st.nextToken());
        greds[i].ssun = Integer.parseInt(st.nextToken());
    }
    
    yori(0, new boolean[N]);
    System.out.println(answer);

}

private static void yori(int idx, boolean[] v) {
    int sin=1;
    int ssun=0;
    if(idx==N) {
        for(int i=0;i<N;i++) {
            if(v[i]) {
                sin*=greds[i].sin;
                ssun+=greds[i].ssun;
            }
        }
        if(sin==1 && ssun==0) return;
        answer=Math.min(answer, Math.abs(sin-ssun));
        return;
    }
    
    v[idx]=true;
    yori(idx+1, v);
    v[idx]=false;
    yori(idx+1, v);
    
}
}
