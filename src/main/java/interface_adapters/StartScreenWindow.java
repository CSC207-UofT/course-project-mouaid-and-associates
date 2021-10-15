package interface_adapters;

import java.io.*;

public class StartScreenWindow implements  Window{

    private Console cnsl = System.console();

    public String[] getUserInput() {


         // returns 0 if the user selects login and returns 1 if the user selects signup

        String[] returnList = new String[1];
        while (true) {
            String input = cnsl.readLine("For Login type L, for Sign up type S");
            if (input.equals("L")) {
                returnList[0] = "0";
                return returnList;
            } else if (input.equals("S")) {
                returnList[0] = "1";
                return returnList;
            }
        }
    }


    /**
    * Probably no need since the run method will take care of this.
     **/
    public void showOptions(){
        if (getUserInput().equals("0")){

             //Starts the LoginWindow

        }
        else{

             //Starts the CreateAccountWindow


        }
    }

}
