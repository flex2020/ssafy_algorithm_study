import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length()-1);
        s = s.replaceAll("\\{", "");
        String[] arr = s.split("\\},");
        arr[arr.length-1] = arr[arr.length-1].substring(0, arr[arr.length-1].length()-1);
        Arrays.sort(arr, (a, b) -> a.length() - b.length());
        
        Set<Integer> set = new TreeSet<>();
        int[] answer = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            String[] temp = arr[i].split(",");
            for (int j=0; j<temp.length; j++) {
                int num = Integer.parseInt(temp[j]);
                if (!set.contains(num)) {
                    set.add(num);
                    answer[i] = num;
                    break;
                }
            }
        }
        
        return answer;
    }
}
