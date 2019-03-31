package 연산자끼워넣기;

import javax.imageio.stream.IIOByteBuffer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] a;
    static int[] operator = new int[4];

    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;



    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        a = new int[N];

        //수열정보
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            a[i]=Integer.parseInt(st.nextToken());
        }

        //연산자
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        makePattern(0, new int[N-1], operator);
        System.out.println(maxValue);
        System.out.println(minValue);

    }


    //연산자 패턴을 만든다
    static void makePattern(int index, int[] pattern, int[] operator){

        if(index==N-1) cal(pattern);

        //+,-,x,/ 순서(0,1,2,3)
        for(int i=0; i<4; i++){

            if (0 < operator[i]) {
                int[] nextOperator = operator.clone(); //남은 연산자의 개수
                nextOperator[i]--;
                pattern[index] = i;
                makePattern(index+1, pattern, nextOperator);
            }
        }
    }


    static void cal(int[] pattern){

        int value = a[0];
        for(int i=0; i<pattern.length; i++){
            value = calculate(value, pattern[i], a[i+1]);
        }

        maxValue = Math.max(maxValue, value);
        minValue = Math.min(minValue, value);
    }


    static int calculate(int oprand1, int operator, int oprand2){

        if(operator==0) return oprand1+oprand2;

        if(operator==1) return oprand1-oprand2;

        if(operator==2) return oprand1 * oprand2;

        if(operator==3){
            if(oprand1<0) return (-1)*((oprand1*-1) / oprand2);
            else return oprand1/oprand2;
        }

        return 0;
    }



}
