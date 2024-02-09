import java.util.*;
import java.io.*;

public class SolutionSWEA4013 {
	
	static final int MAGNET_SIZE = 4;
	static final int MAGNETISM_SIZE = 8;
	static List<LinkedList<Integer>> magnets;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			int K = Integer.parseInt(br.readLine());  // 자석 회전 횟수
			
			// 자성 정보 입력받기
			magnets = new ArrayList<>();
			LinkedList<Integer> magnet;
			for (int i = 0; i < MAGNET_SIZE; i++) {
				st = new StringTokenizer(br.readLine());
				
				magnet = new LinkedList<>();
				for (int j = 0; j < MAGNETISM_SIZE; j++) {
					magnet.addLast(Integer.parseInt(st.nextToken()));
				}
				
				magnets.add(magnet);
			}
			
			// 자석 회전하기
			int magnetNumber;
			int direction;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				magnetNumber = Integer.parseInt(st.nextToken());
				direction = Integer.parseInt(st.nextToken());
				
				isSelected = new boolean[MAGNET_SIZE];
				rotateMagnets(magnetNumber - 1, direction);
			}
			
			// 점수 계산히기
			int score = 0;
			for (int i = 0; i < MAGNET_SIZE; i++) {
				if (magnets.get(i).get(0) == 1) {
					score += Math.pow(2, i);
				}
			}
			
			System.out.println("#" + testCase + " " + score);
		}
	}
	
	public static void rotateMagnets(int current, int direction) {
		// 현재 자석의 꼭짓점 자성
		isSelected[current] = true;
		
		// 오른쪽 자석 회전하기
		if (current + 1 < MAGNET_SIZE && !isSelected[current + 1] && magnets.get(current + 1).get(6) != magnets.get(current).get(2)) {
			rotateMagnets(current + 1, -direction);
		}
		// 왼쪽 자석 회전하기
		if (current - 1 > -1 && !isSelected[current - 1] && magnets.get(current - 1).get(2) != magnets.get(current).get(6)) {
			rotateMagnets(current - 1, -direction);
		}
		
		// 현재 자석 회전하기
		switch(direction) {
			case 1:  // 시계 방향
				magnets.get(current).addFirst(magnets.get(current).pollLast());
				break;
			case -1:  // 반시계 방향
				magnets.get(current).addLast(magnets.get(current).pollFirst());
				break;
		}
	}

}
