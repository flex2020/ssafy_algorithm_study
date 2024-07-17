class Solution {
    int[] answer;
    public int[] solution(String s) {
        answer = new int[2];
        while (true) {
            answer[0] += 1;
            s = binaryTransform(s);
            if (s.equals("1")) break;
        }
        return answer;
    }
    
    public String binaryTransform(String x) {
        for (int i=0; i<x.length(); i++) {
            if (x.charAt(i) == '0') answer[1] += 1;
        }
        x = x.replaceAll("0", "");
        int n = x.length();
        String result = "";
        while (n / 2 != 0) {
            result = n % 2 + result;
            n /= 2;
        }
        result = n + result;
        
        return result;
    }
}
