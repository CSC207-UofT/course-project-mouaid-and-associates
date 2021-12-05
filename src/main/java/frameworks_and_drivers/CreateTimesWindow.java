package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateTimesWindow extends Window {

    private JPanel panel;
    private JTextField times = new JTextField("0");
    private List<String> userInput;
    private List<JLabel> medTimesLabel;
    private int numTimes;

    public CreateTimesWindow(Scanner scanner, ObservableFrame frame, int time){
        super(scanner,frame);
        createView();
        userInput = new ArrayList<>();
        medTimesLabel = new ArrayList<>();
        this.numTimes = time;
    }


    @Override
    public void update(ObservableFrame frame, Object source) {

    }

    @Override
    public String[] getUserInput() {
        return new String[0];
    }

    @Override
    public void createView() {
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);
        List<JTextField> medTimes = new ArrayList<>();
        JLabel medTimesLabel;
        for (int i = 0; i < numTimes; i ++ ){
            medTimesLabel = new JLabel("For the " + i + "'st time what time do you need to take it? Enter in form XX:XX");
            medTimesLabel.setSize(466, 60);
            medTimesLabel.add(medTimesLabel);
            panel.add(medTimesLabel);
        }
        for (int i = 0; i < numTimes; i++){
            JTextField textField = new JTextField("");
            textField.setSize(286, 60);
            medTimes.add(textField);
            panel.add(textField);
        }
        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setSize(400, 70);

        panel.add(saveButton);
        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

}
