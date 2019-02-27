package 괄호의값;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    static boolean isLastPop = false;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String K = br.readLine();

        Queue<String> q = new LinkedList<>();


        /**
         * 수식을 생성한다
         */
        for(int i=0; i<K.length(); i++){
            String str = Character.toString( K.charAt(i) );

            if(str.equals("(") || str.equals("[")){
                if(isLastPop==true && !q.isEmpty()) q.offer("+");
                isLastPop = false;
            }

            if(str.equals(")")){
                if(isLastPop==true){
                    q.offer("*");
                    q.offer("2");
                }else{
                    q.offer("2");
                }
                isLastPop = true;
            }

            if(str.equals("]")){
                if(isLastPop==true){
                    q.offer("*");
                    q.offer("3");
                }else{
                    q.offer("3");
                }
                isLastPop = true;
            }
        }//for


        /**
         * 후위표기법으로 바꾼다
         */



        for(String s: q){
            System.out.printf("%s ", s);
        }

        System.out.println();

        int qSize = q.size();
        int sum = Integer.parseInt(q.poll());
        for(int i=0; i<qSize-1; i++){

            String cal = q.poll();

            if(cal.equals("+")){
                int n=Integer.parseInt(q.poll());
                sum += n;
                i++;
                System.out.printf("+%d ", n);

            }

            if(cal.equals("*")){
                int n= Integer.parseInt(q.poll());;
                sum *= n;
                System.out.printf("*%d ", n);
                i++;
            }
        }
        System.out.println(sum);
    }
}
