package 테스트;

public class Main {
    public static void main(String[] args){


        int hashCode = getHashCode("lipnus");
        System.out.println( hashCode );
    }

     static int getHashCode(String key) {
        int hashCode = 0;
        for(char c : key.toCharArray()) {
            hashCode += c;
        }
        return hashCode;
    }


}
