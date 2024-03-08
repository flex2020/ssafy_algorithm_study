package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1525 {
    static int N = 3, answer = -1, value = 1, startR, startC;
    static String board, goal;
    static Map<String, Boolean> checkDuplicate;
    static Queue<State> Q;
    static class State {
    	String board;
        int cnt;
        
        State (String board, int cnt) {
        	this.board = board;
            this.cnt = cnt; // 이동 횟수
            
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new String();
        goal = "123456780";
        checkDuplicate = new HashMap<>();
        Q = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board += st.nextToken();
            }
        }
        
        State start = new State(board, 0);
        Q.offer(start);
        // 초기상태 방문체크 리스트에 넣기
        checkDuplicate.put(board, true);
        while (!Q.isEmpty()) {
            State cur = Q.poll();
            if (done(cur.board)) {
                answer = cur.cnt;
                break;
            }
            int idx = cur.board.indexOf('0');
            // 상
            if (idx - 3 >= 0) {
            	String newString = new String(cur.board);
            	char value = newString.charAt(idx-3);
            	newString = newString.replace('0', '!');
            	newString = newString.replace(value, '0');
            	newString = newString.replace('!', value);
            	if (!checkDuplicate.containsKey(newString)) {
            		Q.offer(new State(newString, cur.cnt + 1));
                	checkDuplicate.put(newString, true);
            	}
            }
            // 하
            if (idx + 3 < N*N) {
            	String newString = new String(cur.board);
            	char value = newString.charAt(idx+3);
            	newString = newString.replace('0', '!');
            	newString = newString.replace(value, '0');
            	newString = newString.replace('!', value);
            	if (!checkDuplicate.containsKey(newString)) {
            		Q.offer(new State(newString, cur.cnt + 1));
                	checkDuplicate.put(newString, true);
            	}
            }
            // 좌 
            if (idx - 1 >= 0 && idx != 3 && idx != 6) {
            	String newString = new String(cur.board);
            	char value = newString.charAt(idx-1);
            	newString = newString.replace('0', '!');
            	newString = newString.replace(value, '0');
            	newString = newString.replace('!', value);
            	if (!checkDuplicate.containsKey(newString)) {
            		Q.offer(new State(newString, cur.cnt + 1));
                	checkDuplicate.put(newString, true);
            	}
            }
            // 우
            if (idx + 1 < N*N && idx != 2 && idx != 5) {
            	String newString = new String(cur.board);
            	char value = newString.charAt(idx+1);
            	newString = newString.replace('0', '!');
            	newString = newString.replace(value, '0');
            	newString = newString.replace('!', value);
            	if (!checkDuplicate.containsKey(newString)) {
            		Q.offer(new State(newString, cur.cnt + 1));
                	checkDuplicate.put(newString, true);
            	}
            }
        }
        
        System.out.println(answer);

    }
    public static boolean done(String board) {
    	if (board.equals(goal)) return true;
    	return false;
    }
}