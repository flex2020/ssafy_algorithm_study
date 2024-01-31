package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Cord{
	int x;
    int y;
	@Override
	public String toString() {
		return "Cord [x=" + x + ", y=" + y + "]";
	}
    
}

public class question1 {

    static boolean[] visited;
    static Cord company;
    static Cord home;
    static Cord[] sel;
    static Cord[] customers;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            int answer=0;
            st = new StringTokenizer(br.readLine());
            visited=new boolean[N];
            sel = new Cord[N];
            
            company = new Cord();
            home = new Cord();
            company.x=Integer.parseInt(st.nextToken());
            company.y=Integer.parseInt(st.nextToken());
            home.x=Integer.parseInt(st.nextToken());
            home.y=Integer.parseInt(st.nextToken());
            
            customers = new Cord[N];
            
            for(int i=0;i<N;i++){
                customers[i]=new Cord();
                customers[i].x=Integer.parseInt(st.nextToken());
                customers[i].y=Integer.parseInt(st.nextToken());
            }

			answer = perm(0);
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
    
    private static int perm(int k){
        int result=Integer.MAX_VALUE;
        if(k==sel.length){
            return len();
        }
        
        for(int i=0;i<customers.length;i++){
            if(!visited[i]){
                visited[i]=true;
                sel[k]=customers[i];
                result = Math.min(result, perm(k+1));
                visited[i]=false;
            }
        }
        return result;
    }
    
    private static int len(){
//        System.out.println(Arrays.toString(sel));
        int result=0;
        int end = sel.length-1;
        result+=Math.abs(sel[0].x-company.x) + Math.abs(sel[0].y-company.y);
        for(int i=1;i<sel.length;i++){
            result+=Math.abs(sel[i].x-sel[i-1].x) + Math.abs(sel[i].y-sel[i-1].y);
        }
    	result+=Math.abs(sel[sel.length-1].x-home.x) + Math.abs(sel[sel.length-1].y-home.y);
        return result;
    }

}
