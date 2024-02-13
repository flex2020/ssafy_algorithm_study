import java.util.*;
import java.io.*;

class Solution {
    
    static boolean[] v; // 순열 연산자 우선순위 방문체크배열
    static boolean[] check; // 해당하는 연산자가 실제로 있는지 확인하는 용도의 배열
    static int cnt; // 연산자 종류 개수
    static char[] opers = {'*', '+', '-'}; // 연산자 종류( 0 : *, 1 : +, 2 : - )
    static long answer; 
    static int operCnt; // 총 연산자수
    static char[] sel; // 선택한 순열(순서)
    static Deque<String> dq = new ArrayDeque<>(); // 초기 덱(초기 데이터)
    
    public long solution(String expression) {
        answer = 0;
        v = new boolean[3]; 
        char[] exp = expression.toCharArray();
        
        check = new boolean[3];
        cnt=0;
        
        int idx=0;
        // 분리해서 dq에 넣기(초기 데이터)
        for(int i=0;i<exp.length;i++){
            // 연산자를 찾으면 해당 연산자가 있음을 check에 체크하고, 내용을 dq에 넣기
            if(exp[i]=='*' || exp[i]=='+' || exp[i]=='-'){
                dq.offerLast(expression.substring(idx, i));
                dq.offerLast(exp[i] + "");
                idx=i+1;
                switch(exp[i]){ // 연산자 종류 개수 올리기
                    case '*':
                        if(!check[0]){
                            check[0]=true;
                            cnt++;
                        }
                        break;
                    case '+':
                        if(!check[1]){
                            check[1]=true;
                            cnt++;
                        }
                        break;
                    case '-':
                        if(!check[2]){
                            check[2]=true;
                            cnt++;
                        }
                        break;
                }
                operCnt++; // 연산자 개수 늘리기
            }
        }
        dq.offerLast(expression.substring(idx)); // 마지막 남은거 넣기
        
        sel = new char[cnt]; // 연산자 개수 크기의 배열 만들기
        
        perm(0);
        return answer;
    }
    
    // 연산자 우선순위 순열 돌리기
    private static void perm(int idx){
        // basis part
        if(idx==cnt){ // 연산자 우선순위 순서가 정해졌다.
            answer=Math.max(answer, calculate());
            // System.out.println("끝");
        }
        // inductive part
        for(int i=0;i<3;i++){
            if(!v[i] && check[i]){
                v[i]=true;
                sel[idx]=opers[i];
                perm(idx+1);
                v[i]=false;
            }
        }
    }
    
    private static long calculate(){ // 계산하는 메소드
        Deque<String> dq2 = new ArrayDeque<>();
        for(String s : dq){ // 새로운 덱에 넣기
            dq2.offerLast(s);
        }
        int idx=0; // sel(연산자 배열) 인덱스, 한차례 사이클이 돌면 올려준다.
        int i=0; // 매 시행(3번의 poll)마다 하나씩 올려준다. size를 만나면 모든 연산자를 한번 체크한 것으로 한 사이클이 돈 것이다.
        int j= operCnt; // 원하는 연산자의 연산을 진행한 경우 내려준다.
        int a=0; // 테스트용으로 만든거
        int size = operCnt; // 사이클이 한번 돌면(해당 연산자에 대한 연산을 모두 수행한 경우) j로 업데이트해준다.
        while(!dq2.isEmpty()){
            if(size==0){
                size = cnt;
                idx++;
            }
            // print(dq2);
            // if(a==3) break;
            String operand1 = dq2.pollFirst();
            String operator = dq2.pollFirst();
            String operand2 = dq2.pollFirst();
            i++;
            if(operator==null) return Math.abs(Long.parseLong(operand1));
            if(operator.charAt(0) == sel[idx]){ // 연산 진행
                long result=0;
                switch(sel[idx]){
                   case '*':
                        result = Long.parseLong(operand1) * Long.parseLong(operand2);
                        break;
                    case '+':
                        result = Long.parseLong(operand1) + Long.parseLong(operand2);
                        break;
                    case '-':
                        result = Long.parseLong(operand1) - Long.parseLong(operand2);
                        break; 
                }
                j--;
                if(size==i){ // 사이클의 마지막 연산인 경우, 뒤에 넣고 아닌경우 앞에 넣는다. 그리고 size, i, idx를 초기화
                    size=j;
                    i=0;
                    idx++;
                    dq2.offerLast(result + "");
                }else{
                    dq2.offerFirst(result + "");
                }
            }else{ // 다시 넣기
                // System.out.println("다시넣기");
                dq2.offerLast(operand1);
                dq2.offerLast(operator);
                if(size==i) {  // 사이클의 마지막 연산인 경우, 뒤에 넣고 아닌경우 앞에 넣는다. 그리고 size, i, idx를 초기화
                    size=j;
                    i=0;
                    idx++;
                    dq2.offerLast(operand2);
                }else{
                    dq2.offerFirst(operand2);
                }
            }
            //a++;
        }
        return 0;
    }
    
    // 테스트용 메소드
    private static void print(Deque<String> dq2){
        for(String s : dq2){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
