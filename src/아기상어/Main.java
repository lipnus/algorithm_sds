package 아기상어;

import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static List<Integer> fishs = new ArrayList<>();

    //상,좌,우,하 순서로 탐색
    static int[] dn = {-1, 0, 0, 1};
    static int[] dm = {0, -1, 1, 0};

    static int shark=2;
    static int stomach=0;
    static Position p;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                int num = sc.nextInt();

                if(num==9){
                    p = new Position(i, j);
                }else{
                    map[i][j] = num;
                    if(0<num) fishs.add(num);
                }
            }
        }

        System.out.println( loop(p, 0) );

    }


    static int loop(Position pos, int count){
        if( isEnd() ) return count;


        //현 위치에서 탐색시작
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        int[][] visited = new int[N+1][N+1];
        visited[pos.n][pos.m] = 1;

        int cnt=0;
        while (!q.isEmpty()){

            //한 level탐색
            PriorityQueue<Position> rFish = new PriorityQueue<>();
            cnt++;
            int qSize = q.size();
            for(int l=0; l<qSize; l++){

                Position now = q.poll();

                //4방향 탐색
                for(int i=0; i<4; i++){

                    Position next = new Position(now.n+dn[i], now.m+dm[i]);
                    if( isCanGo(next, visited) ){

                        //먹을 수 있는 물고기 후보
                        if( isEatable(next) ){ rFish.add(next); }

                        q.add(next);
                        visited[next.n][next.m]=1; //방문표시

                    }
                }//4방향 탐색
            }//한 레벨 탐색

            //우선순위 평가 후 섭취
            if( !rFish.isEmpty() ){
                eatFish( rFish.peek() );
                return loop(rFish.peek(), count+cnt);
            }

        }


        //물고기는 남았지만 도달할 수 없으면 이곳까지 오게 된다
        return count;
    }




    static boolean isCanGo(Position pos, int[][] visitied){

        //범위 안인지 체크
        if(pos.n < 1 || N < pos.n || pos.m < 1 || N < pos.m ) return false;

        //방문했던 곳인지 체크
        if( visitied[pos.n][pos.m] == 1) return false;

        //나보다 큰 물고기가 있는지 체크
        if( shark < map[pos.n][pos.m]) return false;

        return true;
    }


    //먹을 수 있는 물고기인가
    static boolean isEatable(Position pos){
        if(0 < map[pos.n][pos.m] && map[pos.n][pos.m]<shark) return true;
        return false;
    }



    //먹을 수 있는 물고기 다 먹었는지
    static boolean isEnd(){

        if(fishs.size()==0) return true; //다처먹음
        else{
            for(int f: fishs) if(f < shark) {
                return false; //먹을 물고기가 있다
            }
        }
        return true; //먹을 게 없으니 엄마에게 핼프
    }



    //냠냠
    static void eatFish(Position pos){

        int fish = map[pos.n][pos.m];

        for(int i=0; i<fishs.size(); i++){
            if(fishs.get(i)==fish){

                //먹은물고기 제거
                fishs.remove(i);
                map[pos.n][pos.m] = 0;

                //성장
                stomach++;
                if(shark==stomach){
                    shark++;
                    stomach=0;
                }
                return;
            }
        }
    }



    static class Position implements Comparable<Position>{
        private int n;
        private  int m;

        Position(int n, int m){
            this.n = n;
            this.m = m;
        }


        //[첫번째기준]: n이 작을수록, [두번째기준]: m이 작을수록
        @Override
        public int compareTo(Position o) {
            if(this.n < o.n) return -1;

            else if(this.n == o.n){
                if(this.m < o.m) return -1;
                return 1;
            }

            return 1;
        }
    }
}
