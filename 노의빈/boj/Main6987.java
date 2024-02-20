package boj;

import java.io.*;
import java.util.*;

public class Main6987 {
	private static Score[] scores;
	private static int answer;
	private static Score[] temp;
	private static Game[] games;
	
	static class Game {
		int a, b;
		public Game(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		@Override
		public String toString() {
			return "Game [a=" + a + ", b=" + b + "]";
		}
	}
	
	static class Score {
		int win, draw, lose;
		
		public Score() {}
		
		public Score(int win, int draw, int lose) {
			super();
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Score)) return false;
			Score s = (Score) obj;
			return win == s.win && draw == s.draw && lose == s.lose;
		}

		@Override
		public String toString() {
			return "[win=" + win + ", draw=" + draw + ", lose=" + lose + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		games = new Game[15];
		int idx = 0;
		for (int i=0; i<5; i++) {
			for (int j=i+1; j<6; j++) {
				games[idx++] = new Game(i, j);
			}
		}
		
		
		for (int tc=0; tc<4; tc++) {
			answer = 0;
			scores = new Score[6];
			temp = new Score[6];
			for (int i=0; i<6; i++) temp[i] = new Score();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<6; i++) {
				scores[i] = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			// 승, 무, 패의 합이 5인지 확인
			boolean cant = false;
			for (int i=0; i<6; i++) {
				int total = scores[i].win + scores[i].draw + scores[i].lose;
				if (total != 5) {
					cant = true;
					break;
				}
			}
			if (!cant) recursive(0);
			sb.append(answer + " ");
		}
		System.out.println(sb.toString());
	}
	
	
	private static void recursive(int index) {
		if (answer == 1) return;
		if (index == 15) {
			answer = 1;
			return;
		}
		
		Game game = games[index];
		int teamA = game.a;
		int teamB = game.b;
		// 이겼다
		if (scores[teamA].win > temp[teamA].win && scores[teamB].lose > temp[teamB].lose) {
			temp[teamA].win += 1;
			temp[teamB].lose += 1;
			recursive(index + 1);
			temp[teamA].win -= 1;
			temp[teamB].lose -= 1;
		}
		// 비겼다
		if (scores[teamA].draw > temp[teamA].draw && scores[teamB].draw > temp[teamB].draw) {
			temp[teamA].draw += 1;
			temp[teamB].draw += 1;
			recursive(index + 1);
			temp[teamA].draw -= 1;
			temp[teamB].draw -= 1;
		}
		// 졌다
		if (scores[teamA].lose > temp[teamA].lose && scores[teamB].win > temp[teamB].win) {
			temp[teamA].lose += 1;
			temp[teamB].win += 1;
			recursive(index + 1);
			temp[teamA].lose -= 1;
			temp[teamB].win -= 1;
		}
	}
}
