package 해쉬;

public class HashTable {

    class Hash{
        String key;
        String data;
    }

    int capacity = 0;
    Hash[] table;


    /**
     * 생성자
     * @param capacity
     */
    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Hash[capacity];

        for(int i=0; i<capacity; i++) {
            table[i] = new Hash();
        }
    }


    private int hash(String str){
        int hash = 5381;
        for(int i=0; i<str.length(); i++){
            int c=  (int)str.charAt(i);
            hash = ( (hash << 5)+hash ) + c;
        }
        if(hash < 0) hash *= 1;
        return hash % capacity;
    }


    public String find(String key){
        int h = hash(key);
        int cnt = capacity;

        while( table[h].key!=null && (--cnt)!=0  ){

            if(table[h].key.equals(key)){
                return table[h].data;
            }
            h = (h+1) % capacity;
        }
        return null;
    }


    boolean add(String key, String data){
        int h = hash(key);
        while( table[h].key != null){ //비어있지 않음

            if( table[h].key.equals(key) ) return false; //해당 키는 이미 사용됨
            h = (h+1) % capacity;
        }
        table[h].key = key;
        table[h].data = data;
        return true;
    }
}



