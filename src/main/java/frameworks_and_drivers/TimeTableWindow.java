package frameworks_and_drivers;


import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeTableWindow extends Window implements DisplayEntityInformation {
    /**
     * The window that displays the Timetable page.
     */
    private volatile String[] userInput;
    private JPanel panel;
    private List<JLabel> currentSchedule;
    private Font font;
    public TimeTableWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();
        userInput = new String[1];
        currentSchedule = new ArrayList<>();
        font = new Font("SansSerif", Font.BOLD, 18);
    }

    /**
     * Gets input from the user on whether they want to go to the View Account Page
     * @return A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        // One way to wait until userResponded is true.
        while(!super.userResponded){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return userInput;
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info  Information to be displayed onto the screen.
     */
    @Override
    public void displayInfo(String[] info) {
        int xCoord = 20;
        int startingY =  30;

        if (!currentSchedule.isEmpty()){
            removeOldInfo();
        }

        for (int i = 0; i < info.length; i++){
            JLabel label = new JLabel(info[i]);
            label.setSize(466, 40);
            label.setLocation(xCoord, startingY + 40 * i);
            label.setFont(font);
            panel.add(label);

            // Add the label to the currentSchedule. This is so we can remove it next time.
            currentSchedule.add(label);
        }
        // Set the location of the return button.
        // startingY + 50 * info.length + 50 is the bottom of the last label.
        setLocationOfButton(startingY + 40 * info.length);
    }

    /**
     * Removes the old info from this window.
     */
    private void removeOldInfo(){
        currentSchedule.clear();
    }

    /**
     * Notify this window of a button being pressed.
     *
     * @param source   The button the event is from.
     */
    @Override
    public void update(Object source) {
        // Check if the button is from this window.
        if (super.buttonResponses.containsKey((JButton) source)){
            userInput[0] = "0";
            super.userResponded = true;
        }
    }

    @Override
    public void createView() {
        panel = new JPanel();   // Create a new JPanel
        panel.setLayout(null);  // Set the layout to null
        super.setPanelSize(panel);

        // Create a new button to return to Account page
        JButton returnButton = new JButton("Return to Account Page");
        returnButton.setSize(306, 60);
        returnButton.setFont(font);

        super.buttonResponses.put(returnButton, "0");       // Add to button responses.

        super.addActionListenerToAllButtons();  // Make sure that we can listen for actions.

        // Add the button to the panel.
        panel.add(returnButton);

        // Set the view for this window.
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    /**
     * Sets the location of the button based on the parameter
     * @param y  The y location we are basing the location of the button on.
     */
    private void setLocationOfButton(int y){
        // Since there is only one button, we can set all the buttons in the
        // set to the same location, since there is only one button.
        JButton[] buttons = super.buttonResponses.keySet().toArray(new JButton[0]);
        buttons[0].setLocation(90, y + 20);

        // Resize the panel. y + 20 + 60 is the bottom of the window.
        panel.setPreferredSize(new Dimension(486, y + 80));

        super.view.revalidate();
    }
}
