import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    // 행 인덱스는 down 포지션 인덱스
    // 열 인덱스는 0~3까진 옆구리에 올 수 있는 인덱스값, 4는 down 포지션 인덱스에 대응되는 top 포지션 인덱스
    static int[][] dicePos = {
            {1, 2, 3, 4, 5},
            {0, 2, 4, 5, 3},
            {0, 1, 3, 5, 4},
            {0, 2, 4, 5, 1},
            {0, 1, 3, 5, 2},
            {1, 2, 3, 4, 0}
    };

    static int[][] dices; // 입력받는 2차원 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer=0;

        for (int i = 0; i < 6; i++) { // 첫 주사위가 놓이는 경우의 수(6가지)
            // 첫 주사위의 아랫면이 i 인덱스일 경우 윗면의 인덱스의 숫자값을 top에 넣기
            int top = dices[0][dicePos[i][4]];
            // result에는 옆면에 올 수 있는 숫자값 중 최댓값을 구해서 넣어준다.(돌리는건 4면 다 가능하기 때문)
            int result = findMax(top, 0);
            // 1~나머지 주사위 수만큼 최댓값을 구하고 해당 주사위의 윗면의 수를 구하는 작업을 반복
            for (int j = 1; j < N; j++) {
                result+=findMax(top, j);
                top = findTop(top, j);
            }
            // 위 반복문이 완료되었을 때 최댓값을 answer에 넣는다.
            answer = Math.max(answer, result);
        }

        System.out.println(answer);


    }

    // 이전 주사위의 윗값이 주어졌을 때 현재 주사위의 윗값을 구하는 메소드
    private static int findTop(int top, int idx){
        int result=0;
        // 현재 주사위의 아랫면의 수는 이전 주사위의 윗면의 수와 같아야 하므로 해당 값의 인덱스를 구한다.
        for (int i = 0; i < 6; i++) {
            if(dices[idx][i]==top){
                result=i;
                break;
            }
        }
        // 현재 주사위의 아랫면 수에 대응되는 윗면의 수를 리턴
        return dices[idx][dicePos[result][4]];
    }

    // 이전 주사위의 윗값이 주어졌을 때 옆면 중 최댓값을 구하는 함수
    private static int findMax(int top, int idx){
        int resultIdx=0;
        int result=0;
        // 현재 주사위의 아랫면의 수는 이전 주사위의 윗면의 수와 같아야 하므로 해당 값의 인덱스를 구한다.
        for (int i = 0; i < 6; i++) {
            if(dices[idx][i]==top){
                resultIdx=i;
                break;
            }
        }
        // 옆면들중 가장 큰값을 찾아서 리턴
        for (int i = 0; i < 4; i++) {
            result=Math.max(result, dices[idx][dicePos[resultIdx][i]]);
        }
        return result;
    }

}
