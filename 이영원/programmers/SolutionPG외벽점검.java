import java.util.*;

class Solution {

    static int answer; // 정답
    static boolean[] check; // 체크배열
    static int[] window; // window 배열
    static int len; // 길이

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;

        window = new int[weak.length*2-1];
        check = new boolean[dist.length];
        len = weak.length;

        // 길이 두배로 늘려서 넣기. 예를 들어 n=12고, (1, 5, 6, 10) 이면, (1, 5, 6, 10, 13, 18, 19)로 만들고
        // 0부터 10까지 슬라이딩 윈도우처럼 weak.length개씩만 보는 형태로 한다.
        // 그렇게 하면 모든 경우를 따지기 때문에 시계 반시계 무의미하다.
        for(int i=0;i<weak.length;i++){
            window[i] = weak[i];
        }

        for(int i=weak.length;i<window.length;i++){
            window[i]=weak[i-weak.length]+n;
        }

        // dist 순열 만드는 메소드
        permutation(0, 0, dist, new int[dist.length]);

        // max값이라면 되는 경우가 없는 것이므로 -1로 변경
        if(answer==Integer.MAX_VALUE) answer=-1;

        return answer;
    }

    private static void permutation(int idx, int cnt, int[] dist, int[] arr){
        if(cnt==arr.length){
            for(int i=0;i<len;i++){
                calculate(i, arr);
            }
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(check[i]) continue;
            check[i]=true;
            arr[cnt]=dist[i];
            permutation(i, cnt+1, dist, arr);
            check[i]=false;
        }
    }

    // idx는 window 시작지점, arr은 순서가 정해진 dist
    private static void calculate(int idx, int[] arr){
        int sum=0;
        int arrIdx=0;

        for(int i=idx;i<idx+len-1;i++){
            if(arrIdx==arr.length) break;
            sum += Math.abs(window[i]-window[i+1]);
            if(sum > arr[arrIdx]){ // 지난 거라면 그 전까지 들어간 sum을 0으로 만들어주고 arrIdx를 하나 올려준다.
                sum = 0;
                arrIdx++;
            }
        }

        // 다 봤는데, 모든걸 담을 수 없었다면 answer를 max값으로 바꿔준다.
        if(arrIdx==arr.length && answer==Integer.MAX_VALUE) answer = Integer.MAX_VALUE;
        else answer = Math.min(answer, arrIdx+1);

    }
}
