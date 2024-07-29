class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            if (numbers[i] % 2 == 0) answer[i] = numbers[i] + 1;
            else {
                String b = toBinary(numbers[i]);
                int idx = getLastZeroIndex(b);
                b = b.substring(0, idx) + "10" + b.substring(idx + 2, b.length());
                answer[i] = toDecimal(b);
            }
        }
        return answer;
    }
    
    public String toBinary(long number) {
        String result = "";
        int cnt = 0;
        while (number / 2 > 0) {
            if (number % 2 == 1) cnt += 1;
            result = number % 2 + result;
            number /= 2;
        }
        result = number + result;
        if (number == 1) cnt += 1;
        if (result.length() == cnt) result = "0" + result;
        return result;
    }
    public long toDecimal(String b) {
        long result = 0;
        for (int i=0; i<b.length(); i++) {
            int r = b.length() - i - 1;
            if (b.charAt(i) == '0') continue;
            result += Math.pow(2, r);
        }
        return result;
    }
    public int getLastZeroIndex(String b) {
        int idx = 0;
        for (int i=0; i<b.length(); i++) {
            if (b.charAt(i) == '0') idx = i;
        }
        return idx;
    }
}
