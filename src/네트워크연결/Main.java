package 네트워크연결;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; //노드 수
    static int M; //선의 수
    static int[] parent;
    static List<Edge> edges;


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        edges = new ArrayList<>();
        int costSum = 0;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];

        //집합관계 초기화
        for(int i=1; i<=N; i++){
            parent[i] =i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>(){

            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.cost < o2.cost) return -1;
                else return 1;
            }
        });


        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(v1,v2,cost));
        }


        Edge e;


        while(!pq.isEmpty()){
            e = pq.poll();
            if(find(e.v1)!=find(e.v2)){
                union(e.v1, e.v2);
                costSum += e.cost;
            }
        }
        System.out.println(costSum);
    }


    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot!=bRoot) parent[bRoot] = aRoot;
    }


    static int find(int a){
        if(parent[a]==a) return a;
        else{
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }


    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int cost;

        Edge(int v1, int v2, int cost){
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            if(this.cost<e.cost) return -1;
            else if(this.cost==e.cost) return 0;
            else return 1;
        }
    }
}
