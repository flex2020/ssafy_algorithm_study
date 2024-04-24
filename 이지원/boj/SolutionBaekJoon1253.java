import java.util.*;
import java.io.*;

public class SolutionBaekJoon1253 {

    static class Sum implements Comparable<Sum> {
        int idx1;
        int idx2;
        int sum;

        Sum(int idx1, int idx2, int sum) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.sum = sum;
        }

        boolean contains(int idx) {
            if (this.idx1 == idx || this.idx2 == idx) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Sum o) {
            return Integer.compare(sum, o.sum);
        }
    }

    static int[] nums;
    static Sum[] sums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 0;
        if (N > 2) {
            nums = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // 두 수의 합 구하기
            sums = new Sum[N * (N - 1) / 2];
            int idx = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    sums[idx++] = new Sum(i, j, nums[i] + nums[j]);
                }
            }

            Arrays.sort(sums);

            for (int t = 0; t < nums.length; t++) {
                if (isFind(t)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    public static boolean isFind(int t) {
        int target = nums[t];

        int start = 0, end = sums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sums[mid].sum == target) {
                if (!sums[mid].contains(t)) {
                    return true;
                } else {
                    int pre = mid;
                    while (pre > -1) {
                        if (sums[pre].sum == target) {
                            if (!sums[pre].contains(t)) {
                                return true;
                            }
                        } else {
                            break;
                        }

                        pre--;
                    }

                    int post = mid;
                    while (post < sums.length) {
                        if (sums[post].sum == target) {
                            if (!sums[post].contains(t)) {
                                return true;
                            }
                        } else {
                            break;
                        }

                        post++;
                    }

                    return false;
                }
            }

            if (sums[mid].sum < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

}
