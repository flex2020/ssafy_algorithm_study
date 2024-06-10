import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int maxTime = 23 * 60 + 59;
        Map<String, Integer> inTime = new HashMap<>(); // 입차 시간 저장
        Map<String, Integer> sumTime = new HashMap<>(); // 누적 시간 계산
        Map<String, Integer> fee = new TreeMap<>(); // 차량 별 주차요금
        Set<String> noOut = new HashSet<>(); // 출차하지 않은 차번호
        for (String r : records) {
            String[] record = r.split(" ");
            String[] time = record[0].split(":");
            if (record[2].equals("IN")) { // 입차
                if (!sumTime.containsKey(record[1])) { // 첫 입차
                    sumTime.put(record[1], 0);
                }
                noOut.add(record[1]);
                inTime.put(record[1], Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
            } else { // 출차
                int in = inTime.get(record[1]);
                int out = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                noOut.remove(record[1]);
                sumTime.put(record[1], sumTime.get(record[1]) + out - in);
            }
        }
        for (String number : noOut) {
            sumTime.put(number, sumTime.get(number) + maxTime - inTime.get(number));
        }
        for (String number : sumTime.keySet()) {
            if (sumTime.get(number) > fees[0]) {
                double cost = (double)(sumTime.get(number)-fees[0]) / fees[2];
                fee.put(number, fees[1] + (int)(Math.ceil(cost)) * fees[3]);
            } else {
                fee.put(number, fees[1]);
            }
        }
        
        int[] answer = new int[sumTime.size()];
        int i = 0;
        Collections.sort(new ArrayList<>(fee.keySet()));
        for (String number : fee.keySet()) {
            answer[i++] = fee.get(number);
        }
        return answer;
    }
}
