package 강윤서.boj;

/*
 * 1. 어떤 수가 아래에 오는지에 따라 위로 오는 번호는 고정됨 -> Map으로 고정해놓기 (python: dict)
 * 2. 위아래가 고정되면 양 옆을 어느 방향으로 보는지에 따라 4가지 경우가 나옴 -> 그 중 가장 높은 수 구하기
 * 3. 그렇게 되면 다음에 놓는 거는 놓을 수 있는 가지수가 1가지 (옆면 중 가장 높은 것이 올 수 있도록)
 */
import java.io.*;
import java.util.*;
public class Main_2116 {
    static int N, answer = -1;
    static Map<Integer, Integer> otherSide;
    static List<int[]> dice;
    static boolean[] selected; // 윗면 or 아랫면
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 마주보고 있는 면이 인덱스 구하기
        otherSide = new HashMap<>();
        otherSide.put(0, 5);
        otherSide.put(1, 3);
        otherSide.put(2, 4);
        otherSide.put(3, 1);
        otherSide.put(4, 2);
        otherSide.put(5, 0);

        N = Integer.parseInt(br.readLine());
        dice = new ArrayList<>();
        selected = new boolean[6];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] d = new int[6];
            for (int j=0; j<6; j++) {
                d[j] = Integer.parseInt(st.nextToken());
            }
            dice.add(d);
        }
        for (int i=0; i<6; i++) { // 첫번째 주사위의 i 번째가 아랫면
            int top = dice.get(0)[otherSide.get(i)]; // 위의 값
            selected[i] = true;
            selected[otherSide.get(i)] = true;
            int sum = 0;
            for (int j=0; j<6; j++) {
                if (!selected[j]) {
                    sum = Math.max(sum, dice.get(0)[j]); // 양 옆면 중 가장 높은 값 저장
                }
            }
            Arrays.fill(selected, false); // 위아래 방문 해제 

            // 나머지 주사위는 옆면 중 가장 높은 것만 정하면 됨
            for (int cnt=1; cnt<N; cnt++) {
                // top의 값과 일치하는 면을 아랫면으로 두고 boolean 배열 세팅
                for (int j=0; j<6; j++) {
                    if (dice.get(cnt)[j] == top) {
                        selected[j] = true;
                        selected[otherSide.get(j)] = true;
                        top = dice.get(cnt)[otherSide.get(j)];
                        break;
                    }
                }
                int maxValue = 0;
                for (int j=0; j<6; j++) {
                    if (!selected[j]) {
                        maxValue = Math.max(maxValue, dice.get(cnt)[j]); // 양 옆면 중 가장 높은 값 저장
                    }
                }
                sum += maxValue;
                Arrays.fill(selected, false);
            }
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}
