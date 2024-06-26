import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        
        Map<String, Integer> map = new TreeMap<>(); // 전체 보석의 종류 파악
        Map<String, Integer> window = new TreeMap<>(); // 현재 포함 범위
        int start = 0; // 투포인터
        int end = 0;
        for (String gem : gems) {
            map.merge(gem, 1, Integer::sum);
        }
        window.put(gems[start], 1);
        while (start <= end && end < gems.length) {
            if (window.size() < map.size()) {
                end++;
                if (end >= gems.length) break;
                window.merge(gems[end], 1, Integer::sum);
            } else {
                if (end - start < answer[1] - answer[0]) {
                    answer = new int[] {start+1, end+1};
                } else {
                    window.replace(gems[start], window.get(gems[start])-1);
                    if (window.get(gems[start]) == 0) window.remove(gems[start]);
                    start++;
                }
            }
        }
        return answer;
    }
}
