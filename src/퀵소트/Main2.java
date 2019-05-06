package 퀵소트;

public class Main2 {

    public static void main(String[] args){
        int[] arr = {6,2,4,5,1, 3,7};
        print(arr);
        System.out.println();

        quickSort(arr, 0, arr.length-1);
        print(arr);
    }



    static void quickSort(int[] arr, int start, int end){

        int part2 = partition(arr, start, end);
        System.out.println(part2);

        if(start < part2-1) quickSort(arr, start, part2-1);
        if(part2 < end) quickSort(arr, part2, end);
    }

//나눈 파티션의 오른쪽 끝

    static int partition(int[] arr, int start, int end){

        int pivot = arr[(start+end)/2];
        System.out.printf("pivot:%d\n", pivot);

        while(start <= end){
            while (arr[start]<pivot)start++;
            while (pivot < arr[end])end--;

            if(start<=end){
                System.out.printf("swap %d %d\n", arr[start], arr[end]);
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }



    static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }



    static void print(int[] arr){
        for(int a: arr){
            System.out.printf("%d", a);
        }
    }


}
