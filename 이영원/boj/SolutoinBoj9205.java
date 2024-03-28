import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;

public class Main {
	
	static final int INF = 3366000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        StringBuilder answer = new StringBuilder();
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int t=1;t<=T;t++) {
        	int csNum = Integer.parseInt(br.readLine());
        	Point[] points = new Point[csNum+2];    	
        	
        	for(int j=0;j<csNum+2;j++) { // 0은 집, 1~csNum까진 편의점, csNum+1은 페스티벌
        		st = new StringTokenizer(br.readLine());
        		points[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	}
        	
        	int[][] floid = new int[csNum+2][csNum+2]; // 플로이드 워샬 써보기
        	
        	
        	// 초기화
        	for(int i=0;i<csNum+1;i++) {
        		for(int j=i+1;j<csNum+2;j++) {
        			if(i==j) floid[i][j]=0;
        			int dist = getDistance(points[i].x, points[i].y, points[j].x, points[j].y);
        			if(dist<=1000) {
            			floid[i][j]=dist;
            			floid[j][i]=dist;
        			}else {
            			floid[i][j]=INF;
            			floid[j][i]=INF;
        			}
        		}
        	}
        	
//        	print(floid);
        	
        	for(int k=0;k<csNum+2;k++) { // 경
        		for(int i=0;i<csNum+2;i++) { // 출
        			for(int j=0;j<csNum+2;j++) { // 도
        				floid[i][j] = Math.min(floid[i][k]+floid[k][j], floid[i][j]);
        			}
        		}
        	}
//        	print(floid);
        	
        	if(floid[0][csNum+1]>=INF) answer.append("sad").append("\n");
        	else answer.append("happy").append("\n");
        }
        
        System.out.println(answer);
        
        
    }
    
    
    private static int getDistance(int x1, int y1, int x2, int y2) {
    	return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
    
    private static void print(int[][] map) {
    	System.out.println();
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
    	System.out.println();
    }
    
}
