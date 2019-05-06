package 해쉬;


import java.util.LinkedList;
import java.util.List;

class Hash{

    private int size;
    private List<Node>[] table;

    class Node{
        String key;
        String value;

        Node(String key, String value){
            this.key = key;
            this.value = value;
        }
    }


    Hash(int size){
        this.size = size;
        table = new LinkedList[size];
    }


    private int makeHashCode(String key){

        int hashCode = 0;
        for(int i=0; i<key.length(); i++){
            hashCode += (int)key.charAt(i);
        }
        return hashCode;
    }


    private int makeHashIndex(String key){
        return makeHashCode(key) % size;
    }


    void set(String key, String value){

        int index = makeHashIndex(key);

        //비어있음 경우
        if(table[index]==null) {
            table[index] = new LinkedList();
            table[index].add(new Node(key, value));
        }

        //무언가로 채워져 있는 경우
        else{

            boolean overlap = false;
            for(Node n: table[index]){
                if(n.key.equals(key)){
                    n.value = value; //대체
                    overlap= true;
                    break;
                }
            }
            if(overlap==false){
                table[index].add((new Node(key, value)));
            }
        }


    }

    String get(String key){

        int index = makeHashIndex(key);

        if(table[index]==null) return "널";

        for(Node n: table[index]){
            if(n.key.equals(key)){
                return n.value;
            }
        }

        return "널";
    }
}

public class HashExample {
    public static void main(String[] args){

        Hash hash = new Hash(3);

        hash.set("hwang", "학교종이 땡땡땡");
        hash.set("sun", "스타벅스");
        hash.set("pil", "슈밤슈슈슈밤밤");

        System.out.println(hash.get("sun"));
        System.out.println(hash.get("hwang"));
        System.out.println(hash.get("fuck"));
    }
}
