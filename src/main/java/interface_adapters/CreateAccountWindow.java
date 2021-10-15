package interface_adapters;

import java.io.Console;

public class CreateAccountWindow implements Window{

    Console cnsl = System.console();
    //Creates an account for a user.
    @Override
    public String getUserInput() {
        String name = cnsl.readLine("Name:");
        String username = cnsl.readLine("Username:");
        String password = cnsl.readLine("Password:");

        return name + " " + username + " " + password;

    }

}
