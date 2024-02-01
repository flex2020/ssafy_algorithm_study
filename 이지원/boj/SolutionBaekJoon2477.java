import java.util.*;
import java.io.*;

class Num {
    int direction;
    int value;

    Num (int direction, int value) {
        this.direction = direction;
        this.value = value;
    }
}

public class SolutionBaekJoon2477 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        Num[] nums = new Num[6];
        StringTokenizer st;
        for (int i = 0; i < nums.length; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = new Num(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 현재 방향 = (현재 + 2) 방향이고 (현재 + 1) 방향 = (현재 + 3) 방향이면,
        // 현재 ~ (현재 + 3)은 짧은 변이며, (현재, 현재 + 1)과 (현재 + 1, 현재 + 3)이 서로 같은 방향의 변이다.
        // 가장 긴 변 = 서로 같은 방향의 짧은 변의 합
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].direction == nums[(i + 2) % 6].direction && nums[(i + 1) % 6].direction == nums[(i + 3) % 6].direction) {
                int total = (nums[i].value + nums[(i + 2) % 6].value) * (nums[(i + 1) % 6].value + nums[(i + 3) % 6].value);
                int exception = nums[(i + 1) % 6].value * nums[(i + 2) % 6].value;
                result = (total - exception) * K;
                break;
            }
        }
        
        System.out.println(result);
    }
}