class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        String result = recursive(arr.length, arr);
        for (int i=0; i<result.length(); i++) {
            answer[result.charAt(i) - '0'] += 1;
        }
        return answer;
    }
    public String recursive(int size, int[][] arr) {
        if (size == 1) return arr[0][0] + "";
        
        // 쿼드트리가 만들어지는지 확인함
        // 쿼드트리가 완성된다면 해당 문자를 넘기고
        // 쿼드트리가 완성되지 않는다면 각 방향으로 쪼갠 후 재귀적인 결과를 반환
        int checkResult = check(size, arr);
        if (checkResult != -1) return checkResult + "";
        int[][] arr1 = new int[size/2][size/2];
        int[][] arr2 = new int[size/2][size/2];
        int[][] arr3 = new int[size/2][size/2];
        int[][] arr4 = new int[size/2][size/2];
        
        for (int i=0; i<size/2; i++) {
            for (int j=0; j<size/2; j++) {
                arr1[i][j] = arr[i][j];
                arr2[i][j] = arr[i][j+size/2];
                arr3[i][j] = arr[i+size/2][j];
                arr4[i][j] = arr[i+size/2][j+size/2];
            }
        }
        return recursive(size/2, arr1)
            + recursive(size/2, arr2)
            + recursive(size/2, arr3)
            + recursive(size/2, arr4);
    }
    
    public int check(int size, int[][] arr) {
        int target = arr[0][0];
        
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (target != arr[i][j]) return -1;
            }
        }
        return target;
    }
}
