package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class RemovePrescriptionWindow extends Window {
    String[] userInput;
    JTextField medicineName;
    JLabel medicineLabel;
    JButton submit;
    public RemovePrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        userInput = new String[1];
        createView();
    }

    @Override
    public String[] getUserInput() {
        return userInput;

    }

    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override

    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            super.userResponded = true;
            userInput[0] = medicineName.getText();
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

        medicineLabel = new JLabel("Name of the Prescription to be removed: ");
        medicineLabel.setSize(400, 50);
        medicineLabel.setLocation(10,50);
        medicineName = new JTextField();
        medicineName.setSize(100, 50);
        medicineName.setLocation(10, 110);
        submit = new JButton("Submit");
        submit.setSize(100, 50);
        submit.setLocation(10, 170);


        panel.add(medicineLabel);
        panel.add(medicineName);
        panel.add(submit);



        super.buttonResponses.put(submit, "1");

        super.addActionListenerToAllButtons();

        super.view = panel;
    }
}
