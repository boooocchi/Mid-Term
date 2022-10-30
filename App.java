import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {

            //assumption that a user has already been created
            Account account = new Account("Kota", "password");
            InMyFridge fridge=new InMyFridge();
            
            clrscr();
            System.out.println("====================");
            System.out.println("Welcome to the 'What's in my Fridge'");
            System.out.println("====================");
            
            while(true){
            System.out.println("Please enter your username");
            String userName=input.nextLine();
            System.out.println("Pleaser enter your password");
            String password=input.nextLine();

            if(userName.equals(account.getUserName())&& password.equals(account.getUserPassword())){
                System.out.println("Welcome to the What's in my Fridge, " + userName );
                break;
            }
        

            System.out.println("Invalid credentials\nPress [enter] key to try again or press [Q] to quit");

            if(input.nextLine().toLowerCase().equals("q")){
                System.out.println("Thank you for visiting us!");
                System.exit(0);
        }
    }
        clrscr();   
        fridge.showMenu();
    
}
}

public static void clrscr(){
    //Clears Screen in java
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime();
            System.out.print("\033\143");
    } catch (IOException | InterruptedException ex) {}
}
}
