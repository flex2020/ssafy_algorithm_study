import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution
{
    static int answer;
    static int[] dp; // 결과값 저장할 dp

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            answer=0;
            String stringNum = br.readLine();
            int number = Integer.parseInt(stringNum);
            dp = new int[100000]; // 최대 99999이므로 100000의 dp 생성
            //System.out.println(number + " " + stringNum.length() + " " + 0);
            splitNum(number, stringNum.length(), 0);


            System.out.println("#" + test_case + " " + answer);

        }
    }

    // num = 숫자, cnt = 숫자(num)의 길이 -> 333이면 3, 23이면 2, turn = 몇번 사이클 돌고있는지
    private static void splitNum(int num, int cnt, int turn){
        dp[num] = Math.max(dp[num], turn); // 해당 num의 dp에 turn 횟수 기록
        // basis part
        if(cnt==1){ // cnt가 1이라는 것은 한자리 수이기 때문에 더 이상 진행하지 않고 answer를 찾기
//            System.out.println(turn);
            answer = Math.max(answer, turn); // 현재 turn과 현재 answer중 큰 값 answer로 설정
            return;
        }
        for (int i = 1; i < cnt; i++) { // i개로 나누기 -> 123일 때, i=1이면 1|23, 12|3으로 나눈다는 뜻
            int[] sel = new int[i+1]; // 나누는 수보다 하나 더 크게 배열을 설정해서 나눈 수들이 들어갈 수 있도록 설정
            // 처음에 넣을 때, num은 최소 두자리수이기 때문에 num은 10으로 나누고, remain에는 10으로 나눈 나머지를 넣고, depth는 1로 설정해서 보낸다.
            // 즉, 첫번째 시행을 하고나서 시작한다는 뜻이다.
            realSplit(num/10, num%10, i, sel, turn, 1);
        }
    }

    // num = 현재 수, remain = 현재 나머지(넣지 않은 수), cnt = 현재 남은 나누는 수, sel = 선택한 배열
    // turn = 현재 사이클 횟수, depth = 현재 깊이(나머지 연산에 사용) -> 이놈때문에 고생함
    private static void realSplit(int num, int remain, int cnt, int[] sel, int turn, int depth){
        // basis part
        if(cnt == 0){ // 원하는 횟수만큼 나눴으므로 그다음 진행
            sel[sel.length-1] = remain+(num*10);
//            System.out.println(Arrays.toString(sel));

            // 쪼개진 수들을 곱연산
            int result=1;
            for (int i = 0; i < sel.length; i++) {
                result*=sel[i];
            }
            if(dp[result] != 0){ // dp[result]가 0이 아니라는건 전의 시행에서 여기까지 도달한 전례가 있다는 뜻
//                System.out.println(result + " " + dp[result] + " " + turn);
                // dp[result]가 현재 turn보다 크다면 전의 시행에서 여기까지 도달했을 때 더 많은 turn으로 도달했다는 뜻으로 현재 재귀는
                // 더 해봤자 의미가 없다는 뜻이므로 리턴
                if(dp[result] > turn) return;
            }

//            System.out.println(result + " " + dp[result] + " " + turn + " " + Arrays.toString(sel));
            // turn 횟수를 하나 올리고 새롭게 만들어진 result 숫자로 새로운 splitNum 진행
            splitNum(result, String.valueOf(result).length(), turn+1);
            return;
        }
        if(num == 0){ // 원하는 조건을 만족하지 못했으므로(원하는 만큼 자르지 않음) 그냥 리턴
            return;
        }
        // 여기서 remain 털기(remain을 털었으므로 depth는 1로 설정(초기화))
        sel[cnt-1] = remain;
        realSplit(num/10, num%10, cnt-1, sel, turn, 1);
        // 여기서 remain 안털고 다음으로 넘기기(이때 num%10은 10^depth만큼 곱하고 현재 remain과 더해서 재귀를 돌려야한다.
        realSplit(num/10, remain+num%10*(int)Math.pow(10, depth), cnt, sel, turn, depth+1);
    }
}
