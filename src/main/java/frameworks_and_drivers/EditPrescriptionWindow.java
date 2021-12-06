package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditPrescriptionWindow extends Window implements DisplayEntityInformation {

    private static final int OFFSET_X = 50;
    private static final int OFFSET_Y = 90;

    private final JPanel panel;
    private JTextField name2;
    private String[] validInputs;
    private final List<String> change = new ArrayList<>(List.of(new String[]{"", "", ""}));
    private int j;


    public EditPrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        j = 0;
        panel = new JPanel();
        createView();
    }

    /**
     * Gets the medicines in the prescription
     *
     * @param info the medicines in the prescription
     */
    public void displayInfo(String[] info) {
        // Also add the list of medicine names as valid inputs to verify user input.
        setValidInputs(Arrays.copyOfRange(info, 0, info.length));
    }

    private void setValidInputs(String[] inputs){
        validInputs = inputs;
    }
    @Override
    public String[] getUserInput() {
       return change.toArray(new String[change.toArray().length]);
    }

    /**
     * Notify the observer of a change
     *
     * @param source the button clicked
     */
    @Override
    public void update(Object source) {

        if (super.buttonResponses.containsKey((JButton) source)){
            String text = ((JButton) source).getText();
            if (text.equals("Name of the prescription")) {
                displayEditName();
            }
            else if (text.equals("Remove a medicine from the prescription")){
                displayRemove(validInputs);
                //userInput[1] = ((JButton) source).getText();
                change.set(1, "remove");
            }
            else if (text.equals( "Add a medicine to this prescription") ){
                change.set(2, "Add Medicine");
            }
            else if (text.equals("Go back to the account page")) {
                userResponded = true;
            }
            else if (text.equals("Send")) {
                //userInput[0] = name2.getText();
                change.set(0, name2.getText());
                userResponded = true;
            } else {
                change.add(text);
                ((JButton) source).setBackground(Color.RED);
                ((JButton) source).setOpaque(true);
                this.updateFrame();
            }
        }
    }

    @Override
    public void createView() {
        if( j == 0) {
            panel.setLayout(null);
        }
        // Set the size of the panel.
        super.setPanelSize(panel);

        JLabel edit = new JLabel("What would you like to edit in the prescription?");

        edit.setSize(ObservableFrame.FRAME_WIDTH,50);
        edit.setLocation(20, 10);
        panel.add(edit);

        JButton name = new JButton("Name of the prescription");
        JButton add = new JButton("Add a medicine to this prescription");
        JButton remove = new JButton("Remove a medicine from the prescription");
        JButton back = new JButton("Go back to the account page");

        JButton[] buttons = new JButton[]{name, add, remove, back};
        int i = 0;

        for (JButton button: buttons){
            button.setSize(380, 90);
            button.setLocation(OFFSET_X, OFFSET_Y + i*(120) - (i-1)*30 );
            i++;
            panel.add(button);
            if (j == 0) {
                super.buttonResponses.put(button, button.getText());
            }
        }

    // Add an action listener for each button.
        super.addActionListenerToAllButtons();

    // Set the view for this window.
        super.view = panel;
}
    private void displayEditName(){
        JLabel name = new JLabel("Enter the new name of prescription");
        name.setLocation(20, 550);
        name.setSize(150*3,15);
        panel.add(name);

              // A text box.
        name2 = new JTextField();
        name2.setSize(300, 50);
        name2.setLocation(20,600);
        panel.add(name2);

        JButton send = new JButton("Send");
        send.setLocation(200, 650);
        send.setSize(100, 70);
        panel.add(send);

        super.buttonResponses.clear();
        super.buttonResponses.put(send, send.getText());

    // Add an action listener for each button.
        super.addActionListenerToAllButtons();

    // Set the view for this window.
        super.view = panel;
        updateFrame();
    }

    /**
     * displays medicines as a button
     *
     * @param medicines the button clicked
     */

    private void displayRemove(String [] medicines){
        super.buttonResponses.clear();
        //adds the label
        JLabel remove = new JLabel(" Select the medicines to remove");
        remove.setLocation(20, 625);
        remove.setSize(150*3,15);
        panel.add(remove);

        //adds the back button
        JButton back = new JButton("Go back to the account page");
        back.setLocation(OFFSET_X, OFFSET_Y + 4*(120) - (3)*30 );
        back.setSize(380,90);
        back.setBackground(Color.RED);
        back.setOpaque(true);
        panel.add(back);
        super.buttonResponses.put(back, back.getText());

        int i = 1;
        for (String pieceOfInfo : medicines){
            System.out.println(pieceOfInfo);

            JButton button = new JButton(pieceOfInfo);
            button.setSize(152, 90);
            button.setLocation(7+ (((i-1)%3))*(152), 650 + (i-1)/3*(190) );
            i++;
            panel.add(button);
            super.buttonResponses.put(button, button.getText());

        }

        super.addActionListenerToAllButtons();
        super.view = panel;
        updateFrame();


    }
}