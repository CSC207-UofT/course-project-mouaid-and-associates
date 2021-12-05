package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditPrescriptionWindow extends ScheduleInputWindow implements DisplayEntityInformation {

    private static final int OFFSET_X = 50;
    private static final int OFFSET_Y = 90;

    private JPanel panel;
    private JTextField name2;
    private String[] validInputs;
    private JButton[] buttons = new JButton[4];
    private String[] userInput;
    private ArrayList<String> removeMedicines = new ArrayList<String>();
    private List<String> change = new ArrayList<>(List.of(new String[]{"", "", ""}));
    private int j;


    public EditPrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        j = 0;
        panel = new JPanel();
        userInput = new String[3];
        createView();
    }

    public void displayInfo(String[] info) {
        // Also add the list of medicine names as valid inputs to verify user input.
        setValidInputs(Arrays.copyOfRange(info, 0, info.length));
    }


    private void setValidInputs(String[] inputs){
        validInputs = inputs;
    }
    @Override
    public String[] getUserInput() {
//        userInput[0] = "Nothing";
//        switch (userInput[0]) {
//            case "Name of the prescription": {
//                displayEditName();
//                userInput[0] = name2.getText();
//            }
//            case "Remove a medicine from the prescription": {
//                displayRemove(validInputs);
//                change.set(1, "remove");
//            }
//            case "Add a medicine to this prescription":{
//                JLabel add = new JLabel("You'll be prompted to the add new medicine form after you click option 4");
//                add.setLocation(20, 200);
//                panel.add(add);
//                updateFrame();
//                super.view = panel;
//
//            }
//        }
//
       return change.toArray(new String[change.toArray().length]);
        //return userInput;
    }

    /**
     * Notify the observer of a change
     *
     * @param source
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
                    /*JLabel add = new JLabel("You'll be prompted to the add new medicine form after you click option 4");
                    add.setLocation(20, 200);
                    panel.add(add);*/
                    //userInput[2] = "1";
                    /*super.view = panel;*/

                    change.set(2, "Add Medicine");
                }
            else if (text.equals("Go back to the account page")) {
                    userResponded = true;

                }
           else if (text.equals("Send")) {
                    //userInput[0] = name2.getText();
                    change.set(0, name2.getText());
                    userResponded = true;
                }
           else{
                change.add(text);
                ((JButton) source).setBackground(Color.RED);
                ((JButton) source).setOpaque(true);
                updateFrame();

            }

//            System.out.println(j);
//            // Since changes is an ordered list of text boxes, thus
//            // we just add the items to the userInput.
//
//
//            if (j != 0) {
//                if(userInput[0].equals("Name of the prescription")){
//                    change.set(0, this.name2.getText());
//                    super.userResponded = true;
//                    j = 0;
//                }
//                else if(userInput[0].equals("Remove a medicine from the prescription") &&
//                        !((JButton) source).getText().equals("Back")){
//                    change.add(((JButton) source).getText());
//                }
//                else if(userInput[0].equals("Add a medicine to this prescription")){
//                    change.set(2, "3");
//                    super.userResponded = true;
//                    j = 0;
//                }
//                else{
//                    // goes back to ViewAccountWindow
//                    super.userResponded = true;
//                    j = 0;
//                    if (change.get(1) != "") {
//                        change = List.of(new String[]{"", "", ""});
//                    }
//                }
//
//
//
//            }
//            else {
//                userInput[0] = ((JButton) source).getText();
//                j++;
//            }
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

        buttons = new JButton[]{name, add, remove, back};
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

        int i = 1;
        for (String pieceOfInfo : medicines){
            System.out.println(pieceOfInfo);

            JButton button = new JButton(pieceOfInfo);
            button.setSize(152, 90);
            button.setLocation(7+ (((i-1)%3))*(152), 650 + (i-1)/3*(190) );
            i++;
            panel.add(button);
            super.buttonResponses.put(button, button.getText());

            //System.out.println(info_count);
        }

        super.addActionListenerToAllButtons();
        super.view = panel;
        updateFrame();


    }
}
