import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 완탐? 그리디? 그게뭔데으아아악
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words =  new String[N];
        int[][] weight = new int[26][2]; // 가중치 배열

        for (int i = 0; i < 26; i++) { // 몰라 그냥 인덱스
            weight[i][0]=i;
        }

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int size = words[i].length();
            int cnt=0;
            for (int j = size-1; j >= 0 ; j--) { // 일의 자리면 1, 십의 자리면 10 이런식으로 알파벳에 더해주기
                weight[words[i].charAt(j)-'A'][1]+=Math.pow(10,cnt++);
            }
        }

        Arrays.sort(weight, (a,b) -> b[1]-a[1]); // 가중치 배열 순으로 내림차순 정렬

        HashMap<Character, Integer> maps = new HashMap<>();
        int num=9;
        for (int i = 0; i < 26; i++) {
            if(weight[i][1]==0) break;
            if(!maps.containsKey((char)(weight[i][0]+'A'))){ // 키가 없다면 가중치가 가장 큰것부터 9부터 하나씩 할당
                maps.put((char)(weight[i][0]+'A'), num--);
            }
        }

        int answer=0;
        // 정해진 가중치만큼 숫자를 만들어서 정답에 더해준다.
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            int size = words[i].length();
            for(int j = 0; j < size; j++) {
                sb.append(maps.get(words[i].charAt(j)));
            }
//            System.out.println(sb);
            answer+=Integer.parseInt(sb.toString());
        }

        System.out.print(answer);
    }
}
