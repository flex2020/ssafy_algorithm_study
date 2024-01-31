package com.ssafy.recursive;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gredient{
	int score;
    int calorie;
}

// 5215. 햄버거 다이어트 ( 조합 )
public class Question2 {

    static int sum;
    static int N;
    static int L;
    static boolean[] visited;
    static Gredient[] greds;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            sum=0;
            N = Integer.parseInt(st.nextToken()); // 재료수
            L = Integer.parseInt(st.nextToken()); // 최대칼로리
            greds = new Gredient[N];
            visited=new boolean[N];
            int answer=0;
            
            for(int i=0;i<N;i++){
            	st = new StringTokenizer(br.readLine());
                greds[i] = new Gredient();
                greds[i].score = Integer.parseInt(st.nextToken());
                greds[i].calorie = Integer.parseInt(st.nextToken());
            }
            
            comb(0,0,0);
            
            System.out.println("#" + test_case + " " + sum);
            
		}
	}
    
    private static void comb(int idx, int jumsu, int cal){
        if(cal>L){
            return;
        }
        if(idx == N){
            sum = Math.max(sum,jumsu);
            return;
        }
        // inductive part
        // 재료를 선택한 경우
        comb(idx+1, jumsu+greds[idx].score, cal+greds[idx].calorie);
        // 재료를 선택하지 않은 경우
        comb(idx+1, jumsu, cal);
    }

}
