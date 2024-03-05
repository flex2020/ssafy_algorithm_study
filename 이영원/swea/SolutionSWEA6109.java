import java.util.*;
import java.io.*;

class Solution
{
    static int[][] original;
    static int[][] move;
    static int N;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            
            original = new int[N][N];
            move = new int[N][N];
            
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    original[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            switch(command){
                case "left":
                    leftMove();
                    break;
                case "right":
                    rightMove();
                    break;
                case "up":
                    upMove();
                    break;
                case "down":
                    downMove();
                    break;
            }
            
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    sb.append(move[i][j]).append(" ");
                }
                sb.append("\n");
            }
            
            System.out.println("#" + test_case);
            System.out.print(sb);
		}
	}
    
    private static void downMove(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int idx=N-1;
            for(int j=N-1;j>=0;j--){
                if(original[j][i]!=0){ // 담기
                    dq.offerLast(original[j][i]);
                }
            }
            //System.out.println(dq);
            while(!dq.isEmpty()){
                int num = dq.pollFirst();
                if(move[idx][i]==0) move[idx][i]=num;
                else if(move[idx][i]==num) move[idx--][i]=num*2;
                else{
                    move[--idx][i]=num;
                }
            }
        }
    }
    
    private static void upMove(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int idx=0;
            for(int j=0;j<N;j++){
                if(original[j][i]!=0){ // 담기
                    dq.offerLast(original[j][i]);
                }
            }
            //System.out.println(dq);
            while(!dq.isEmpty()){
                int num = dq.pollFirst();
                if(move[idx][i]==0) move[idx][i]=num;
                else if(move[idx][i]==num) move[idx++][i]=num*2;
                else{
                    move[++idx][i]=num;
                }
            }
        }
    }
    
    private static void rightMove(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int idx=N-1;
            for(int j=N-1;j>=0;j--){
                if(original[i][j]!=0){ // 담기
                    dq.offerLast(original[i][j]);
                }
            }
            //System.out.println(dq);
            while(!dq.isEmpty()){
                int num = dq.pollFirst();
                if(move[i][idx]==0) move[i][idx]=num;
                else if(move[i][idx]==num) move[i][idx--]=num*2;
                else{
                    move[i][--idx]=num;
                }
            }
        }
    }
    
    private static void leftMove(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int idx=0;
            for(int j=0;j<N;j++){
                if(original[i][j]!=0){ // 담기
                    dq.offerLast(original[i][j]);
                }
            }
            //System.out.println(dq);
            while(!dq.isEmpty()){
                int num = dq.pollFirst();
                if(move[i][idx]==0) move[i][idx]=num;
                else if(move[i][idx]==num) move[i][idx++]=num*2;
                else{
                    move[i][++idx]=num;
                }
            }
        }
    }
    
}
