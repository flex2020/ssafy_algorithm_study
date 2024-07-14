import java.util.*;

class Solution {

    static int answer;

    public int solution(int[] info, int[][] edges) {
        answer = 0;

        List<List<Integer>> edgeList = new ArrayList<>();

        for(int i=0;i<info.length;i++){
            edgeList.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            edgeList.get(edges[i][0]).add(edges[i][1]);
        }

        List<Integer> visit = new ArrayList<>();
        visit.add(0);

        dfs(new int[]{1, 0}, info, 0, edgeList, visit);

        return answer;
    }

    private static void dfs(int[] counts, int[] info, int idx, List<List<Integer>> edgeList, List<Integer> visit){
        if(counts[0] <= counts[1]){
            answer = Math.max(answer, counts[0]);
            return;
        }
        answer = Math.max(answer, counts[0]);

        List<Integer> nextVisit = new ArrayList<>();
        nextVisit.addAll(visit);
        nextVisit.remove(Integer.valueOf(idx));

        for(int child : edgeList.get(idx)){
            nextVisit.add(child);
        }

        for(int go : nextVisit){
            counts[info[go]]++;
            dfs(counts, info, go, edgeList, nextVisit);
            counts[info[go]]--;
        }


    }
}
