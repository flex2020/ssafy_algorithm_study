import java.util.*;

class Solution {

    static int[] answer; // 정답배열
    static Map<String, Integer> map; // 각 요소를 숫자로 매핑한 map
    static ArrayList<Integer>[] score; // info의 점수들을 담을 배열

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];

        map = new HashMap<>();
        setting(map);

        // 점수들을 담을 배열
        score = new ArrayList[24];

        // score를 담을 ArrayList 초기화
        for(int i=0;i<score.length;i++){
            score[i] = new ArrayList<Integer>();
        }

        // hashing해서 나온 배열에 해당하는 숫자를 넣는다.
        for(int i=0;i<info.length;i++){
            String[] s = info[i].split(" ");
            int hash = hashing(map.get(s[0]), map.get(s[1]), map.get(s[2]), map.get(s[3]));
            int num = Integer.parseInt(s[4]);
            score[hash].add(num);
        }

        // 오름차순 정렬
        for(int i=0;i<score.length;i++){
            Collections.sort(score[i]);
        }

        for(int i=0;i<query.length;i++){
            String[] s = query[i].split(" and ");
            String[] s2 = s[3].split(" ");
            s[3]=s2[0];
            int num = Integer.parseInt(s2[1]);
            // 위 score 배열에 숫자를 채울때는 hash를 만들어서 넣었지만, query에서는 "-"를 고려해줘야 했기 때문에
            // 각 자리마다 hash를 만들어주는 식으로 진행했다.
            // 첫번째 재귀에서는 -000이면 왼쪽에서 첫번째 자리의 숫자를 결정하고(얘는 3개짜리라 일부러 재귀 바깥에서 하고 들어갔다)
            // 두번째 재귀에서는 --00이면 왼쪽에서 두번째 자리의 숫자를 결정하고(-000 or -100) 그런 느낌이다.
            if(map.get(s[0])==0){
                for(int j=0;j < 3;j++){
                    int hash = 8*j; // 끼워맞추기 ㅎㅎ;;
                    calculate(s, num, 3, i, hash);
                }
            }else{
                int hash = 8*(map.get(s[0])-1);
                calculate(s, num, 3, i, hash);
            }
        }




        return answer;
    }

    // 실제로 query의 해시를 구한 다음에 그걸 score에서 더하는 재귀함수이다.
    // depth는 3부터 시작해서 하나씩 작아지게 만들었다.
    // idx는 몇번째 쿼리인지를 말한다. answer에 넣기 위해 계속 받아줬다.
    // 예를 들어서 ---0이면 3*2*2 해서 depth==0을 12번 만나야 계산이 가능해진다. 그래서 이런식으로 재귀를 돌렸다. 모든 경우를 들어가주기 위해서.
    private static void calculate(String[] s, int num, int depth, int idx, int hash){
        if(depth==0){ // depth가 0이라면 hash가 만들어졌다는 뜻이므로 만들어진 hash에 해당하는 ArrayList에서 커지는 것을 찾으면 size-i해서 answer[idx]에 더해줬다.
            int size = score[hash].size();
            for(int i=0;i<size;i++){
                if(num<=score[hash].get(i)){
                    answer[idx] += size-i;
                    break;
                }
            }

            return;
        }

        // depth가 3부터 시작했는데, s 배열에서는 반대로 커져야하므로 4-depth를 해준다.
        if(map.get(s[4-depth])==0){ // 이게 0이면 "-"라는 뜻이므로 반복문으로 hash를 들어가서 calculate 재귀를 진행
            for(int i=0;i<2;i++){
                // 여기선 0인지 1인지가 중요한게 아니라 어짜피 "-"니까 해당 자릿수의 모든 경우의 수(2가지)만 보면 되니까 0을 shift, 1을 shift 이렇게 한번씩 해준거다.
                hash = hash | (i << (depth-1));
                calculate(s, num, depth-1, idx, hash); // 재귀는 depth를 하나 낮추고 보내준다.
            }
        }else{ // 0이 아니면 해당하는 것만 calculate 재귀를 진행
            // 해당하는 요소가 0이 아닌 1 아니면 2이기 때문에 1이면 hash를 업데이트하지 않고, 2이면 hash를 업데이트하도록 만들어준 것이다.
            hash = hash | ((map.get(s[4-depth])-1) << (depth-1));
            calculate(s, num, depth-1, idx, hash);
        }
        return;
    }

    // "-"는 0, 나머지는 1부터 시작해서 1씩 올려서 숫자랑 매핑했다.
    private static void setting(Map<String, Integer> map){
        map.put("-", 0);
        map.put("cpp", 1);
        map.put("java", 2);
        map.put("python", 3);
        map.put("backend", 1);
        map.put("frontend", 2);
        map.put("junior", 1);
        map.put("senior", 2);
        map.put("chicken", 1);
        map.put("pizza", 2);
    }

    // 어거지로 끼워맞춘거긴 한데, cpp, java, python은 3개짜리니까 if문 3개로 나누고 나머지는 각 한자리씩 써서 표현하고 숫자로 해싱
    private static int hashing(int n1, int n2, int n3, int n4){
        int result = 0;
        if(n1==1){
            result = n2==2 ? result | (1 << 2) : result;
            result = n3==2 ? result | (1 << 1) : result;
            result = n4==2 ? result | 1 : result;
        }else if(n1==2){
            result = n2==2 ? result | (1 << 2) : result;
            result = n3==2 ? result | (1 << 1) : result;
            result = n4==2 ? result | 1 : result;
            result+=8;
        }else if(n1==3){
            result = n2==2 ? result | (1 << 2) : result;
            result = n3==2 ? result | (1 << 1) : result;
            result = n4==2 ? result | 1 : result;
            result+=16;
        }

        // System.out.println(result);

        return result;
    }
}
