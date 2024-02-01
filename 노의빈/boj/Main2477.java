package boj;

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main2477 {
	private static int K, answer;
	private static List<Point> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		Point now = new Point(0, 0);
		for (int i=0; i<6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			switch (d) {
				case 1:
					now.y += len;
					break;
				case 2:
					now.y -= len;
					break;
				case 3:
					now.x -= len;
					break;
				case 4:
					now.x += len;
					break;
			}
			list.add(new Point(now.x, now.y));
		}
		solve();
		System.out.println(answer);
	}
	private static void solve() {
		// 가장 왼쪽 y좌표, 가장 위쪽 x좌표, 가장 오른쪽 y좌표, 가장 아래쪽 x좌표
		// 중간 x 좌표, 중간 y 좌표를 조합해 답 계산
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		Point[] smallRect = new Point[4];
		int idx = 0;
		for (int i=0; i<6; i++) {
			Point now = list.get(i);
			minX = Math.min(minX, now.x);
			minY = Math.min(minY, now.y);
			maxX = Math.max(maxX, now.x);
			maxY = Math.max(maxY, now.y);
		}
		// 중간점 구하기
		Point mid = new Point(0, 0);
		for (int i=0; i<6; i++) {
			Point now = list.get(i);
			if (minX < now.x && now.x < maxX || minY < now.y && now.y < maxY) {
				smallRect[idx++] = new Point(now.x, now.y);
			}
		}
		
		
		// 가장 큰 4개의 꼭짓점 중 리스트에 없는 꼭짓점과
		// 중간점들을 이용해 사각형 넓이 구하기
		Point[] largeRect = new Point[4];
		largeRect[0] = new Point(minX, minY);
		largeRect[1] = new Point(minX, maxY);
		largeRect[2] = new Point(maxX, minY);
		largeRect[3] = new Point(maxX, maxY);
		Point last = new Point(0, 0);
		for (int i=0; i<4; i++) {
			boolean isIn = false;
			Point nowCheck = largeRect[i];
			// 4개점 중 없는거 확인
			for (int j=0; j<6; j++) {
				Point p = list.get(j);
				if (p.x == nowCheck.x && p.y == nowCheck.y) {
					isIn = true;
					break;
				}
			}
			// 4개 점 중 없다면 마지막 점으로 선택
			if (!isIn) {
				last = new Point(nowCheck.x, nowCheck.y);
			}
		}
		// 작은 사각형의 4개의 점을 모두 알았기 때문에 전체 사각형 - 작은 사각형
		smallRect[3] = last;
		int minX2 = Integer.MAX_VALUE;
		int minY2 = Integer.MAX_VALUE;
		int maxX2 = Integer.MIN_VALUE;
		int maxY2 = Integer.MIN_VALUE;
		for (int i=0; i<4; i++) {
			Point now = smallRect[i];
			minX2 = Math.min(minX2, now.x);
			minY2 = Math.min(minY2, now.y);
			maxX2 = Math.max(maxX2, now.x);
			maxY2 = Math.max(maxY2, now.y);
		}
		int large = (maxX - minX) * (maxY - minY);
		int small = (maxX2 - minX2) * (maxY2 - minY2);
		answer = (large - small) * K;
	}
}
