package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;

import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class RemoveMedicinePrescriptionWindow extends ScheduleInputWindow implements DisplayEntityInformation {

    private JPanel panel = new JPanel();
    private JTextField name2 = new JTextField("Enter new Name");
    private String[] validInputs;
    private JButton[] buttons = new JButton[4];
    private String[] userInput = new String[1];
    private List<String> change = new ArrayList<>(List.of(new String[]{"", "", ""}));
    private int j = 0;


    public RemoveMedicinePrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        createView();
    }
    @Override
    public void displayInfo(String[] info) {
        // Also add the list of medicine names as valid inputs to verify user input.
        setValidInputs(Arrays.copyOfRange(info, 0, info.length));
    }

    private void setValidInputs(String[] inputs){
        validInputs = inputs;
    }

    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey(source) && !((JButton) source).getText().equals("Back")){
            change.add(((JButton) source).getText());
        }
        else{
            userResponded =  true;
        }

    }

    @Override
    public String[] getUserInput() {
        return userInput;
    }

    @Override
    public void createView() {
        panel.setLayout(null);
        //adds the label
        JLabel remove = new JLabel("Type the name of the medicine that you would like to remove");
        remove.setLocation(20, 450);
        remove.setSize(150*3,15);
        panel.add(remove);

        //adds the back button
        JButton back = new JButton("Back");
        back.setLocation(0, ObservableFrame.FRAME_HEIGHT-80);
        back.setSize(80,80);
        panel.add(back);

        int info_count = 0;
        for (String pieceOfInfo : validInputs){
            System.out.println(pieceOfInfo);

            JButton label = new JButton();
            label.setText(pieceOfInfo);
            label.setLocation(20, 480 + 15*info_count);
            label.setSize(152*3,15);

            panel.add(label);
            info_count ++;
            //System.out.println(info_count);
        }

        super.addActionListenerToAllButtons();
        super.view = panel;
        updateFrame();
    }
}
