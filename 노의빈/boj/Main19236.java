import java.util.*;
import java.io.*;

public class Main19236 {
	private static int answer;
    private static int[][] map, dirMap;
    private static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[4][4];
        dirMap = new int[4][4];
        for (int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dirMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 상어의 초기위치,방향 설정
        int temp = map[0][0];
        map[0][0] = 0;
        
        moveFish();
        moveShark(0, 0, dirMap[0][0], temp, temp + " ");
        
        System.out.println(answer);
    }
    
    private static void moveShark(int x, int y, int d, int count, String s) {
    	answer = Math.max(answer, count);
		
    	// 상어는 최대 3칸까지 이동가능
    	for (int dist=1; dist<=3; dist++) {
    		int nx = x + dx[d] * dist;
    		int ny = y + dy[d] * dist;
    		
    		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == -1) continue;
    		// 상어, 물고기 이동전 지도 백업
    		int[][] tempMap = new int[4][4];
    		int[][] tempDirMap = new int[4][4];
    		copyMap(map, tempMap, dirMap, tempDirMap);
    		
    		// 상어가 이동했다면 빈칸으로 만들고
    		int temp3 = map[nx][ny];
    		map[x][y] = -1;
    		dirMap[x][y] = -1;
    		map[nx][ny] = 0;
    		// 상어가 이동했으니 물고기가 이동한다.
    		moveFish();

    		// 상어가 다음 이동을 한다
    		moveShark(nx, ny, dirMap[nx][ny], count + temp3, s + temp3 + " ");
    		
    		// 원래 지도 복구
    		copyMap(tempMap, map, tempDirMap, dirMap);
    		
    	}
    }
    
    private static void moveFish() {
    	L: for (int target=1; target<=16; target++) {
    		for (int i=0; i<4; i++) {
    			for (int j=0; j<4; j++) {
    				if (map[i][j] == target) {
    					for (int k=0; k<8; k++) {
    						int nx = i + dx[dirMap[i][j]];
    						int ny = j + dy[dirMap[i][j]];
    						if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == 0) { 
    							dirMap[i][j] = dirMap[i][j] + 1 == 9 ? 1 : dirMap[i][j] + 1;
    							continue;
    						}
    						swap(i, j, nx, ny);
    						break;
    					}
    					continue L;
    				}
    			}
    		}
    	}
    }
    
    private static void copyMap(int[][] map, int[][] tempMap, int[][] dirMap, int[][] tempDirMap) {
    	for (int i=0; i<4; i++) {
    		for (int j=0; j<4; j++) {
    			tempMap[i][j] = map[i][j];
    			tempDirMap[i][j] = dirMap[i][j];
    		}
    	}
    }

    private static void swap(int x1, int y1, int x2, int y2) {
    	int temp = map[x1][y1];
    	map[x1][y1] = map[x2][y2];
    	map[x2][y2] = temp;
    	
    	temp = dirMap[x1][y1];
    	dirMap[x1][y1] = dirMap[x2][y2];
    	dirMap[x2][y2] = temp;
    }
}
