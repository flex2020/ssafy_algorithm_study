class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        int start = 1;
        int end = 0;
        for (int i=0; i<diffs.length; i++) 
            end = Math.max(end, diffs[i]);
        
        int mid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;
            long totalTime = 0;
            for (int i=0; i<diffs.length; i++) {
                int level = diffs[i] > mid ? diffs[i] - mid : 0;
                if (i==0)
                    totalTime += (times[i]) * level + times[i];
                else
                    totalTime += (long)(times[i-1] + times[i]) * level + times[i];
                // System.out.println(level + " " + totalTime);
            }
            // System.out.println(start + " " + end + " " + mid + " " + totalTime);
            if (limit < totalTime) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = Math.min(answer, mid);
            }
        }
        return answer;
    }
}
