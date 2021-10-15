package interface_adapters;

import java.io.*;

public class StartScreenWindow implements  Window{

    private Console cnsl = System.console();

    public String getUserInput() {


         // returns 0 if the user selects login and returns 1 if the user selects signup


        while (true) {
            String input = cnsl.readLine("For Login type L, for Sign up type S");
            if (input.equals("L")) {
                return "0";
            } else if (input.equals("S")) {
                return "1";
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
