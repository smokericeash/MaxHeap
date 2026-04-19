import java.util.Scanner;

public class main {
    public static void main(String[] args){
        System.out.println("Please select how you want to test the program.");
        System.out.println("(1) 20 sets of 100 randomly generated integers");
        System.out.println("(2) Fixed integer values 1-100");
        System.out.print("Enter choice: ");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();

        if(choice==1){
            System.out.println("gay");
        }
        else if(choice==2){  
            System.out.println("gay");
        }
        else{
            System.out.println("You did not input a correct number, please try again.");
        }   
    }
}
