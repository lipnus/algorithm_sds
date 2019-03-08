package 테스트;

public class Main {
    public static void main(String[] args){

        System.out.println(1/2);

    }

     static int getHashCode(String key) {
        int hashCode = 0;
        for(char c : key.toCharArray()) {
            hashCode += c;
        }
        return hashCode;
    }


}
