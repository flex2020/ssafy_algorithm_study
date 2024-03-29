import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();
        
        int[] table = getPi(P);
        
//        System.out.println(Arrays.toString(table));
        
        List<Integer> list = new ArrayList<>();
        int cnt=0;
        
        int j=0;
        for(int i=0;i<T.length();i++) {
        	
        	while(j>0 && T.charAt(i) != P.charAt(j)) {
        		j = table[j-1];
        	}
        	
        	if(T.charAt(i) == P.charAt(j)) {
        		if(j == P.length()-1) { // 찾았다.
//        			System.out.println(i-P.length()+2);
        			list.add(i-P.length()+2);
        			cnt++;
        			j = table[j];
        		}else {
        			j++;
        		}
        	}
        }
        
        System.out.println(cnt);
        
        for(int num : list) {
        	System.out.print(num + " ");
        }
        
        

    }
    
    // pi 테이블 만들기
	private static int[] getPi(String pattern) {
		int len = pattern.length();
		int[] pi = new int[len];
		int j=0;
		
		for(int i=1;i<len;i++) {
			
			while(j>0 && pattern.charAt(i)!=pattern.charAt(j)) {
				j=pi[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		
		return pi;
	}
}
