import java.util.*;
import java.io.*;

class Team {
	int winCount;
	int noneCount;
	int loseCount;
	boolean isFinish;
	
	Team(int winCount, int noneCount, int loseCount) {
		this.winCount = winCount;
		this.noneCount = noneCount;
		this.loseCount = loseCount;
	}
}

class Match {
	Team my;
	Team enemy;
	
	Match(Team my, Team enemy) {
		this.my = my;
		this.enemy = enemy;
	}
}

// win: 1, none: 0, lose: -1
public class SolutionBaekJoon6987 {
	
	static final int T = 4;
	static final int TEAM_SIZE = 6;
	static Team[] teams;
	static Match[] matches;
	static int FIGHT_COUNT;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		aa: for (int testCase = 0; testCase < T; testCase++) {
			result = 1;
			
			st = new StringTokenizer(br.readLine());
			
			teams = new Team[TEAM_SIZE];
			for (int i = 0; i < teams.length; i++) {
				teams[i] = new Team(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				
				// 5번 경기 치르지 않은 경우
				if (teams[i].winCount + teams[i].noneCount + teams[i].loseCount != 5) {
					result = 0;
					sb.append(result + " ");
					continue aa;
				}
			}
			
			// 완승, 완무승부, 완패인 팀 검증하기
			for (int i = 0; i < teams.length; i++) {
				if (teams[i].noneCount == 0 && teams[i].loseCount == 0) {  // 완승
					teams[i].winCount = 0;
					teams[i].isFinish = true;
					if (!validatedComplete(i, -1)) {
						result = 0;
						sb.append(result + " ");
						continue aa;
					}
				} else if (teams[i].winCount == 0 && teams[i].loseCount == 0) {  // 완무승부
					teams[i].noneCount = 0;
					teams[i].isFinish = true;
					if (!validatedComplete(i, 0)) {
						result = 0;
						sb.append(result + " ");
						continue aa;
					}
				} else if (teams[i].winCount == 0 && teams[i].noneCount == 0) {  // 완패
					teams[i].loseCount = 0;
					teams[i].isFinish = true;
					if (!validatedComplete(i, 1)) {
						result = 0;
						sb.append(result + " ");
						continue aa;
					}
				}
			}
			
			// 남은 승부 횟수 계산하기
			int teamCount = 0;
			for (int i = 0; i < teams.length; i++) {
				if (!teams[i].isFinish) {
					if (teams[i].winCount == 0 && teams[i].noneCount == 0 && teams[i].loseCount == 0) {
						teams[i].isFinish = true;
					} else {
						teamCount++;
					}
				}
			}
			FIGHT_COUNT = (teamCount * (teamCount - 1)) / 2;
			
			// 남은 팀으로 경기표 생성하기
			matches = new Match[FIGHT_COUNT];
			int index = 0;
			for (int i = 0; i < teams.length; i++) {
				if (!teams[i].isFinish) {
					for (int j = i + 1; j < teams.length; j++) {
						if (!teams[j].isFinish) {
							matches[index++] = new Match(teams[i], teams[j]);
						}
					}
				}
			}
			
			result = 0;
			validateScores(0);
			
			sb.append(result + " ");
		}
		
		System.out.println(sb);
	}
	
	// 완승, 완무승부, 완패 검증하기
	// result: 기대하는 상대팀 결과
	public static boolean validatedComplete(int my, int result) {
		for (int enemy = 0; enemy < teams.length; enemy++) {
			if (enemy != my && !teams[enemy].isFinish) {
				switch (result) {
					case -1:
						if (teams[enemy].loseCount > 0) {
							teams[enemy].loseCount--;
						} else {
							return false;
						}
						break;
					case 0:
						if (teams[enemy].noneCount > 0) {
							teams[enemy].noneCount--;
						} else {
							return false;
						}
						break;
					case 1:
						if (teams[enemy].winCount > 0) {
							teams[enemy].winCount--;
						} else {
							return false;
						}
						break;
				}
			}
		}
		
		return true;
	}
	
	// 승부 결과 검증하기
	public static void validateScores(int count) {
		if (result == 1) {
			return;
		}
		
		if (count == FIGHT_COUNT) {
			result = 1;
			return;
		}
		
		Team my = matches[count].my;
		Team enemy = matches[count].enemy;
		
		// 자기 팀이 이긴 경우
		if (my.winCount > 0 && validateFight(enemy, -1)) {
			my.winCount--;
			enemy.loseCount--;
			validateScores(count + 1);
			my.winCount++;
			enemy.loseCount++;
		}
		
		// 자기 팀이 비긴 경우
		if (my.noneCount > 0 && validateFight(enemy, 0)) {
			my.noneCount--;
			enemy.noneCount--;
			validateScores(count + 1);
			my.noneCount++;
			enemy.noneCount++;
		}
		
		// 자기 팀이 진 경우
		if (my.loseCount > 0 && validateFight(enemy, 1)) {
			my.loseCount--;
			enemy.winCount--;
			validateScores(count + 1);
			my.loseCount++;
			enemy.winCount++;
		}
	}
	
	public static boolean validateFight(Team enemy, int result) {
		switch (result) {
			case -1:
				if (enemy.loseCount > 0) {
					return true;
				} else {
					return false;
				}
			case 0:
				if (enemy.noneCount > 0) {
					return true;
				} else {
					return false;
				}
			case 1:
				if (enemy.winCount > 0) {
					return true;
				} else {
					return false;
				}
		}
		
		return false;
	}

}
