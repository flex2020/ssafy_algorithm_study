import java.util.*;

class Solution {
    int start, end, gemsInRange, totalGems, lengthOfRange, minLengthOfRange, startOfAnswer, endOfAnswer;
    Map<String, Integer> map = new TreeMap<>();
    
    public int[] solution(String[] gems) {
        countTotalGems(gems);
        gemsInRange = 1; // 범위 안의 보석의 수
        start = 0;
        end = 0;
        minLengthOfRange = Integer.MAX_VALUE; // 범위의 최소 길이
        startOfAnswer = 0; // 정답 범위의 시작
        endOfAnswer = 0; // 정답 범위의 끝
        map.put(gems[0], 1);
        while (start <= end) {
            // start ~ end 안에 모든 보석이 담겨진 경우
            if (gemsInRange == totalGems) {
                // 정답이 갱신되는 경우
                if (end - start + 1 < minLengthOfRange) {
                    minLengthOfRange = end - start + 1; // 최소 범위 길이 갱신
                    startOfAnswer = start; // 정답 갱신
                    endOfAnswer = end; // 정답 갱신
                }
                else {
                    String gem = gems[start];
                    int count = map.get(gem) - 1;
                    count = count < 0 ? 0 : count;
                    map.put(gem, count);
                    // 해당 보석이 더이상 범위에 없다면 범위안의 보석 수 - 1
                    if (count == 0) gemsInRange -= 1;
                    start += 1;
                }
            }
            // 보석을 더 담아야하는 경우
            else if (gemsInRange < totalGems) {
                end += 1;
                if (end >= gems.length) break;
                String gem = gems[end];
                int count = map.get(gem);
                if (count == 0) gemsInRange += 1;
                map.put(gem, count + 1);
            }
        }
        
        return new int[]{startOfAnswer + 1, endOfAnswer + 1};
    }
    
    public void countTotalGems(String[] gems) {
        Set<String> set = new TreeSet<>();
        for (int i=0; i<gems.length; i++) {
            set.add(gems[i]);
            map.put(gems[i], 0);
        }
        totalGems = set.size();
    }
}
