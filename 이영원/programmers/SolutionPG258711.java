import java.io.*;
import java.util.*;

class Solution {
    
    public static int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        int m = 0;

        for (int i = 0; i < edges.length; i++) {
            m=Math.max(m, edges[i][0]);
            m=Math.max(m, edges[i][1]);
        }

        int[][] inNout = new int[m+1][2]; // 0이 들어오는거, 1이 나가는거

        for (int i = 0; i < edges.length; i++) {
            inNout[edges[i][0]][1]++;
            inNout[edges[i][1]][0]++;
        }

        // for (int i = 0; i < inNout.length; i++) {
        //     System.out.print(Arrays.toString(inNout[i]));
        // }

        for (int i = 1; i < inNout.length; i++) {
            if(inNout[i][0]==0 && inNout[i][1]>=2) answer[0]=i;
            else if(inNout[i][0]>=2 && inNout[i][1]>=2) answer[3]++;
            else if(inNout[i][0]!=0 && inNout[i][1]==0) answer[2]++;
        }

        answer[1] = inNout[answer[0]][1]-answer[3]-answer[2];

        return answer;
    }
}
