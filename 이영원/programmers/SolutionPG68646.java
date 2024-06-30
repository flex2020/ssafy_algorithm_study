import java.util.*;

// 거 LCS인지 뭔지 쓰는 문제
class Solution {

    static int len;

    public int solution(int[] a) {
        int answer = 0;
        int minIdx=0;
        int minNum=a[0];

        for(int i=1;i<a.length;i++){ // 최소숫자를 찾는 행위
            if(minNum > a[i]){
                minNum = a[i];
                minIdx = i;
            }
        }
        // System.out.println(minNum + " " + minIdx);

        // System.out.println("left");
        answer += left(minIdx-1, a);
        // System.out.println("right");
        answer += right(minIdx+1, a);

        return answer+1; // 가장 작은 놈 포함을 위한 +1
    }

    // 왼쪽이 내림차순으로 LCS 만들고 반환
    private static int left(int start, int[] a){
        if(start<0) return 0;
        int size=0;
        int[] arr = new int[a.length];
        int idx = 0;

        for(int i=start;i>=0;i--){
            idx = binarySearch(a[i], size, arr);
            arr[idx] = a[i];
            size = idx+1;
            // System.out.println(Arrays.toString(arr));
        }

        return size;
    }

    // 오른쪽이 오름차순으로 LCS 만들고 숫자 반환
    private static int right(int start, int[] a){
        if(start==a.length) return 0;
        int size=0;
        int[] arr = new int[a.length];
        int idx = 0;

        for(int i=start;i<a.length;i++){
            idx = binarySearch(a[i], size, arr);
            arr[idx] = a[i];
            size = idx+1;
            // System.out.println(Arrays.toString(arr));
        }

        return size;
    }

    // 이거 생각하는게 제일 오래걸림... 나 이분탐색 몰라..
    private static int binarySearch(int num, int size, int[] arr){
        int mid = size/2;
        int left = 0;
        int right = size-1;
        while(left <= right){
            if(num < arr[mid]){
                right = mid-1;
            }else{
                left = mid+1;
            }
            mid=(right+left)/2;
        }

        return left;
    }
}
