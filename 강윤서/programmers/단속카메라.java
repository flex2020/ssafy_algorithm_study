import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int end = -30001;
        List<int[]> list = new ArrayList<>();
        for (int[] route : routes) {
            list.add(route);
        }
        list.sort((l1, l2) -> l1[0] - l2[0]);
        for (int[] l : list) {
            if (l[0] > end) { // 새로운 감시카메라 필요
                answer++;
                end = l[1];
            } else {
                end = Math.min(end, l[1]);
            }
        }
        return answer;
    }
}
