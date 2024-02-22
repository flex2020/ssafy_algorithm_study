import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// 크루스칼 알고리즘 이용
class Solution
{
    
    static class Edge{
        int from;
        int to;
        int weight;
        
        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
    }
    
    static int[] set; // 최소 신장 트리
    static int V; // 정점 수
    static int E; // 간선 수
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
           	long answer=0;
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            Edge[] edgeList = new Edge[E]; // 입력받은 간선 정보 배열
            int cnt=0; // 넣은 간선 수
            
            make(); // make-set
            
            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }
            
            // 가중치 기준 오름차순 정렬
            Arrays.sort(edgeList, (a, b) -> {
                return a.weight - b.weight;
            });
            
            for(int i=0;i<E;i++){
                if(union(edgeList[i].from, edgeList[i].to)){ // 아직 연결된 게 아니라면 cnt, answer 업데이트
                    //System.out.println(edgeList[i].from + " " + edgeList[i].to + " " + edgeList[i].weight);
                    answer+=edgeList[i].weight;
                    cnt++;
                }
                if(cnt==V-1) break;
            }
            
			System.out.println("#" + test_case + " " +answer);
		}
	}
    
    private static void make(){ // make-set
        set = new int[V+1];
        for(int i=1;i<set.length;i++){
            set[i]=i;
        }
    }
    
    private static int find(int a){ // find-set
        if(set[a]==a){
            return set[a];
        }else{
            return set[a] = find(set[a]);
        }
    }
    
    private static boolean union(int a, int b){ // union-set
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB){
            return false;
        }else{
            set[rootA] = rootB;
            return true;
        }
    }
}
