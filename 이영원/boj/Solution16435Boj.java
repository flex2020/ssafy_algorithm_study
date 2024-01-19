import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 과일 수 N
        int L = sc.nextInt(); // 스네이크버드 초기길이 L
        int answer=L;

        int[] fruits = new int[N];

        for(int i=0;i<fruits.length;i++) {
            fruits[i]=sc.nextInt();
        }

        Arrays.sort(fruits); // 오름차순 정렬

        for (int i = 0; i < fruits.length; i++) {
            if(answer>=fruits[i]) answer++;
            else break;
        }

        System.out.println(answer);
    }
}