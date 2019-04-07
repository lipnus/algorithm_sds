package 경사로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int L;
    static int[][] map;
    static int count= 0;

    public static void main(String[] args) throws Exception{

        input();


        int[] line = new int[6];

        //가로줄
        for(int i=0; i<N; i++){
            line = map[i].clone();
            if(checkLine(line)) count++;
        }

        //세로줄
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                line[j] = map[j][i];
            }

            if(checkLine(line)) count++;
        }

        System.out.println(count);
    }




    //한 줄 체크
    static boolean checkLine(int[] line){

        //경사로 건설 유무
        int[] maked = new int[N];


        for(int i=0; i<N-1; i++){

            //2칸이상 차이나면 나가리
            if( Math.abs( Math.abs(line[i]-line[i+1])) >1){
                return false;
            }

            //위로 건설해야 하는 곳 체크
            if(line[i]+1==line[i+1]){

                if(canMake(line, i-L+1,i, maked)){
                    makeSlip(maked, i-L+1, i);
                }
                else return false;
            }

            //아래로 건설해야하는 곳 체크
            else if(line[i]-1==line[i+1]){

                if(canMake(line, i+1,i+L, maked)){
                    makeSlip(maked, i+1, i+L);
                }
                else return false;
            }
        }
        return true;
    }

    /**
     *
     * @param line 전체라인
     * @param start 경사로 시작
     * @param end 경사로 끝
     * @param  maked 경사로 건설 유무
     * @return
     */
    static boolean canMake(int[] line, int start, int end, int[] maked){

        //범위 체크
        if(start<0 || N<=end){
            return false;
        }

        //건설예정인 곳 땅 체크
        for(int i=start; i<=end; i++){

            //이미 경사로가 있다
            if(maked[i]==1) return false;

            //평탄하지 못하다
            if(i!=end && line[i]!=line[i+1]) return false;
        }
        return true;
    }


    //경사로를 건설한다
    static void makeSlip(int[] maked, int start, int end){

        for(int i=start; i<=end; i++){
            maked[i] =1;
        }
    }



    static void input() throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
