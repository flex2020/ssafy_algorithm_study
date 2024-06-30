import java.util.*;
import java.io.*;

class Solution {

    static class Scheduler{
        int remain; // 남은시간
        int idx; // 인덱스

        Scheduler(int idx, int remain){
            this.remain = remain;
            this.idx = idx;
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        String[][] newPlans = new String[plans.length][3]; // 새로 플랜 만들었는데 굳이 이래야하나 싶긴 하다.

        for(int i=0;i<plans.length;i++){
            String[] time = plans[i][1].split(":");
            // 10진수 계산을 위해서 진행
            int hour = Integer.parseInt(time[0])*60;
            int minute = Integer.parseInt(time[1]);

            newPlans[i][0]= plans[i][0]; // name
            newPlans[i][1] = String.valueOf(hour+minute); // time
            newPlans[i][2] = plans[i][2]; // playtime
        }

        // 시간순 정렬
        Arrays.sort(newPlans, (a, b) -> {

            int timeA = Integer.parseInt(a[1]);
            int timeB = Integer.parseInt(b[1]);

            return timeA-timeB;
        });

//         for(int i=1;i<newPlans.length;i++){
//             int timeB = Integer.parseInt(newPlans[i][1]);
//             int timeA = Integer.parseInt(newPlans[i-1][1]);

//             int hour = timeB/100 - timeA/100;
//             int minute = timeB%100 - timeA%100;

//             newPlans[i][1] = String.valueOf(timeA + (hour*60) + minute);
//         }

        for(int i=0;i<plans.length;i++){
            System.out.println(Arrays.toString(newPlans[i]));
        }

        int answerIdx = 0; // 정답을 넣을 인덱스
        // 스택으로 사용할 덱
        Deque<Scheduler> dq = new ArrayDeque<>();

        // 과거 시작시간 + playTime
        int pastTime = Integer.parseInt(newPlans[0][1]) + Integer.parseInt(newPlans[0][2]);
        int pastIdx = 0;
        // 현재 시작시간
        int curTime;
        int curIdx = 1;

        // dq.offerFirst(new Scheduler(0, Integer.parseInt(newPlans[0][1])));

        while(true){
            if(dq.isEmpty() && curIdx==plans.length) {
                // 마지막꺼 대입
                answer[answerIdx++] = newPlans[curIdx-1][0];
                break;
            }
            // curTime = Integer.parseInt(newPlans[curIdx][1]) + Integer.parseInt(newPlans[curIdx][2]);
            if(curIdx==plans.length){
                // 마지막꺼 대입
                answer[answerIdx++] = newPlans[curIdx-1][0];
                while(!dq.isEmpty()){ // 남은거 다 넣어주기
                    Scheduler s = dq.pollFirst();
                    answer[answerIdx++] = newPlans[s.idx][0];
                }
                // answer[answerIdx++] = newPlans[curIdx-1][0];
                break;
            }

            curTime = Integer.parseInt(newPlans[curIdx][1]);

            if(curTime < pastTime){ // 전에 있던게 끝나기 전에 새로운게 등장한 경우
                dq.offerFirst(new Scheduler(pastIdx, pastTime-curTime)); // 스케쥴러에 넣는다.
                pastTime = curTime + Integer.parseInt(newPlans[curIdx][2]);
                pastIdx = curIdx;
            }else if(curTime == pastTime){
                answer[answerIdx++] = newPlans[pastIdx][0]; // 끝난거 넣고
                pastTime = curTime + Integer.parseInt(newPlans[curIdx][2]);
                pastIdx = curIdx;
            }else{ // pastTime이 먼저 끝난 경우(다음것까지 공백이 있는경우)
                answer[answerIdx++] = newPlans[pastIdx][0]; // 끝난거 넣고
                if(!dq.isEmpty()){ // 스케쥴러에 덜된게 있는 경우
                    Scheduler s = dq.pollFirst();
                    pastTime = pastTime + s.remain;
                    pastIdx = s.idx;
                    continue;
                }else{ // 덜된게 없는 경우
                    pastTime = curTime + Integer.parseInt(newPlans[curIdx][2]);
                    pastIdx = curIdx;
                }
            }

            curIdx++;
        }


        return answer;
    }
}
