import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[][] dp; // dp[i][j]는 i번째 문자부터 j번째 문자까지를 팰린드롬으로 만들기 위한 최소 연산 횟수를 저장하는 배열
    static int answer; // 최소 연산 횟수를 저장할 변수
    static char[] charArr; // 입력받은 문자열을 문자 배열로 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받은 문자열을 문자 배열로 변환
        charArr = br.readLine().toCharArray();

        // dp 배열 초기화: 50x50 크기 배열 (문자열 최대 길이 50)
        dp = new int[50][50];
        // 최소 연산 횟수를 최대 값으로 초기화
        answer = Integer.MAX_VALUE;

        // 네 번째 연산을 적용하지 않은 상태에서 연산 계산
        calculate(0);

        // 자기 자신만을 포함하는 부분 문자열 (길이가 1인 경우)은 이미 팰린드롬이므로, 연산이 필요 없다. dp[i][i] = 1로 설정
        for (int i = 0; i < charArr.length; i++) {
            dp[i][i] = 1;
        }

        // 두 문자만 교환(네 번째 연산)을 사용한 경우를 확인
        for (int i = 0; i < charArr.length-1; i++) {
            for (int j = i+1; j < charArr.length; j++) {
                // i번째 문자와 j번째 문자를 교환
                swap(i, j);
                // 교환 후에 연산을 계산 (네 번째 연산을 한 번 사용한 상태)
                calculate(1);
                // 원래 상태로 다시 복구
                swap(i, j);
            }
        }

        // 최소 연산 횟수를 출력
        System.out.println(answer);

    }

    // 디버깅용 함수: dp 배열과 현재 상태의 문자열을 출력
    private static void print(){
        for (int i = 0; i < charArr.length; i++) {
            System.out.print(charArr[i]);
        }
        System.out.println();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = 0; j < charArr.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // i번째 문자와 j번째 문자를 교환하는 함수
    private static void swap(int i, int j){
        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

    // 주어진 문자열을 팰린드롬으로 만들기 위한 최소 연산 횟수를 계산하는 함수
    private static void calculate(int plus){
        // 두 문자의 경우 (길이 2인 부분 문자열)를 먼저 처리
        for (int i = 0; i < charArr.length-1; i++) {
            dp[i][i+1] = charArr[i]!=charArr[i+1] ? plus+1 : plus; 
            // 만약 i번째 문자와 i+1번째 문자가 같지 않다면, 연산이 필요하기 때문에 plus+1을 저장
            // 같으면 연산이 필요 없으므로 plus를 그대로 저장
        }

        // 그 외 부분 문자열 길이가 3 이상인 경우를 처리
        for (int i = 2; i < charArr.length; i++) {
            for (int j = 0; i+j < charArr.length; j++) {
                // dp[j][i+j]는 (i+j-1)까지의 연산을 고려하여 연산 횟수 계산
                dp[j][i+j] = Math.min(dp[j][i+j-1], dp[j+1][i+j])+1;
                // 앞쪽이나 뒤쪽 중 하나를 삽입하거나 삭제하는 경우
                dp[j][i+j] = Math.min(dp[j][i+j], dp[j+1][i+j-1] + ((charArr[j]!=charArr[i+j]) ? 1 : 0));
                // j번째 문자와 i+j번째 문자가 다를 경우 문자를 교체하거나 맞추는 연산이 필요함
            }
        }

        // 최소 연산 횟수를 업데이트 (dp[0][charArr.length-1]에 전체 문자열을 팰린드롬으로 만들기 위한 최소 연산 횟수가 저장됨)
        answer = Math.min(answer, dp[0][charArr.length-1]);
    }
}
