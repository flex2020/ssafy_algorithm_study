import java.io.*;
import java.util.*;


// LCA(최소 공통 조상 알고리즘)
public class Main {

    static List<Integer>[] adjList; // 트리 인접리스트
    static final int MAX_DEPTH = 17;
    static int[] depth; // 깊이 배열
    // 부모 배열(dp, lca) -> 이놈이 개정 LCA의 핵심이다. 이렇게 저장함으로 인해 메모리를 더 쓰는 대신 시간복잡도가 O(MlogN)이 된다고 한다.
    // parent[a][b]에서 a는 노드 개수 + 1, b는 들어갈 수 있는 숫자가 된다.
    // 해당 배열에 저장되는 건 parent[a][0]=a의 2^0번째 조상(부모), parent[a][1]=a의 2^1번째 조상, parent[a][2]=a의 2^2번째 조상... 이런 느낌이다.
    // 그래서 처음에 parent를 만들때는 int[][] parent = new int[N+1][2^k](k>2^(최대N) 이렇게 만들어주면 된다.
    static int[][] parent;
    static boolean[] check;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList();
        }

        depth = new int[N+1];
        check = new boolean[N+1]; // dfs할 때 쓸 check 배열
        parent = new int[N+1][MAX_DEPTH];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            // 이것만으로 누가 부모고 누가 자식인지 모르니까 나중에 진행
            adjList[from].add(to);
            adjList[to].add(from);
        }

        check[1]=true;
        dfs(1, 0);

        setParent();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int answer = lca(a, b);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);

    }

    // lca를 돌리는 메소드이다. 공통조상을 찾는게 목적이다.
    private static int lca(int a, int b){
        // 일반적인 LCA의 경우 b가 무조건 깊도록 만들기 때문에 a가 더 깊다면 둘이 swap해주는 메소드가 들어간다.
        if(depth[a] > depth[b]){
            int temp = a;
            a = b;
            b= temp;
        }

//        해당 문제에서는 깊이차가 최대 1밖에 안나기 때문에 이렇게 안했지만 원래대로라면 이렇게 해야한다.
//        아래 코드는 a와 b의 depth를 일단 같게 해주는 과정이다.
//        맨 위쪽 큰데서부터 아래로 내려오면서 루트를 벗어나지 않는 선에서 목적지까지 점프할 수 있는 최대치를 찾는다.
//        목적지 자체에 도달할 때까지(depth[b]-depth[a]==0일 때까지) 이 행동을 반복해준다.
//        조건문 하나로 가능한 이유는 b=parent[b][i]한 다음의 b는 이전 b보다 깊이가 당연히 더 작다. 즉, 현재 진행중인 i에서 계속해서 반복문을 돌려도
//        원하는 결과를 얻을 수 있다는 뜻이라서 저렇게 반복문 하나, 조건문 하나로 처리할 수 있는 것이다.
        if(depth[a] < depth[b]){
            for (int i = MAX_DEPTH; i >= 0; i--) {
                if(depth[b]-depth[a] >= (1<<i)){
                    b = parent[b][i];
                }
            }
        }

        // 그렇게 만들어진 a와 b가 같다면 걔가 공통조상이니까 그냥 리턴해준다.
        if(a==b){
            return a;
        }

        // a와 b가 같지 않다면 최소 공통 조상을 찾아야한다.
        // 위 내용이랑 동일하지만 일단 복붙해둔다.
        // 맨 위쪽 큰데서부터 아래로 내려오면서 루트를 벗어나지 않는 선에서 목적지까지 점프할 수 있는 최대치를 찾는다.
        // 목적지 자체에 도달할 때까지(curA==curB일 때까지) 이 행동을 반복해준다. 사실 같아도 반복은 계속한다.
        // 그렇게 반복을 계속하고 나면 parent[curA][0]==parent[curB][0]가 같아진다. 그걸로 요리조리 조리하면 된다.
        for (int i = MAX_DEPTH-1; i >= 0; i--) {
            if(parent[a][i]!=parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        // 그리고 둘중 하나를 리턴해주면 걔가 공통 조상이다.
        return parent[a][0];
    }

    // LCA 핵심2이다. setParent에서 각 노드의 조상들을 parent(dp)에 저장하는 메소드이다.
    // 이 작업을 통해 타고 올라가는데 시간이 더 안든다.
    private static void setParent(){
        // 테이블을 그려보면 dp를 채우기 위해서는 열별로 먼저 채워야한다. 일반적인 이중반복문은 행별로 먼저 채우지만 여기서는 뒤집어서 생각하면 된다.
        for (int i = 1; i < MAX_DEPTH; i++) {
            for (int j = 1; j < N+1; j++) {
                // 제대로 이해했다기보다는 직접 그려보면서 이해했다. 그냥 저렇게 하면 기록이 잘 된다고 생각하자.
                // depth가 4까지 있는(루트 depth = 0) 트리를 그리고 테이블을 같이 그려보면서 복기하도록 하자.
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    // 해당 노드의 깊이를 depth 배열에 저장하고 부모관계를 확실히 하고 parent[num][0] 여기에 넣어준다.
    // 루트노드가 1인건 자명하니까 그 아랫놈들부터 차례로 봐주면 된다.
    private static void dfs(int num, int d){
        depth[num]=d;
        int size = adjList[num].size();
        for (int i = 0; i < size; i++) {
            int n = adjList[num].get(i);
            if(!check[n]){
                check[n]=true;
                parent[n][0]=num;
                dfs(adjList[num].get(i), d+1);
            }

        }
    }
}
