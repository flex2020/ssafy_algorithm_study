package 강윤서.boj;

import java.util.*;
import java.io.*;

public class Main_16927 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int rCnt = 0; // 겉 사각형마다 회전하는 횟수
        for (int cnt=0; cnt<Math.min(N, M)/2; cnt++){ // 겉 사각형 개수 (따로 돌리기)
        	if (cnt == 0) { // 첫 사각형 (0,0)이 첫 좌표
        		rCnt = R%((N+M-2)*2);
        	} else { // 그 안쪽 사각형 (1,1), (2,2) 등등.. 2씩 줄어들어야 한다.
        		rCnt = R%((N+M-2*(2*cnt+1))*2);
        	}
        	for (int r=0; r<rCnt; r++){ // 회전 횟수 
                // (cnt, cnt) -> 해당 겉 사각형의 왼쪽 위 좌표
                int first = A[cnt][cnt];
               
                for (int i=cnt; i<M-cnt-1; i++) { // 왼쪽으로 땡기기
                    A[cnt][i] = A[cnt][i+1];
                }
                
                for (int i=cnt; i<N-cnt-1; i++) { // 위쪽으로 땡기기
                    A[i][M-cnt-1] = A[i+1][M-cnt-1];
                }
                
                for (int i=M-cnt-1; i>cnt; i--) { // 오른쪽으로 땡기기
                    A[N-cnt-1][i] = A[N-cnt-1][i-1];
                }
                
                for (int i=N-cnt-1; i>cnt+1; i--) { // 아래로 땡기기
                    A[i][cnt] = A[i-1][cnt];
                }
                A[cnt+1][cnt] = first;
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
            	sb.append(A[i][j] + " ");
                
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
   }
   
}