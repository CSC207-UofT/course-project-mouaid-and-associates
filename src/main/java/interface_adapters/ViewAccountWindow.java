package interface_adapters;

import java.io.Console;
import java.util.List;
import java.util.Objects;

public class ViewAccountWindow implements Window{

    Console cnsl = System.console();

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    String[] returnList = new String[1];
    @Override
    public String[] getUserInput() {
        if (selectAction().equals("yes")){
            returnList[0] = selectMedicineAction();
            return returnList;
        }
        else{
            returnList[0] = "no";
            return returnList;
        }

    }

    /**
     * @return "yes" or "no"
     **/
    public String selectAction(){

        while (true){
            String input = cnsl.readLine("Do you want to add a new medicine, remove a medicine, edit their medicine or " +
                    "look at their medication timetable.? Type yes or no");
            if (input.equals("yes") || input.equals("no")) {
                return input;
            }
        }

    }
    /**
     * @return "add" or "edit" or "remove"
     **/
    public String selectMedicineAction(){

        while (true){
            String input = cnsl.readLine("Add, edit or remove?");
            if (input.equals("add") || input.equals("edit") || input.equals("remove")) {
                return input;
            }
        }

    }

    /** Shows the account informaton of the user
     * @param username the username the user uses
     * @param name the name of the user
     * @param medicine the list of the medcines the user uses
     */
    public void showAccountInfo(String username, String name, List<String> medicine){

        System.out.println("Username: " + username);
        System.out.println("Name: " + name);

        StringBuilder medicine_list = new StringBuilder();

        for (String x: medicine){
            medicine_list.append(x);
            medicine_list.append(", ");
        }

        System.out.println("List of medicines:" + medicine_list);

    }

}
