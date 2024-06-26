import java.util.*;

class Solution {
    int R, C, answer;
    List<List<Integer>> answerList;
    List<Integer> sel;
    String[][] relations;
    public int solution(String[][] relation) {
        answer = 0;
        answerList = new ArrayList<>();
        R = relation.length;
        C = relation[0].length;
        relations = relation;
        // 1개부터 C개까지 시도
        for (int i=1; i<=C; i++) {
            sel = new ArrayList<>();
            combination(0, 0, i);
        }
       
        return answer;
    }
    
    public void combination(int idx, int cnt, int maxCnt) {
        if (cnt == maxCnt) {
            // 후보키 조건 충족하는지 확인하기
            if (!checkUniqueness() || !checkMinimality()) return;
            answer += 1;
            List<Integer> temp = new ArrayList<>();
            for (int i=0; i<sel.size(); i++) temp.add(sel.get(i));
            answerList.add(temp);
            return;
        }
        if (idx == C) return;
        // 현재 요소 선택
        sel.add(idx);
        combination(idx + 1, cnt + 1, maxCnt);
        
        // 현재 요소 선택 X
        sel.remove(sel.size() - 1);
        combination(idx + 1, cnt, maxCnt);
    }
    
    // 유일성 확인
    public boolean checkUniqueness() {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<R; i++) {
            int hashValue = 0;
            String[] item = relations[i];
            for (int j=0; j<sel.size(); j++) {
                int idx = sel.get(j);
                hashValue = Objects.hash(hashValue, item[idx]);
            }
            set.add(hashValue);
        }

        return set.size() == R;
    }
    
    // 최소성 확인
    public boolean checkMinimality() {
        // sel 안에 정답의 일부가 들어있는 경우
        for (List<Integer> ans : answerList) {
            boolean flag = true;
            for (Integer idx : ans) {
                flag &= sel.contains(idx);
            }
            if (flag) return false;
        }
        return true;
    }
}
