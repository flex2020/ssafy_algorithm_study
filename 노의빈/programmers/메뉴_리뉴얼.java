import java.util.*;

class Solution {
    Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        map = new TreeMap<>();
        for (int i=0; i<orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            Arrays.sort(temp);
            orders[i] = new String(temp);
            for (int j=0; j<course.length; j++) {
                combination(0, "", course[j], orders[i]);
            }
        }
        int[] maxlen = new int[course.length];
        for (int i=0; i<course.length; i++) {
            for (String key : map.keySet()) {
                if (key.length() != course[i]) continue;
                maxlen[i] = Math.max(maxlen[i], map.get(key));
            }
        }
        List<String> list = new ArrayList<>();
        for (int i=0; i<course.length; i++) {
            if (maxlen[i] < 2) continue;
            for (String key : map.keySet()) {
                if (key.length() != course[i]) continue;
                int cnt = map.get(key);
                if (cnt == maxlen[i]) {
                    list.add(key);
                }
            }
        }
        Collections.sort(list, (a, b) -> a.compareTo(b));
        answer = new String[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    public void combination(int idx, String s, int k, String order) {
        if (s.length() == k) {
            int cnt = map.get(s) == null ? 1 : map.get(s) + 1;
            map.put(s, cnt);
            return;
        }
        
        if (idx == order.length()) return;
        
        combination(idx + 1, s + order.charAt(idx), k, order);
        combination(idx + 1, s, k, order);
    }
    
}
