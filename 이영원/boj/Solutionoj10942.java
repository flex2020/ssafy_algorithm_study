import java.io.*;
import java.util.*;

public class Main {

    // N: 칠판에 적힌 숫자의 개수, M: 질문의 개수, S: 질문의 시작 인덱스, E: 질문의 끝 인덱스
    static int N, M, S, E;
    static int[] board;  // 칠판에 적힌 숫자들을 저장할 배열
    static StringBuilder sb;  // 결과 출력을 위한 StringBuilder
    static int[][] field;  // 팰린드롬 여부를 저장할 DP 테이블

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        // 첫 번째 입력: 칠판에 적힌 숫자의 개수 N
        N = Integer.parseInt(br.readLine());

        // board 배열 초기화 (1-based 인덱스 사용)
        board = new int[N+1];
        field = new int[N+1][N+1];  // DP 테이블 초기화 (1-based 인덱스 사용)

        // 두 번째 입력: 칠판에 적힌 숫자들을 배열에 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        // 팰린드롬 여부를 미리 계산
        calculate();

        // 세 번째 입력: 질문의 개수 M
        M = Integer.parseInt(br.readLine());

        // 각 질문에 대한 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());  // 질문의 시작 인덱스
            int end = Integer.parseInt(st.nextToken());    // 질문의 끝 인덱스
            sb.append(field[start][end]).append("\n");  // 미리 계산된 결과를 출력에 추가
        }

        // 최종 결과 출력
        System.out.println(sb);
    }

    // 팰린드롬 여부를 계산하는 함수
    private static void calculate(){
        // 홀수 길이의 팰린드롬을 찾는 부분
        for (int i = 1; i <= N; i++) {
            int left = i;   // 왼쪽 인덱스 (팰린드롬의 중앙)
            int right = i;  // 오른쪽 인덱스 (팰린드롬의 중앙)
            // 팰린드롬을 확장하면서 확인
            while(left > 0 && right <= N){
                if(board[left] == board[right]) field[left][right] = 1;  // 팰린드롬이면 1 저장
                else break;  // 더 이상 팰린드롬이 아니면 종료
                left--;  // 왼쪽으로 확장
                right++;  // 오른쪽으로 확장
            }
        }

        // 짝수 길이의 팰린드롬을 찾는 부분
        for (int i = 1; i < N; i++) {
            int left = i;   // 왼쪽 인덱스
            int right = i+1;  // 오른쪽 인덱스 (짝수 길이이므로 바로 다음 인덱스)
            // 팰린드롬을 확장하면서 확인
            while(left > 0 && right <= N){
                if(board[left] == board[right]) field[left][right] = 1;  // 팰린드롬이면 1 저장
                else break;  // 더 이상 팰린드롬이 아니면 종료
                left--;  // 왼쪽으로 확장
                right++;  // 오른쪽으로 확장
            }
        }
    }

}
