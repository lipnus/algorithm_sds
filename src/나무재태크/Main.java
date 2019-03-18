package 나무재태크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N; //맵크기
    static int M; //나무수
    static int K; //년

    static Ground[][] map;

    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Ground[N+1][N+1];

        //초기화 & 양분세팅
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = new Ground( Integer.parseInt(st.nextToken()) );
            }
        }


        //나무심기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            map[x][y].addTree(age);
        }

        //세월아 흘러라
        for(int year=0; year<K; year++){

            //봄&여름
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    PriorityQueue<Integer> buffer = new PriorityQueue<>();

                    //해당 땅의 나무들 검사
                    int dieFood = 0; //죽어서 생긴 양분

                    while (!map[i][j].getTrees().isEmpty()){
                        int nowTree = map[i][j].trees.poll();

                        if(nowTree<=map[i][j].food){
                            map[i][j].food -= nowTree;
                            buffer.add(nowTree+1);
                        }else{
                            dieFood += nowTree/2;
                        }
                    }

                    //정산
                    map[i][j].trees = buffer;   //살아남은 나무들
                    map[i][j].food += dieFood;  //죽어서 양분이 된 나무들
                }
            }


            //가을(번식) & 겨울(양분추가)
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){

                    for(int tree: map[i][j].getTrees()){
                        if(tree%5==0) breeding(i,j);
                    }

                    map[i][j].addFood();
                }
            }


        }

        //나무의 수 출력
        int treeCount=0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                treeCount += map[i][j].getTreeCount();
            }
        }
        System.out.println(treeCount);

    }


    //입력받은 칸 주위 8칸으로 번식
    static void breeding(int a, int b){

        for(int i=0; i<8; i++){

            int x = a + dx[i];
            int y = b + dy[i];

            //범위내부
            if(1<= x && x<=N && 1<= y && y <= N){
                map[x][y].addTree(1);
            }
        }
    }



    static class Ground{

        private PriorityQueue<Integer> trees;
        private int food;
        private int yearFood; //매년 추가되는 양분


        public Ground(int yearFood){
            trees = new PriorityQueue<>();
            this.food = 5;
            this.yearFood = yearFood;
        }


        void addTree(int treeAge){
           this.trees.add(treeAge);
        }

        PriorityQueue<Integer> getTrees(){
            return trees;
        }

        //이 땅의 나무가 몇개인지 반환
        int getTreeCount(){
            return trees.size();
        }

        //매년 할당된 양의 양분을 추가
        void addFood(){
            this.food += this.yearFood;
        }
    }
}
