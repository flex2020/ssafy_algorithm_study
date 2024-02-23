import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Solution
{
    
    static ArrayList<Node>[] adjList;
    static int N;
    static double E;
    
    static class Node{
        int num;
        double weight;
        
        Node(int num, double weight){
            this.num = num;
            this.weight = weight;
        }
    }
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            long answer;
			N = Integer.parseInt(br.readLine());
            int[] x = new int[N];
            int[] y = new int[N];
            adjList = new ArrayList[N];
            
            for(int i=0;i<N;i++){
                adjList[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                x[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                y[i] = Integer.parseInt(st.nextToken());
            }
            E = Double.parseDouble(br.readLine());
            
            for(int i=0;i<N;i++){ // 인접리스트 만들기
                for(int j=0; j<N ;j++){
                    if(i==j) continue;
                    double distance = Math.pow(Math.abs(x[i]-x[j]), 2) + Math.pow(Math.abs(y[i]-y[j]), 2);
                    adjList[i].add(new Node(j, distance));
                    adjList[j].add(new Node(i, distance));
                }
            }
            
            answer = Math.round(prim());
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    private static double prim(){
        double result=0.0;
        int cnt=0;
        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(a.weight, b.weight);
        });
        
        pq.offer(new Node(0, 0.0));
        while(!pq.isEmpty()){
            Node n = pq.poll();
            
            if(visited[n.num]) continue;
            visited[n.num]=true;
            cnt++;
            result+= n.weight * E;
            if(cnt==N) break;
            
            for(Node next : adjList[n.num]){
                if(!visited[next.num]){
                    pq.offer(next);
                }
            }
        }
        
        return result;
    }
}
