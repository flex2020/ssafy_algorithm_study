import java.util.*;

class Solution {

    static boolean[] check;
    static int start;
    static int size;
    static String binary;

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0;i<numbers.length;i++){
            answer[i] = calculate(numbers[i]);
        }

        return answer;
    }

    private static int calculate(long num){
        size = (1 << (log2(log2(num) + 1) + 1)) - 1; // 자릿수 구하기
        check = new boolean[size]; // 해당 자리에 1이 있어도 되는지 판별하는 배열

        binary = Long.toBinaryString(num); // 이진수로 바꾸기

        // 시작점, 예를 들어서 8은 1000인데 이걸 0001000처럼 표현하고, 이렇게 표현했을 때
        // 이진수로 바꾼 String이 어디 인덱스부터 가져오면 되는지 표현하기 위함
        start = size - binary.length();
        // System.out.println(num + " " + size + " " + binary + " " + start);

        int idx = size/2; // 제일 처음
        if(binary.charAt(idx-start)=='1') check[idx]=true;

        calculate(0, size-1);

        // System.out.println(Arrays.toString(check));

        for(int i=start;i<size;i++){
            // 해당 자리가 1이고 check가 false라면 거긴 1이 있으면 안되는 자리라는 뜻이므로 0 리턴
            if(binary.charAt(i-start)=='1' && !check[i]) return 0;
        }

        // 다 되면 1 리턴
        return 1;
    }

    private static void calculate(int left, int right){
        if(left==right) return; // left==right면 뒤에 볼게 없으므로 리턴
        int mid = (right+left)/2; // 중간값
        // 거기가 1이면 걔 자식들은 들어올 수 있으니까 true로 바꿔주기
        if(mid>=start && binary.charAt(mid-start)=='1'){
            check[(left+(mid-1))/2]=true;
            check[((mid+1)+right)/2]=true;
        }
        // 양 옆에 또 계산
        calculate(left, mid-1);
        calculate(mid+1, right);
    }

    // log2 구현
    private static int log2(long num){
        return (int)(Math.log(num)/Math.log(2));
    }
}
