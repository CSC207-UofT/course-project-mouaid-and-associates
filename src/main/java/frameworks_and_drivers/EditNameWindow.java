package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditNameWindow extends ScheduleInputWindow {

    private JPanel panel = new JPanel();
    private JTextField name2 = new JTextField("Enter new Name");
    private String[] validInputs;
    private String[] userInput = new String[1];
    private int j = 0;


    public EditNameWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        createView();
    }

    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)) {
            userInput[0] = ((JButton) source).getText();
        }

    }

    @Override
    public String[] getUserInput() {
        return userInput;
    }

    @Override
    public void createView() {
        panel.setLayout(null);
        JLabel name = new JLabel("Enter the new name of prescription");
        name.setLocation(20, 100);
        name.setSize(150*3,15);
        panel.add(name);


        // A text box.
        name2.setSize(300, 50);
        name2.setLocation(20,200);
        panel.add(name2);

        JButton send = new JButton("Send");
        send.setLocation(200, 400);
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
}





