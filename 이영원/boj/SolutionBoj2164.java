import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean flag=true; // flag가 true면 버리고 시작, false면 옮기고 시작
        int answer=1; // 한번 쭉 돌아갔을 때 살아남은 수 중 가장 위에 있는 수를 answer로 한다.
        int plus=1; // 돌아갔을 때 더할 수
        int i=N; 
        int remain=0;

        // i/2가 1이 될때까지 반복
        while(i!=1){
            remain=0;
            if(flag) { // 버리고 시작
                answer += plus; // 맨 위 숫자를 버리니까 그 다음 살아남은 숫자를 answer로 지정
                if(i%2!=0){ // 버리고 시작했는데 i가 홀수면 나머지값을 0으로 설정한다.
                    // 이 경우 버리고 시작하기 때문에 i/2개가 남기 때문이다.
                    remain=0;
                }
            }else{
                if(i%2!=0){ // 옮기고 시작했는데 i가 홀수면 나머지값을 1로 설정한다.
                    // 이 경우 옮기고 시작하기 때문에 i/2+1개가 남기 때문이다.
                    remain=1;
                }
            }

            if(i%2!=0) { // i가 홀수인 경우 다음 시행의 첫번째 행동이 바뀜
                flag = !flag;
            }
            plus*=2; // 살아남은 숫자들의 차이값 저장
            i=i/2+remain; // i를 2로 나누고 remain을 더한값을 i로 지정
        }
        System.out.println(answer);
    }
}
