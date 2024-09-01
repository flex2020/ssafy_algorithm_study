import java.util.*;

class Solution {
    
    static StringBuilder sb;
    static int[][] logTime;
    static int answer;
    static long answerTime;
    static int[] dp;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        sb = new StringBuilder();
        answer = 0;
        answerTime = 0;
        
        int playTime = getTime(play_time);
        int advTime = getTime(adv_time);
        
        dp = new int[360000];
        
        for(int i=0;i<logs.length;i++){
            String[] sp = logs[i].split("-");
            int start = getTime(sp[0]);
            int end = getTime(sp[1]);
            // 동영상 재생시간 = 재생이 종료된 시각 - 재생이 시작된 시각
            // (예를 들어, 00시 00분 01초부터 00시 00분 10초까지 동영상이 재생되었다면,
            // 동영상 재생시간은 9초 입니다.) 라고 합니다.
            for(int j=start;j<end;j++) dp[j]++;
        }
        
        // 위와 같은 이유로 advTime까진 <=가 아니라 <로 잰다.
        for(int i=0;i<advTime;i++){
            answerTime += dp[i];
        }
        
        long result = answerTime; // long으로 해주기
        
        for(int i=advTime;i<=playTime;i++){
            result += dp[i] - dp[i-advTime];
            if(answerTime < result){
                answerTime = result;
                answer = i-advTime+1; // 뭔가 1씩 삑나길래 +1해줌
            }
        }
        
        makeTime(answer);
        
        return sb.toString();
    }
    
    private static int getTime(String time){
        String[] strTime = time.split(":");
        int result = 0;
        
        result += Integer.parseInt(strTime[0]) * 60 * 60;
        result += Integer.parseInt(strTime[1]) * 60;
        result += Integer.parseInt(strTime[2]);
        
        return result;
    }
    
    private static void makeTime(int time){
        int hour = time/3600;
        int minute = (time%3600)/60;
        int second = time%60;
        sb.append(hour>=10?hour:"0"+hour);
        sb.append(":");
        sb.append(minute>=10?minute:"0"+minute);
        sb.append(":");
        sb.append(second>=10?second:"0"+second);
    }
    
    
}
