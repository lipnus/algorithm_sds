package 단절점;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V; //정점의 수 ~10,000
    static int E; //간선의 수 ~100,000
    static List<Integer>[] map;
    static int[] cutVertex; //단절점


    static int[] order; //각 정점의 방문순서
    static int seq; //전체 방문순서 카운터


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        order = new int[V+1]; //0이면 방문을 하지 않은 상태
        cutVertex = new int[V+1];

        seq = 1;

        //몇개 안되니까 초기화를 다 시킨다
        map = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            map[i] = new ArrayList<>();
        }

        for(int i=1; i<=E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].add(b);
            map[b].add(a);
        }

        //forest가 2개 이상일 수 있으므로.
        for(int i=1; i<=V; i++){
            if(order[i]==0) dfs(i, true);
        }

        //출력
        int cutPointCount = 0;
        for(int i=1; i<=V; i++){
            if(cutVertex[i]==1) cutPointCount++;
        }

        System.out.println(cutPointCount);

        for(int i=1; i<=V; i++){
            if(cutVertex[i]==1) System.out.printf("%d ", i);
        }
    }


    /**
     * 어떤(now)의 다음 노드(next)가  now노드를 거치지 않고 그 앞의 애들에게 접근할 수 없으면 단절점.
     * 리턴값은 탐색가능한 노드 중 seq가 가장 작은 노드의 seq.
     *
     * [예외] root의 경우는 자손이 2개 이상이면 단절점.
     */
    static int dfs(int now, boolean isRoot){

        order[now] = seq++; //현재노드의 방문번호
        int retu = order[now]; //가장 말단이면 자기 자신의 order를 반환
        int childCount=0;


        //다음노드 검사
        for(int next: map[now]){

            if(order[next]==0){ //새로운 곳(탐색O)

                childCount++;
                int dfs = dfs(next, false);
                if( order[now]<=dfs && !isRoot ){
                    System.out.printf("단절점추가:%d(%d) %d(%d)\n", now, order[now], next, dfs);
                    cutVertex[now]=1; //다음노드가 나 이전으로 가지 못하면 나는 단절점
                }
                retu = Math.min(retu, dfs);

            }else { //이미 갔던 곳: order만 가져온다 (탐색X)
                retu = Math.min(retu, order[next]);
            }
        }

        //루트이면서 연결지점이 2개이상인 경우 단절점
        if( isRoot && 2<=map[now].size() ) cutVertex[now]=1;

        return retu;
    }
}
