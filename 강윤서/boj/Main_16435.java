package 강윤서.boj;

import java.util.*;
public class Main_16435 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        List<Integer> fruit = new ArrayList<>();
        for (int i=0; i<N; i++) {
            fruit.add(sc.nextInt());
        }
        fruit.sort(null);
        
        for (int i=0; i<N; i++) {
            if (L >= fruit.get(i)) {
                L++;
            } else {
                break;
            }
        }
        System.out.println(L);
    }
}
