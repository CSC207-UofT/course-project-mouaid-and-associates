package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveMedicineWindow extends Window implements DisplayEntityInformation {
    String[] userInput;
    JTextField presName;
    JLabel presLabel;
    JButton submit;
    public RemoveMedicineWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        userInput  = new String[1];
        createView();
    }

    @Override
    public String[] getUserInput() {
        return userInput;

    }

    public String getUserChoice(){
        System.out.println("Please enter the name of the medicine you would like to remove below:");
        System.out.println("* You can just press enter to exit *");

        return scanner.nextLine();
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info      The list of medicine to be printed.
     */
    @Override
    public void displayInfo(String[] info) {
        // For every sentence in info, print it out.
        for (String sentence: info){
            System.out.println(sentence);
        }
    }

    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey(source)){
            super.userResponded = true;
            userInput[0] = presName.getText();
        }

    }

    @Override
    public void createView() {
        // Our container for the components (like a board on which you pin things)
        JPanel panel = new JPanel();
        // The layout in which components are placed.
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);

        presLabel = new JLabel("Name of the medicine to be removed: ");
        presLabel.setSize(400, 50);
        presLabel.setLocation(10,50);
        presName = new JTextField();
        presName.setSize(100, 50);
        presName.setLocation(10, 110);
        submit = new JButton("Submit");
        submit.setSize(100, 50);
        submit.setLocation(10, 170);


        panel.add(presLabel);
        panel.add(presName);
        panel.add(submit);



        super.buttonResponses.put(submit, "1");

        super.addActionListenerToAllButtons();

        super.view = panel;
    }
}
