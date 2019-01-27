package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Edge>[] adj;
    static int[] cost; //출발점에서 해당 지점까지 가는 비용
    static final int INF = Integer.MAX_VALUE;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); //노드수
        int E = Integer.parseInt(st.nextToken()); //간선수
        int K = Integer.parseInt(br.readLine()); //시작정점

        adj = new ArrayList[V+1];
        cost = new int[V+1];
        visited = new int[V+1];
        Arrays.fill(cost, INF);

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(adj[u]==null) adj[u] = new ArrayList<>();
//            if(adj[v]==null) adj[v] = new ArrayList<>();
            adj[u].add( new Edge(v, w) );
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder("");
        for(int i=1; i<=V; i++){
            if(cost[i]!=INF) sb.append( cost[i]+"\n" );
            else sb.append( "INF\n" );
        }
        System.out.println(sb);
    }

    static void dijkstra(int K){

        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer( new Edge(K,0) );
        cost[K]=0;

        while(!q.isEmpty()){

            Edge edge = q.poll();
            int now = edge.v2; //지금노드

            if(visited[now]==0) visited[now] = 1;
            if( adj[now]==null ) continue;


            //지금거에 연결된 다음 노드들 확인
            Edge nextEdge;
            for(int i=0; i<adj[now].size(); i++){
                nextEdge = adj[now].get(i);

                if(nextEdge.v2 == now) continue;

                if( cost[now]+ nextEdge.weight < cost[nextEdge.v2]){
                    cost[nextEdge.v2] = cost[now]+nextEdge.weight;
                    q.offer( nextEdge );
                }
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        int v2;
        int weight;

        Edge(int v2, int weight){
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight < o.weight) return -1;
            else if(this.weight == o.weight) return 0;
            else return 1;
        }
    }
}
