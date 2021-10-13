package interface_adapters;

import java.io.*;

public class StartScreenWindow implements  Window{


    Console cnsl = System.console();


    public int getInput() {


         // returns 0 if the user selects login and returns 1 if the user selects signup


        while (true) {
            String input = cnsl.readLine("For Login type L, for Sign up type S");
            if (input.equals("L")) {
                return 0;
            } else if (input.equals("S")) {
                return 1;
            }
        }
    }

    public void showOptions(){
        if (getInput() == 0){
            /**
             * Starts the LoginWindow
             */
        }
        else{
            /**
             * Starts the CreateAccountWindow
             */

        }
    }

}
