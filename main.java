import java.util.*;

public class main {
    public static void main(String[] args){
        System.out.println("Please select how you want to test the program.");
        System.out.println("(1) 20 sets of 100 randomly generated integers");
        System.out.println("(2) Fixed integer values 1-100");
        System.out.print("Enter choice: "); //list choices and ask user for choice
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();

        if(choice==1){

            //initialize swap count
            int totalSeqSwaps = 0;
            int totalOptSwaps = 0;
            for(int i = 0; i < 20; i++){
                Integer[] rand = new Random().ints(100,1,1001).boxed().toArray(Integer[]::new); //creating 100 randomly generated integers 1-1000


                //initialize sequential heap 
                MaxHeap<Integer> seqHeap = new MaxHeap<>(100);
                for (Integer num: rand){
                    seqHeap.add(num);
                }

                //count seqHeap swap counts
                totalSeqSwaps += seqHeap.getSwapCount();

                //initialize optimal heap 
                MaxHeap<Integer> optHeap = new MaxHeap<>(rand);

                //count optHeap swap counts 
                totalOptSwaps += optHeap.getSwapCount();
            }
            //print out results
            System.out.println("Average swaps for series of insertion:" + (totalSeqSwaps/20));
            System.out.println("Average swaps for optimal method:" + (totalOptSwaps/20));
            System.out.println("");
        } //end of choice 1

        else if(choice==2){  
            //initialize the swap counts for both options
            int totalSeqSwaps = 0;
            int totalOptSwaps = 0;
            
            //creating fixed array ranging from 1-100
            Integer[] fixed = new Integer[100];
            for(int i = 0 ; i < 100 ; i++){
                fixed[i] = i+1;
            }

            //initialize sequential heap
            MaxHeap<Integer> seqHeap = new MaxHeap<>(100);
            for (Integer num: fixed){
                seqHeap.add(num);
            }

            //count seqHeap swap counts
            totalSeqSwaps += seqHeap.getSwapCount();

            //initialize optimal heap
            MaxHeap<Integer> optHeap = new MaxHeap<>(fixed);

            //count optHeap swap counts 
            totalOptSwaps += optHeap.getSwapCount();


            System.out.print("Heap built using series of insertions: ");
            //printing out first ten integers before removal
            seqHeap.printFirstTen();
            System.out.println("Number of swaps:" + seqHeap.getSwapCount());

            //remvoing the 10
            for(int i = 0 ; i < 10 ; i ++){
                seqHeap.removeMax();
            }

            //printing out first ten AFTER 10 removals
            System.out.print("Heap after 10 removals: ");
            seqHeap.printFirstTen();
            System.out.println("");


            
            //printing out heap method using Floyd's algo
            System.out.print("Heap built using optimal method: ");
            //printing out first ten integers before removal
            optHeap.printFirstTen();
            System.out.println("Number of swaps:" + optHeap.getSwapCount());

            //remvoing the 10
            for(int i = 0 ; i < 10 ; i ++){
                optHeap.removeMax();
            }

            //printing out first ten AFTER 10 removals
            System.out.print("Heap after 10 removals: ");
            optHeap.printFirstTen();
            System.out.println("");
        } //end of choice 2

        else{
            System.out.println("You did not input a correct number, please try again.");
        } //if user puts any other number except for 1 and 2
    }
}
