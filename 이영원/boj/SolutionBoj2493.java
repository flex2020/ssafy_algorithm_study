import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Tower{
    int idx;
    int height;
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Tower[] tower = new Tower[N]; // 탑 정보를 담을 배열(인덱스, 높이)
        int[] answer = new int[N]; // 정답을 담을 배열
        Deque<Tower> dq = new ArrayDeque();
        Tower[] remain = new Tower[N];
        int idx=0;

        for (int i = 0; i < N; i++) { // 탑에 해당하는 것들 dq에 넣기(deque을 스택으로 활용)
            tower[i] = new Tower();
            tower[i].idx=i;
            tower[i].height=Integer.parseInt(st.nextToken());
        }

        if(N!=1){
            // 초기 데이터 2개 넣기
            dq.offerLast(tower[N-1]);
            dq.offerLast(tower[N-2]);
            idx = N-3; // 중간에 스택에 추가로 넣어줄 데이터 인덱스값 설정
            // 가장 인접한 인덱스에서 원하는 비교를 하짐 못했을 경우, tower에서 새로운 데이터를 스택에 추가해주라는 것을 표시하기 위한 flag값
            boolean add=false;
            while(!dq.isEmpty()){
                // t가 오른쪽 탑, tmp가 레이저가 부딪히는지 확인할 왼쪽 탑
                Tower tmp = dq.pollLast();
                Tower t = dq.pollLast();

                // t가 null이면 스택에 하나만 담겨있었을 경우
                // idx가 0보다 작다면 tower 배열의 모든 데이터를 스택에 넣었을 경우
                // 위 두가지 경우가 모두 만족할 경우 스택에는 가장 왼쪽 탑의 데이터만 들어있다는 뜻이므로 break
                if(t==null &&  idx<0) break;
                else if(t==null) { // t만 null일 경우 tower에서 새 데이터를 넣어주고 다시 진행
                    dq.offerLast(tmp);
                    dq.offerLast(tower[idx--]);
                    continue;
                }
                // 인덱스가 더 크고 높이가 작다면 만나는거니까 answer에 데이터 입력 후 tmp는 다시 스택에 넣는다.
                if(t.height<tmp.height && t.idx>tmp.idx) {
                    answer[t.idx] = tmp.idx + 1;
                    dq.offerLast(tmp);
                }else{ // 만나지 않는다면 그대로 다시 스택에 t를 넣는다.
                    dq.offerLast(t);
                    if(idx>=0) { // tower 배열의 모든 데이터가 스택에 들어가지 않았을 경우, tmp와 새로운 tower 데이터를 스택에 넣는다.
                        dq.offerLast(tmp);
                        dq.offerLast(tower[idx--]);
                    } else{ // tower 배열의 모든 데이터가 스택에 들어간 경우, 나머지 스택에 들어있는 데이터는 다 0이다.(레이저가 안만남)
                        break;
                    }
                }
            }
        }
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
