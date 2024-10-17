import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, D; // N: 사람 수, D: 철로의 길이

    static class Position{
        int h, o; // h: 집 위치, o: 사무실 위치

        // Position 생성자, 집과 사무실 위치를 받음
        public Position(int h, int o){
            this.h = h;
            this.o = o;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "h=" + h +
                    ", o=" + o +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0; // 정답(최대로 포함되는 사람 수)

        N = Integer.parseInt(br.readLine()); // N: 사람 수 입력

        Position[] positions = new Position[N]; // 각 사람의 집과 사무실 위치를 담을 배열

        // 각 사람의 집과 사무실 위치 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 집 위치 입력
            int b = Integer.parseInt(st.nextToken()); // 사무실 위치 입력

            Position p;

            // 집과 사무실의 위치를 작은 값이 집, 큰 값이 사무실로 설정
            if(a < b){
                p = new Position(a, b);
            }else{
                p = new Position(b, a);
            }

            positions[i] = p; // 배열에 저장
        }

        D = Integer.parseInt(br.readLine()); // 철로의 길이 입력

        // 철로의 끝점(o)에 대해 오름차순 정렬
        Arrays.sort(positions, (a, b) -> {
            return a.o - b.o;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 집 위치를 저장할 우선순위 큐 (최소값을 쉽게 추출하기 위해)

        // 각 사람에 대해 철로에 포함될 수 있는지 확인
        for(Position p : positions){
            pq.offer(p.h); // 현재 사람의 집 위치를 큐에 추가

            // 철로에 포함될 수 없는 집들은 큐에서 제거
            while(!pq.isEmpty() && p.o > pq.peek() + D){
                pq.poll(); // 철로 길이 D를 초과하는 집 위치 제거
            }

            // 철로에 포함되는 사람 수의 최댓값 갱신
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer); // 최대로 포함되는 사람 수 출력
    }
}
