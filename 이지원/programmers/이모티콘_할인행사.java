import java.util.*;

class Solution {
    
    public int minRatio;
    public int[] ratios;
    public int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 최소 비율 알아내기
        minRatio = Integer.MAX_VALUE;
        for (int i = 0; i < users.length; i++) {
            minRatio = Math.min(minRatio, users[i][0]);
        }
        minRatio = (minRatio / 10 + 1) * 10;
        
        // 적용할 수 있는 비율 알아내기
        int size = 4 - minRatio / 10 + 1;
        ratios = new int[size];
        for (int i = 0; i < size; i++) {
            ratios[i] = minRatio + i * 10;
        }
        
        // 물건별 할인률에 따른 가입수 및 총 판매액 계산하기
        answer = new int[2];
        calculateResult(users, emoticons, 0, new int[users.length]);
        
        return answer;
    }
    
    public void calculateResult(int[][] users, int[] emoticons, int idx, int[] prices) {
        if (idx == emoticons.length) {
            // 가입수 및 총 판매액 계산하기
            int cnt = 0;
            int totalPrice = 0;
            for (int num = 0; num < users.length; num++) {
                if (prices[num] >= users[num][1]) {
                    cnt++;
                } else {
                    totalPrice += prices[num];
                }
            }
            
            // 가입수 및 총 판매액 갱신하기
            if (answer[0] < cnt || (answer[0] == cnt && answer[1] < totalPrice)) {
                answer[0] = cnt;
                answer[1] = totalPrice;
            }
            
            return;
        }
        
        for (int i = 0; i < ratios.length; i++) {
            int ratio = ratios[i];
            int price = emoticons[idx] * (100 - ratio) / 100;
            
            // 가입수 및 판매액 계산하기
            for (int num = 0; num < users.length; num++) {
                // 이모티콘 구매 계산하기
                if (ratio >= users[num][0]) {
                    prices[num] += price;
                }
            }
            
            // 다음 계산하기
            calculateResult(users, emoticons, idx + 1, prices);
            
            // 복원하기
            for (int num = 0; num < users.length; num++) {
                if (ratio >= users[num][0]) {
                    prices[num] -= price;
                }
            }
        }
    }
}