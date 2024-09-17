import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    // T: 목표로 하는 합, N: 배열 A의 크기, M: 배열 B의 크기
    static int T, N, M;
    static int[] A, B;  // 배열 A와 B
    static HashMap<Long, Long> mapA;  // A의 부 배열 합을 저장할 해시맵 (합, 빈도)
    static HashMap<Long, Long> mapB;  // B의 부 배열 합을 저장할 해시맵 (합, 빈도)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 입력: 목표 값 T
        T = Integer.parseInt(br.readLine());

        // 두 번째 입력: 배열 A의 크기 N
        N = Integer.parseInt(br.readLine());

        // 배열 A와 B의 부 배열 합을 저장할 해시맵 초기화
        mapA = new HashMap<>();
        mapB = new HashMap<>();

        // 정답을 저장할 변수
        long answer = 0;

        // 배열 A 초기화
        A = new int[N];

        // 세 번째 입력: 배열 A의 요소들
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 A의 모든 부 배열 합을 계산하여 mapA에 저장
        for (int i = 0; i < A.length; i++) {
            long sum = A[i];  // 첫 번째 요소부터 시작
            if(!mapA.containsKey(sum)) mapA.put(sum, 1L);  // 새로운 합이면 추가
            else mapA.put(sum, mapA.get(sum)+1);  // 이미 존재하는 합이면 빈도수 증가
            for (int j = i+1; j < A.length; j++) {  // 부 배열 합 계산 (연속된 부분합)
                sum += A[j];
                if(!mapA.containsKey(sum)) mapA.put(sum, 1L);  // 새로운 합이면 추가
                else mapA.put(sum, mapA.get(sum)+1);  // 이미 존재하는 합이면 빈도수 증가
            }
        }

        // 네 번째 입력: 배열 B의 크기 M
        M = Integer.parseInt(br.readLine());

        // 배열 B 초기화
        B = new int[M];

        // 다섯 번째 입력: 배열 B의 요소들
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 B의 모든 부 배열 합을 계산하여 mapB에 저장
        for (int i = 0; i < B.length; i++) {
            long sum = B[i];  // 첫 번째 요소부터 시작
            if(!mapB.containsKey(sum)) mapB.put(sum, 1L);  // 새로운 합이면 추가
            else mapB.put(sum, mapB.get(sum)+1);  // 이미 존재하는 합이면 빈도수 증가
            for (int j = i+1; j < B.length; j++) {  // 부 배열 합 계산 (연속된 부분합)
                sum += B[j];
                if(!mapB.containsKey(sum)) mapB.put(sum, 1L);  // 새로운 합이면 추가
                else mapB.put(sum, mapB.get(sum)+1);  // 이미 존재하는 합이면 빈도수 증가
            }
        }

        // 배열 A의 부 배열 합과 배열 B의 부 배열 합을 비교하여 T가 되는 경우 찾기
        for(long a : mapA.keySet()){  // mapA의 모든 부 배열 합에 대해
            if(mapB.containsKey(T-a)){  // T - A의 부 배열 합이 mapB에 존재하면
                answer = answer + (mapA.get(a) * mapB.get(T-a));  // 해당 빈도수 곱해서 정답에 더함
            }
        }

        // 결과 출력
        System.out.println(answer);
    }

}
