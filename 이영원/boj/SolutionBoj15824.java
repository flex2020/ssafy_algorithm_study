import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 배열의 크기 N
    static final int DIVIDE_VALUE = 1000000007; // 모듈러 연산을 위한 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader
        StringTokenizer st;
        long answer = 0; // 최종 스코필 지수의 합을 저장할 변수

        N = Integer.parseInt(br.readLine()); // N 값 입력받기

        long[] arr = new long[N]; // 음식의 매운 정도 배열
        long[] cnt = new long[N]; // 조합을 만들 때 사용될 2의 거듭제곱 값을 저장할 배열
        long[] sum = new long[N]; // 배열의 누적합을 저장할 배열

        st = new StringTokenizer(br.readLine()); // 음식의 매운 정도 값을 한 줄로 입력받음

        // 매운 정도 배열에 값 넣기
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken()); // 매운 정도 값을 배열에 저장
        }

        Arrays.sort(arr); // 배열을 오름차순으로 정렬 (최소값, 최대값을 효과적으로 찾기 위함)

        long mulCnt = 1L; // 2의 거듭제곱 값을 저장할 변수
        sum[0] = arr[0]; // 첫 번째 값은 그대로 누적합으로 저장

        // cnt 배열과 sum 배열 초기화
        for (int i = 1; i < N; i++) {
            cnt[i-1] = mulCnt; // i번째 값이 몇 번 곱해지는지 저장 (2^(i-1))
            mulCnt = (mulCnt * 2) % DIVIDE_VALUE; // 2의 거듭제곱을 계속 구함 (모듈러 연산을 통해 큰 값 방지)
            sum[i] = (sum[i-1] + arr[i]) % DIVIDE_VALUE; // 누적합 계산 (모듈러 연산)
        }

        // 테스트 출력 (확인을 위한 디버깅 코드, 실제로는 주석 처리되어 있음)
//        System.out.println(Arrays.toString(cnt));
//        System.out.println(Arrays.toString(sum));

        // 스코필 지수의 차이를 계산
        for (int i = 0; i < N-1; i++) {
            // 최댓값 - 최솟값에 해당하는 값을 구함
            long diff = (sum[N-1] - sum[N-i-2] - sum[i]) % DIVIDE_VALUE;
            if (diff < 0) {
                diff += DIVIDE_VALUE; // 음수일 경우, 모듈러 연산을 통해 양수로 변환
            }
            // 결과값을 업데이트 (모듈러 연산 적용)
            answer = (answer + (diff * cnt[i]) % DIVIDE_VALUE) % DIVIDE_VALUE;
        }

        // 최종 결과 출력 (모듈러 연산 적용)
        System.out.println(answer % DIVIDE_VALUE);
    }
}
