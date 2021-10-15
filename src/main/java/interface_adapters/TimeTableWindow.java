package interface_adapters;


import java.io.Console;

public class TimeTableWindow implements Window{

    Console cnsl = System.console();

    @Override
    public String[] getUserInput() {

        // returns 1 if the user goes back to ViewAccount..
        String[] returnList = new String[1];
        while (true) {
            String input = cnsl.readLine("If you want to go back to ViewAccount type VA");
            if (input.equals("VA")) {
                returnList[0] = "1";
                return returnList;
            }
        }
    }

    /**
     * Displays a visual representation of the schedule.(by using Schedule.toString())
     */
    public void display(String s) {

        System.out.println(s);


    }
}
