package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class ChoosePrescriptionToEditWindow extends Window implements DisplayEntityInformation {
    private String[] validInputs;
    private volatile String[] userInput;
    private JPanel panel1;


    public ChoosePrescriptionToEditWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();
        userInput = new String[]{""};
        validInputs = new String[0];
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info   The information to be printed. In this case, a list of prescription names,
     *               and a title.
     */
    @Override
    public void displayInfo(String[] info) {
        if (!super.buttonResponses.isEmpty()) {
            removeOldInfo();
        }

        // Also add the list of medicine names as valid inputs to verify user input.
        setValidInputs(Arrays.copyOfRange(info, 1, info.length));
        // Create buttons for each medicine.
        createButton(30 + 15 * info.length + 20);

        userInput[0] = "";
    }

    private void setValidInputs(String[] inputs){
        validInputs = new String[inputs.length];
        for (int i = 0; i < inputs.length; i++){
            validInputs[i] = inputs[i].substring(3);
        }
    }

    private void removeOldInfo(){
        for (JButton button: super.buttonResponses.keySet()){
            panel1.remove(button);
            super.buttonResponses.remove(button);
        }
    }

    @Override
    public String[] getUserInput() {
        String[] userResponse = new String[1];

        while(!(super.userResponded && verifyInput(userInput[0]))){
            if (verifyInput(userInput[0])){
                userResponse[0] = userInput[0];
            }
        }

        return userResponse;
    }

    private boolean verifyInput(String input){
        for (String validInput : validInputs) {
            if (validInput.equals(input)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton)source)) {
            userInput[0] = super.buttonResponses.get((JButton) source);
            super.userResponded = true;
        }
    }

    public void createButton(int y) {
        for (int i = 0; i < validInputs.length; i++) {
            JButton button = new JButton(validInputs[i]);
            button.setSize(286, 90);
            button.setLocation(100, y + 95 * i);
            panel1.add(button);
            super.buttonResponses.put(button, validInputs[i]);
        }

        super.addActionListenerToAllButtons();

        // Set the preferred size so we can contain all the new buttons.
        panel1.setPreferredSize(new Dimension(486, validInputs.length + 90));

        // Tell the view to know of all the new changes.
        super.view.revalidate();
    }

    @Override
    public void createView() {

        panel1 = new JPanel();

        // Required for us to use pixel measurements.
        panel1.setLayout(null);

        // Set the panel size.
        super.setPanelSize(panel1);

        JLabel label = new JLabel("Select a Prescription to Edit:");
        label.setSize(486, 40);
        label.setLocation(20, 20);
        panel1.add(label);

        super.view = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



    }
}
