package codetree;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main나무박멸 {
    private static int N, M, K, C, answer;
    private static int[][] map, herbicide;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    private static int[] hdx = {1, 1, -1, -1}, hdy = {1, -1, 1, -1};
    static class Tree {
    	int x, y, cnt;
		public Tree(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
    }
    

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        herbicide = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        simulate();
        System.out.println(answer);
    }

    private static void simulate() {
        while (M-- != 0) {
        	// 제초제 감소
        	decreaseHerbicide();
            // 1. 나무 성장
            grow();
            // 2. 나무 번식
            breed();
            // 3. 제초제 위치 선정
            Point p = select();
            // 4. 제초제 뿌리기
            spread(p);
        }
    }

    private static void grow() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
            	if (map[i][j] < 1) continue;
                for (int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] < 1) continue;
                    map[i][j] += 1;
                }
            }
        }
    }
    private static void breed() {
    	List<Tree> list = new ArrayList<>();
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			if (map[i][j] < 1) continue;
    			List<Point> temp = new ArrayList<>();
    			for (int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0 || herbicide[nx][ny] > 0) continue;
                    temp.add(new Point(nx, ny));
                }
    			for (Point p : temp) {
    				list.add(new Tree(p.x, p.y, map[i][j] / temp.size()));
    			}
    		}
    	}
    	for (Tree t : list) {
    		map[t.x][t.y] += t.cnt;
    	}
    }
    
    private static Point select() {
    	int x = 0, y = 0, max = 0;
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			if (map[i][j] < 1) continue;
    			int cnt = getDeathCount(i, j);
    			if (cnt > max) {
    				x = i;
    				y = j;
    				max = cnt;
    			}
    		}
    	}
    	answer += max;
    	return new Point(x, y);
    }
    
    private static void spread(Point p) {
    	map[p.x][p.y] = 0;
    	herbicide[p.x][p.y] = C + 1;
    	for (int d=0; d<4; d++) {
    		int nx = p.x;
    		int ny = p.y;
    		int dist = 0;
    		while (dist++ != K) {
    			nx += hdx[d];
    			ny += hdy[d];
    			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) break;
    			boolean flag = map[nx][ny] == 0;
    			map[nx][ny] = 0;
    			herbicide[nx][ny] = C + 1;
    			if (flag) break;
    		}
        }
    }
    
    private static int getDeathCount(int x, int y) {
    	int count = map[x][y];
    	for (int d=0; d<4; d++) {
    		int nx = x;
    		int ny = y;
    		int dist = 0;
    		while (dist++ != K) {
    			nx += hdx[d];
    			ny += hdy[d];
    			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) break;
    			boolean flag = map[nx][ny] == 0;
    			count += map[nx][ny];
    			if (flag) break;
    		}
        }
    	return count;
    }
    
    private static void decreaseHerbicide() {
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			if (herbicide[i][j] > 0) herbicide[i][j] -= 1;
    		}
    	}
    }
}
