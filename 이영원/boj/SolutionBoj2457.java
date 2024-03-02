import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 제공되는 꽃 개수
        int answer=0;
        int[][] flowers  = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDate = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDate = Integer.parseInt(st.nextToken());

            flowers[i][0] = startMonth*100 + startDate;
            flowers[i][1] = endMonth*100 + endDate;
        }

        Arrays.sort(flowers, (a, b) -> { // 시작날짜 기준 오름차순, 같다면 끝날짜 내림차순
            if(a[0]==b[0]) return b[1]-a[1];
            return a[0]-b[0];
        });

        int end=0;
        int idx=0;
        answer=1;

        for (int i = 0; i < flowers.length; i++) { // 시작점 잡기
            if(flowers[i][0]<=301 && flowers[i][1]>end){
                end = flowers[i][1];
                idx = i;
            }else if(flowers[i][0] > 301){
                break;
            }
        }

        if(end==0) answer=0;

//        System.out.println(end);

        while(true) {
            if(end>1130) break;
            int maxEnd=0;
            for (int i = idx+1; i < flowers.length; i++) { // 가장 fit한것 찾기
                if(flowers[i][0] <= end && flowers[i][1] > end){
                    if(maxEnd < flowers[i][1]){
                        idx=i;
                        maxEnd=flowers[i][1];
                    }
                }
            }
//            System.out.println(maxEnd);

            if(maxEnd!=0){ // fit한것 잡고 다음으로 넘기기, 요구사항 만족시 탈출
                answer++;
                end = flowers[idx][1];
                if(end>1130) break;
            }else{ // 만족하는게 없으면 그냥 탈출
                break;
            }
        }

//        for (int i = 0; i < flowers.length; i++) {
//            System.out.print(Arrays.toString(flowers[i]) + " ");
//        }

        if(end<=1130) answer=0;
        System.out.println(answer);


    }
}
