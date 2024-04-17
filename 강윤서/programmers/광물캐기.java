import java.util.*;
class Solution {
    // 한 곡괭이로 5개를 캘 수 있다
    // 곡괭이 종류별로 5개씩 가질 수 있음
    // 5*5*3 만큼 캘 수 있음
    int[][] table = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int cnt = 0; // 캘 수 있는 광물 수
        for (int p : picks) {
            cnt += p;
        }
        int[] arr = new int[minerals.length/5+1];
        PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]);
        L: for (int i=0; i<minerals.length; i++) {
            for (int j=0; j<5; j++) {
                if (i*5+j >= minerals.length) break L;
                if (minerals[i*5+j].equals("diamond")) {
                    arr[i] += 25;   
                } else if (minerals[i*5+j].equals("iron")) {
                    arr[i] += 5;
                } else {
                    arr[i] += 1;
                }
            }
        }
        for (int i=0; i<Math.min(cnt, arr.length); i++) {
            PQ.offer(new int[] {i, arr[i]});
        }
        while (!PQ.isEmpty()) {
            int[] cur = PQ.poll();
            for (int idx=0; idx<3; idx++) {
                if (picks[idx] > 0) {
                    picks[idx]--;
                    for (int j=cur[0]*5; j<cur[0]*5+5; j++) {
                        if (j >= minerals.length) break;
                        if (minerals[j].equals("diamond")) {
                            answer += table[idx][0];
                        } else if (minerals[j].equals("iron")) {
                            answer += table[idx][1];
                        } else {
                            answer += table[idx][2];
                        }
                    }
                    break;
                }
            }
        }
        return answer;
    }
}
