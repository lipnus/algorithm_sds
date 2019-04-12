package 퇴사;
import java.util.Scanner;

public class Main2 {

    static int N;
    static Col[] table;
    static int[] D; //n일까지 벌 수 있는 최대 이익

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        table = new Col[N+1];
        D = new int[N+1];

        //입력받기
        for(int i=1; i<=N; i++){
            int t = sc.nextInt();
            int p = sc.nextInt();
            table[i] = new Col(t, p);
        }

        System.out.println(counsel(0, 0) );

//        for(int i=0; i<=N; i++){
//            System.out.printf("D[%d]=%d\n", i, D[i]);
//        }

    }





    static int counsel(int date, int price){

        if(date==N){
            D[N] = Math.max(D[N], price);
            return price;
        }

        if(D[date] <= price) D[date] = price;
        else{
            System.out.println("나가리");
            return -1;//똑같은 조건에서 더 우월한 놈이 있기때문에 더 해봤자 안됨
        }


        int t = table[date+1].time;
        int p = table[date+1].price;


        if(date+t<=N){ //현재날짜에서 시작한 상담이 퇴사전에 끝남 (오늘상담을 하는거랑 안하는 것중 더 우월한 거 선택)

            return Math.max( counsel(date+t, price+p), counsel(date+1, price) );

        }else if(date<N-1){ //현재날짜 상담 버리고 다음날을 도모

            return counsel(date+1, D[date]);

        }else{// 현재날짜에서 더이상 진행 불가

            return price;

        }

    }

    static class Col{
        int time;
        int price;

        Col(int time, int price){
            this.time = time;
            this.price = price;
        }
    }
}
