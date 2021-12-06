package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewAccountWindow extends Window implements DisplayEntityInformation {

    private static final int OFFSET_X = 7;
    private static final int OFFSET_Y = 120;
    private final String[] userInput;
    private final JPanel panel;
    private List<JLabel> displayedInfo;

    public ViewAccountWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        panel = new JPanel();
        userInput = new String[1];
        createView();
        displayedInfo = new ArrayList<>();
    }

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    @Override
    public String[] getUserInput() {return userInput;}


    @Override
    public void createView() {

        // Our container for the components (like a board on which you pin things)

        // The layout in which components are placed.
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);

        JButton pres = new JButton("Add Prescription");
        JButton logout = new JButton("Logout");

        //adds the buttons
        JButton rem_pres = new JButton("Remove Prescription");
        JButton edit_pres = new JButton("Edit Prescription");
        JButton edit_med = new JButton("Edit Medicine");
        JButton rem_med = new JButton("Remove Medicine");
        JButton add_med = new JButton("Add Medicine");
        JButton sleep = new JButton("Set Sleep Times");
        JButton view = new JButton("View Timetable");
        JButton meal = new JButton("Set Meal Times");

        JButton[] buttons = new JButton[]{pres, add_med, sleep, rem_pres, rem_med,
                view, edit_pres, edit_med, meal, logout};
        int i = 1;
        for (JButton button: buttons){
            button.setSize(152, 90);
            button.setLocation(OFFSET_X + (((i-1)%3))*(152), OFFSET_Y + (i-1)/3*(90) );
            i++;
            panel.add(button);
            super.buttonResponses.put(button, button.getText());

        }

        //reset the count
        super.buttonResponses.put(logout, logout.getText());

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // Set the view for this window.
        super.view = panel;

    }
    /**
     * prints the user information at the panel
     *
     * @param info the user information
     */
    @Override
    public void displayInfo(String[] info) {
        panel.setLayout(null);
        JLabel label1 = new JLabel();
        label1.setText("Welcome " + info[0].substring(5) + "!");
        label1.setLocation(110, 40);
        label1.setSize(152*3,30);
        label1.setFont(new Font("Serif", Font.PLAIN, 28));
        panel.add(label1);

        if (!displayedInfo.isEmpty()){
            removeOldInfo();
        }

        int j = 0;
        for (String pieceOfInfo : info) {
            JLabel label = new JLabel();
            label.setText(pieceOfInfo);
            label.setLocation(20, OFFSET_Y + 390 + 30 * j);
            label.setSize(152 * 3, 15);
            j++;

            panel.add(label);
            displayedInfo.add(label);
        }
    }

    /**
     * Removes the old information from the window
     */
    private void removeOldInfo() {
        for (JLabel label: displayedInfo){
            panel.remove(label);
        }
        displayedInfo.clear();
    }

    /**
     * Notify the observer of a change
     *
     * @param source the button clicked
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            // Since changes is an ordered list of text boxes, thus
            // we just add the items to the userInput.
            userInput[0] = ((JButton) source).getText();
            //System.out.println(((JButton) source).getText());
            // Best to do this after you make all your changes.
            super.userResponded = true;
        }
    }
}
