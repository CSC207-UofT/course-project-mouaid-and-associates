package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class ChooseMedicineToEditWindow extends Window implements DisplayEntityInformation {
    private String[] validInputs;
    private volatile String[] userInput;
    private JPanel panel;


    public ChooseMedicineToEditWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();
        userInput = new String[]{""};
        validInputs = new String[0];
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info The information to be printed. In this case, a list of medicine names,
     *             and a title.
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

    private void setValidInputs(String[] inputs) {
        validInputs = new String[inputs.length];
        for (int i = 0; i < inputs.length; i++){
            validInputs[i] = inputs[i].substring(3);
        }

    }

    private void removeOldInfo(){
        for (JButton button: super.buttonResponses.keySet()){
            panel.remove(button);
        }
        super.buttonResponses.clear();
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

    private boolean verifyInput(String input) {
        for (String validInput : validInputs) {
            if (validInput.equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update userInput based on if the event originated from this window.
     *
     * @param source    The button that is the source of the event.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton)source)) {
            userInput[0] = super.buttonResponses.get((JButton) source);
            super.userResponded = true;
        }
    }
    public void createButton(int y) {
        for (int i  = 0; i < validInputs.length; i++) {
            JButton button = new JButton(validInputs[i]);
            button.setSize(286, 90);
            button.setLocation(100, y + 95 * i);
            panel.add(button);
            super.buttonResponses.put(button, validInputs[i]);
        }

        super.addActionListenerToAllButtons();

        // Set the preferred size so we can contain all the new buttons.
        panel.setPreferredSize(new Dimension(486, validInputs.length + 90));

        // Tell the view to know of all the new changes.
        super.view.revalidate();
        super.view.repaint();
    }


    @Override
    public void createView() {
        panel = new JPanel();

        // Required for us to use pixel measurements.
        panel.setLayout(null);

        // Set the panel size.
        super.setPanelSize(panel);

        JLabel label = new JLabel("Select a Medicine to Edit:");
        label.setSize(486, 40);
        label.setLocation(20, 20);
        panel.add(label);

        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
