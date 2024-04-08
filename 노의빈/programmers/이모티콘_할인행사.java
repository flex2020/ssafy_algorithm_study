import java.util.*;

class Solution {
    int[] answer;
    int N, M;
    List<Integer> sel;
    int[][] users;
    int[] emoticons;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        N = users.length;
        M = emoticons.length;
        sel = new ArrayList<>();
        this.users = users;
        this.emoticons = emoticons;
        recursive(0);
        return answer;
    }
    
    // 이모티콘의 할인율을 10, 20, 30, 40으로 설정 후 시뮬레이션
    private void recursive(int idx) {
        if (idx == M) {
            simulate();
            return;
        }
        
        for (int i=10; i<=40; i+=10) {
            sel.add(i);
            recursive(idx + 1);
            sel.remove(idx);
        }
    }
    
    private void simulate() {
        int reg = 0;
        int price = 0;
        
        L: for (int i=0; i<N; i++) {
            int p = 0;
            for (int j=0; j<M; j++) {
                // 할인율이 낮다면 사지 않음
                if (sel.get(j) < users[i][0]) continue;
                p += emoticons[j] * (100 - sel.get(j)) / 100;
                // 구매비용이 더 많아지는 경우 구매 취소 후 가입
                if (p >= users[i][1]) {
                    p = 0;
                    reg += 1;
                    continue L;
                }
            }
            // 총 가격에 추가
            price += p;
        }
        
        
        if (answer[0] < reg) {
            answer[0] = reg;
            answer[1] = price;
        } else if (answer[0] == reg) answer[1] = Math.max(answer[1], price);
    }
}
