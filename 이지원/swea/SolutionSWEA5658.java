import java.util.*;
import java.io.*;
 
public class SolutionSWEA5658 {
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int passwordLength = N / 4;  // 생성할 수 1개의 길이
            
            // 숫자 입력받기
            char[] tmp = br.readLine().toCharArray();
            char[] inputs = new char[N + passwordLength - 1];
            int startIndex = passwordLength - 1;
            for (int i = 0; i < tmp.length; i++) {
                inputs[i + startIndex] = tmp[i];
            }
            
            // 생성 가능한 수 구하기
            int count = 0;
            String passwordTmp = "";
            Integer[] passwords = new Integer[N];  // 16진수를 10진수로 변환한 비밀번호
            int index = 0;
            for (int c = 0; c < passwordLength; c++) {
                for (int i = startIndex; i < startIndex + N; i++) {
                    passwordTmp += inputs[i];
                    count++;
                    
                    // 숫자 1개의 길이(passwordLength)만큼 입력받은 16진수를 잘라서
                    // 10진수로 바꿔서 저장하기
                    if (count == passwordLength) {
                        passwords[index++] = Integer.parseInt(passwordTmp, 16);
                        passwordTmp = "";
                        count = 0;
                    }
                }
                startIndex--;
                if (startIndex > -1) {
                	inputs[startIndex] = inputs[startIndex + N];
                }
            }

            Arrays.sort(passwords, (a, b) -> Integer.compare(b, a));  // 숫자 내림차순으로 정렬
            
            // 주어진 크기 순서(K)에 해당하는 숫자 출력하기
            if (K == 1) {
                System.out.println("#" + testCase + " " + passwords[0]);
            } else {
                int number = 1;  // 크기 순서
                for (int i = 1; i < passwords.length; i++) {
                    if (passwords[i - 1].equals(passwords[i])) {  // 중복된 숫자 pass
                        continue;
                    }

                    number++;
                    if (number == K) {
                        System.out.println("#" + testCase + " " + passwords[i]);
                        break;
                    }
                }
            }
        }
    }
 
}