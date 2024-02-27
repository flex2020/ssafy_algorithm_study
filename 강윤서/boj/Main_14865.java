package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_14865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		List<int[]> Position = new ArrayList<>();
        Stack<int[]> inputX = new Stack<>();
		st = new StringTokenizer(br.readLine());
		int curX = Integer.parseInt(st.nextToken());
		int curY = Integer.parseInt(st.nextToken());
        int curD = 2; // 1 -1
        int startX = curX, startY = curY;
		int x = -1, y = -1;
		for (int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (curY * y < 0 && curX == x) {
                if (y < curY) { // 아래 -> 이전 방향이 위였으면 Position에 넣기
                    curD = -1;
                    if (!inputX.isEmpty() && inputX.peek()[1] == 1) { // 스택(inputX)의 탑이랑 비교해서 방향이 다른지 (y좌표가 위인 곳을 보려면 위->아래로 바껴야함)
                        if (inputX.peek()[0] < x) {
                            Position.add(new int[] {inputX.peek()[0], x});
                        } else {
                            Position.add(new int[] {x, inputX.peek()[0]});
                        }
                        inputX.pop();
                    } else {
                        inputX.add(new int[] {x, curD});
                    }
                }
                else { // 위
                    curD = 1;
                    inputX.add(new int[] {x, curD});
                }
            }
            curX = x;
            curY = y;
		}
        if (!inputX.isEmpty()) { // 시작점과 비교
            if (inputX.size() == 1) {
                if (startY * y < 0 && startX == x) {
                    if (startY < y) { // 아래
                        curD = -1;
                    } else {
                        curD = 1;
                    }
                }
                if (inputX.peek()[1] * curD < 1) {
                    if (inputX.peek()[0] < x) {
                        Position.add(new int[] {inputX.peek()[0], x});
                    } else {
                        Position.add(new int[] {x, inputX.peek()[0]});   
                    }
                }
            } else if (inputX.size() == 2) {
                int[] one = inputX.pop();
                int[] two = inputX.pop();
                if (one[1] * two[1] < 0) {
                    if (one[0] < two[0])
                        Position.add(new int[] {one[0], two[0]});
                    else
                        Position.add(new int[] {two[0], one[0]});
                }
            }
            
        }
		Collections.sort(Position, (p1, p2) -> p1[0] - p2[0]);

		int answer1 = 0; // 포함되지 않는 개수
		int answer2 = 0; // 포함하지 않는 개수
		
		Stack<int[]> stack = new Stack<>();
		
		int end = Position.get(0)[1];
		stack.add(Position.get(0));
		for (int i=1; i<Position.size(); i++) {
            if (end < Position.get(i)[0]) {
                answer1 += 1;
                end = Position.get(i)[1];
            }
            if (stack.peek()[1] < Position.get(i)[0]) {
                answer2 += 1;
                stack.pop();
            }
			stack.add(Position.get(i));
		}
		if (!stack.isEmpty()) {
			answer1 += 1;
            answer2 += 1;
		}
		System.out.println(answer1 + " " + answer2);
	}
}
