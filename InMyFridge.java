
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InMyFridge {
   
    HashMap<String, ItemsDetails> items=new HashMap<String, ItemsDetails>();
    // ItemsDetails details=new  ItemsDetails();

    ItemsDetails details=new ItemsDetails();
    public InMyFridge() {
    }

    // input
    void input(String itemName, int numItem, String exDate) {
        
       
        items.put(itemName,new ItemsDetails(numItem,exDate));
        
    }
    //sort the map
    public static LinkedHashMap<String, ItemsDetails> sortMapByValue(Map<String, ItemsDetails> items) {
        List<Map.Entry<String, ItemsDetails>> entries = new LinkedList<>(items.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getValue().getExDate().compareTo(o2.getValue().getExDate()));
        
        LinkedHashMap<String, ItemsDetails> result = new LinkedHashMap<>();
        for (Map.Entry<String, ItemsDetails> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    
    //show all the items in the fridge method
    void showAllItems(){

        Map<String, ItemsDetails> result = sortMapByValue(items);
        
        for(Map.Entry<String, ItemsDetails> entry : result.entrySet()) {
            System.out.println("■ " + entry.getKey() + "("
            +entry.getValue().getNumItem()+"), "+ "EXP: " + entry.getValue().getExDate());

    }
    }
 
    // delete the item
    void deleteItem(String item){
        items.remove(item);
    }


    //modify the expiration date
    void modifyExdate(String item,String exDate){
        for(Map.Entry<String, ItemsDetails> m: items.entrySet()) {
            if(m.getKey().equals(item)) {

                items.put(item, new ItemsDetails(m.getValue().getNumItem(),exDate) );
            } 
    }
}

    //modify the num of item
    void modifyNumItem(String item, int numItem){
        for(Map.Entry<String, ItemsDetails> m: items.entrySet()) {
            if(m.getKey().equals(item)) {

                items.put(item, new ItemsDetails(numItem, m.getValue().getExDate()) );
            } 
    }
}


    //error message
    void errorMessage(String message) {
        System.out.println(message);
    }


    //simple date format
    void simpleDate(String exDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);
        sdf.parse(exDate);
    }
 

    //Show Menu options method
    void showMenu(){
        int option;
        try(Scanner input=new Scanner(System.in)){
            do{
                System.out.println(
                "=========================================\n"+
                "Please Select an option from the below\n"+
                "1: New Item\n"+
                "2: Show all the items\n"+
                "3: Search for an item\n"+
                "4: Delete the Item\n"+
                "5: Modify the number of an item\n"+
                "6: Modify the expiration date of an item\n"+
                "7: Log out\n"+
                "========================================="
                );
                option=Integer.parseInt(input.nextLine());
                App.clrscr();

                switch(option){
                    
                    //add new item
                    case 1:
                        System.out.println("-----------------------------");
                        System.out.println("Enter the name of the item");
                        System.out.println("-----------------------------");
                        String item=input.nextLine();
                        
                        System.out.println("-------------------------------");
                        System.out.println("Enter the number of "+item);
                        System.out.println("-------------------------------");
                        int numItem=Integer.parseInt(input.nextLine());
                        
                        
                   
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Enter the expiration Date in the following format: yyyy/MM/dd (ex. 2022/10/12)");
                        System.out.println("------------------------------------------------------------------------------");
                        String exDate=input.nextLine();
                       

                        try{    
                            simpleDate(exDate);
                            input(item,numItem,exDate);
                            App.clrscr(); 
                            System.out.println("You have successfuly added " + item);
                            
                        }catch(ParseException e){
                            errorMessage(exDate +" is not a valid date. Please enter the date in the following format: yyyy/MM/dd (ex. 2022/10/12)");
                        }
                        break;

                    //show all the item
                    case 2:
                    if(items.isEmpty()){
                        System.out.println("You have nothing in your fridge");
                    }else{
                        showAllItems();
                    }
                    break;
                    //search for the item
                    case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Please enter the name of the item");
                    System.out.println("-----------------------------------");
                    try {
                        String searchItem=input.nextLine();
                    for(Map.Entry<String, ItemsDetails> m: items.entrySet()) {
                        if(m.getKey().equals(searchItem)) {
                            System.out.println("■ " + m.getKey() + " ("
                            +m.getValue().getNumItem()+"), "+ "EXP: " + m.getValue().getExDate());
                        break;
                    }else{
                        errorMessage("There is no " + searchItem);
                    }}}catch(Exception e){
                        errorMessage("Wrong input! Please enter the NAME of the item");
                    }
                    break;

                    //delete the item
                    case 4:
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Please enter the name of the item you would like to delete");
                    System.out.println("-----------------------------------------------------------");
                    showAllItems();
                   
                    System.out.println("-------------------------------");
                   
                    String index=input.nextLine();
                    for(Map.Entry<String, ItemsDetails> m: items.entrySet()) {
                        if(m.getKey().equals(index)){
                    deleteItem(index);
                    System.out.println("You have successfully deleted " +index);
                        }else {
                            System.out.println("You do not have "+index+" in your fridege");
                        }
                    }
                    break;
                    
                 
                    //modify the item number
                    case 5:
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("Please enter the name of the item you would like to modify the number of");
                    System.out.println("-------------------------------------------------------------------------");
                    showAllItems();
                    System.out.println("-------------------------------");
                    try{
                    String modifiedItem=input.nextLine();
                    if (items.containsKey(modifiedItem)){
                    System.out.println("---------------------------------");
                    System.out.println("Please enter the number of items");
                    System.out.println("---------------------------------");
                   try{
                    int modifiedNumItem=Integer.parseInt(input.nextLine());
                    modifyNumItem(modifiedItem, modifiedNumItem);
                    System.out.println("you have successfully modified the number of "+modifiedItem+".");}catch(Exception e){
                        errorMessage("Wrong input! Please enter the NUMBER ");
                        break;
                    }
                    }else{
                        errorMessage("there is no "+modifiedItem+ " in your fridge");
                    }}catch(Exception e){
                        errorMessage("Wrong input! Please enter the NAME of the item");
                    }
                    break;
               
                
            
                    //modify the exDate
                    case 6:
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("Please enter the name of the item you would like to modify the expiration date of");
                    System.out.println("----------------------------------------------------------------------------------");
                    showAllItems();
                    System.out.println("-------------------------------");
                    try{
                    String modifiedItem2=input.nextLine();
                    if (items.containsKey(modifiedItem2)){
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println("Please enter the new expiration datein the FLOLLOWING FORMAT: yyyy/MM/dd (ex. 2022/10/12)");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    try{
                    String modifiedExdate=input.nextLine();
                    simpleDate(modifiedExdate);
                    modifyExdate(modifiedItem2, modifiedExdate);
                    System.out.println("you have successfully modified "+modifiedItem2+"'s expiration date.");
                    break;
                    }catch(Exception e){
                        errorMessage("Wrong input! Please enter the DATE in the FLOLLOWING FORMAT: yyyy/MM/dd (ex. 2022/10/12)");
                    }
                    }else{
                        errorMessage("there is no "+modifiedItem2);
                    }}catch(Exception e){
                        errorMessage("Wrong input! Please enter the NAME of the item");
                    }
                    break;
               
                  
                    case 7:
                    break;

                    default:
                    System.out.println("Invalid option. Please try again.");
                    break;
                }
                
            }while(option!=7);

            System.out.println("Thank you for using our service.");
            System.exit(0);
        }

     
    }





   
    
}
