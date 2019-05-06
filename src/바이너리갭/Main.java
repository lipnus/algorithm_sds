package 바이너리갭;

public class Main {
    public static void main(String[] args){
        System.out.println( Solution(529) );
    }

    static int Solution(int N){

        String binary = Integer.toBinaryString(N);

        int max=-1;
        int count=0;
        for(int i=0; i<binary.length(); i++){

            if(binary.charAt(i)=='1'){
                max = Math.max(max, count);
                count=0;
            }else if(binary.charAt(i)=='0'){
                count++;
            }
        }

        return max;
    }
}
