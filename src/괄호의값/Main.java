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

        Stack<String> s = new Stack<>();
        Queue<String> q = new LinkedList<>();

//        [()[[]]]


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

                }
                isLastPop = true;
            }




        }//for
    }
}
