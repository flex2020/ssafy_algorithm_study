package boj;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main17143 {
	private static int R, C, M, fishingKing, answer;
	private static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, 1, -1};
	private static Shark[] sharks;
	private static int[][][] sharkMap;
	private static int[][][] tempMap;
	
	static class Shark {
		int r, c, s, d, z;
		boolean death;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[M];
		sharkMap = new int[R+1][C+1][2];
		tempMap = new int[R+1][C+1][2];
		for (int i=1; i<=R; i++) {
			for (int j=1; j<=C; j++) {
				sharkMap[i][j][0] = -1;
				tempMap[i][j][0] = -1;
			}
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(r, c, s, d, z);
			sharkMap[r][c][0] = i;
			sharkMap[r][c][1] = z;
		}
		
		simulate();
		
		System.out.println(answer);
	}
	
	private static void simulate() {
		//display();
		while (++fishingKing <= C) {
			// 낚시왕 이동
			// 낚시왕 열의 상어 중 0과 제일 가까운 상어 잡기
			//display();
			fishing();
			//System.out.println("물고기 이동");
			// 상어 이동
			moveShark();
			
		}
	}
	
	private static void fishing() {
		// 현재 낚시왕의 열 중 가장 가까운 상어를 잡음
		for (int i=1; i<=R; i++) {
			// 상어가 없다면 지나간다
			if (sharkMap[i][fishingKing][0] == -1) continue;
			int idx = sharkMap[i][fishingKing][0]; // 낚시왕이 잡은 상어의 인덱스
			sharks[idx].death = true; // 상어 잡혔다 처리
			sharkMap[i][fishingKing][0] = -1;
			//System.out.println(fishingKing + " 위치 잡은 물고기 " + sharkMap[i][fishingKing][1]);
			answer += sharkMap[i][fishingKing][1]; // 잡은 물고기 크기 증가
			break;
		}
		
	}
	
	private static void moveShark() {
		List<Point> plist = new ArrayList<>();
		for (int i=0; i<M; i++) {
			// 상어가 잡혔다면 더미값 넣어주기
			if (sharks[i].death) {
				plist.add(new Point(0, 0));
				continue;
			}
			// 상어가 살아있다면 다음에 이동할 곳 알아내기
			int s = 0;
			// 위로 이동
			switch (sharks[i].d) {
				case 1: // 상
				case 2: // 하
					s = sharks[i].s % (2 * (R - 1));
					break;
				case 3: // 우
				case 4: // 좌
					s = sharks[i].s % (2 * (C - 1));
					break;
			}
			// s번 이동
			int r = sharks[i].r;
			int c = sharks[i].c;
			//System.out.println(sharks[i].z + " 상어 기존 위치 " + r + ", " + c + " 이동 수 = " + s + " 방향 = " + sharks[i].d);
			for (int k=0; k<s; k++) {
				int nr = r + dx[sharks[i].d];
				int nc = c + dy[sharks[i].d];
				//System.out.println("일단해보는 nr = " + nr + ", nc = " + nc);
				// 지도밖이면 방향 바꿔주고 위치 조정하기
				if (nr <= 0 || nr > R || nc <= 0 || nc > C) {
					if (sharks[i].d % 2 == 0) sharks[i].d -= 1;
					else sharks[i].d += 1;
					//System.out.println(sharks[i].z + " 방향전환 = " + sharks[i].d);
					nr = r + dx[sharks[i].d];
					nc = c + dy[sharks[i].d];
				}
				r = nr;
				c = nc;
				//System.out.println("r = " + r + ", c = " + c);
			}
			Point p = new Point(r, c);
			//System.out.println(sharks[i].z + " 상어 다음 위치 " + p.x + ", " + p.y);
			plist.add(p);
		}
		Set<Point> tempset = new HashSet<>(); // tempMap 초기화용도
		// 상어가 이동할 곳으로 이동
		for (int i=0; i<plist.size(); i++) {
			if (sharks[i].death) continue;
			Point next = plist.get(i);
			int prevR = sharks[i].r;
			int prevC = sharks[i].c;
			//System.out.println("prevR = " + prevR + ", prevC = " + prevC);
			sharkMap[prevR][prevC][0] = -1;
			// 가려고하는 위치가 비어있는 경우
			if (tempMap[next.x][next.y][0] == -1) {
				// 상어가 이동한다
				tempMap[next.x][next.y][0] = i;
				tempMap[next.x][next.y][1] = sharks[i].z;
				sharks[i].r = next.x;
				sharks[i].c = next.y;
				tempset.add(new Point(next.x, next.y));
				continue;
			}
			// 가려고 하는 위치에 이미 더 큰 상어가 있는 경우
			if (tempMap[next.x][next.y][1] > sharks[i].z) {
				// 상어 죽음 처리
				sharks[i].death = true;
				//System.out.println("더 큰 상어 만나서 죽음 = " + sharks[i].z);
				continue;
			}
			// 가려고 하는 위치에 더 작은 상어가 있는 경우
			sharks[tempMap[next.x][next.y][0]].death = true;
			//System.out.println(sharks[i].z +  "번 상어가 작은 상어만나서 먹음 = " + sharks[tempMap[next.x][next.y][0]].z);
			tempMap[next.x][next.y][0] = i;
			tempMap[next.x][next.y][1] = sharks[i].z;
			sharks[i].r = next.x;
			sharks[i].c = next.y;
			
			tempset.add(new Point(next.x, next.y));
		}
		// tempMap 초기화
		for (Point p : tempset) {
			sharkMap[p.x][p.y][0] = tempMap[p.x][p.y][0];
			sharkMap[p.x][p.y][1] = tempMap[p.x][p.y][1];
			tempMap[p.x][p.y][0] = -1;
		}
	}
	
	private static void display() {
		for (int i=1; i<=R; i++) {
			for (int j=1; j<=C; j++) {
				System.out.print(sharkMap[i][j][0] == -1 || sharks[sharkMap[i][j][0]].death ? -1 + " " : sharks[sharkMap[i][j][0]].z + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
