package 뚜룬;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Music {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;


        while( (line = br.readLine()) !=null ){

            System.out.printf("INSERT INTO `music` (`title`, `initial`, `singer`, `path`, `use_init`, `writer`)  VALUES ");
            String[] voca = line.split("\t");

            System.out.printf("(");
            for(int i=0; i<voca.length; i++){
                if(i<voca.length-1) System.out.printf(" '%s', ", voca[i]);
                else System.out.printf(" '%s' );\n", voca[i]);
            }
        }
    }
}