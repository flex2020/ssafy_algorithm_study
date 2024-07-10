import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        int idx = 0;
        
        String[][] select  = {{"cpp", "java", "python"},
                              {"backend", "frontend"},
                              {"junior", "senior"},
                              {"chicken", "pizza"}};
        
        int[][] board = new int[1 << 6][100001];
        
        for (int i = 0; i<info.length; i++) {
            String[] arr = info[i].split(" ");
            int value = 1 << 5;
            for (int j=0; j<4; j++) {
                int index = Arrays.asList(select[j]).indexOf(arr[j]);
                if (index == 0) continue;
                if (j == 0) {
                    if (index == 2) value = value | 1 << (j+1);
                    else value = value | 1 << j;
                } else {
                    value = value | 1 << (j+1);   
                }
            }
            for (int start = Integer.parseInt(arr[4]); start > 0; start--)
                board[value][start] += 1;
        }
        
        // - 조건을 어떻게 처리 ...? ㅜㅜ
        for (int i=0; i<query.length; i++) {
            String[] arr = query[i].split(" ");
            Queue<Integer> Q = new ArrayDeque<>();
            int value = 1 << 5;
            // 개발 언어
            if (arr[0].equals("-")) {
                Q.offer(value);
                Q.offer(value | 1 << 0);
                Q.offer(value | 1 << 1);
            } else {
                int index = Arrays.asList(select[0]).indexOf(arr[0]);
                if (index == 0) Q.offer(value);
                else if (index == 1) Q.offer(value | 1 << 0);
                else if (index == 2) Q.offer(value | 1 << 1);
            }
            // 지원 직군
            if (arr[2].equals("-")) {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    Q.offer(v);
                    Q.offer(v | 1 << 2);
                }
            } else {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    int index = Arrays.asList(select[1]).indexOf(arr[2]);
                    if (index == 1) Q.offer(v | 1 << 2);
                    else Q.offer(v);
                }
            }
            // 지원 경력
            if (arr[4].equals("-")) {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    Q.offer(v);
                    Q.offer(v | 1 << 3);
                }
            } else {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    int index = Arrays.asList(select[2]).indexOf(arr[4]);
                    if (index == 1) Q.offer(v | 1 << 3);
                    else Q.offer(v);
                }
            }
            // 소울 푸드
            if (arr[6].equals("-")) {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    Q.offer(v);
                    Q.offer(v | 1 << 4);
                }
            } else {
                int size = Q.size();
                for (int s=0; s<size; s++) {
                    int v = Q.poll();
                    int index = Arrays.asList(select[3]).indexOf(arr[6]);
                    if (index == 1) Q.offer(v | 1 << 4);
                    else Q.offer(v);
                }
            }
            int cnt = 0;
            while (!Q.isEmpty()) {
                int v = Q.poll();
                cnt += board[v][Integer.parseInt(arr[7])];
            }
            answer[idx++] = cnt;
        }
       
        return answer;
    }
}
