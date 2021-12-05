package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Scanner;

public class ViewAccountWindow extends Window implements DisplayEntityInformation {

    private static final int OFFSET_X = 7;
    private  static final int OFFSET_Y = 120;
    private JButton[] buttons = new JButton[10];
    private String[] userInput;
    private JButton logout;
    private JPanel panel;
    private static int info_count;


    public ViewAccountWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        panel = new JPanel();
        userInput = new String[1];
        createView();
    }

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    @Override
    public String[] getUserInput() {

           /* System.out.println("Type 'add' to add a new medicine \n" +
                    "Type 'pres' to add a new prescription \n" +
                    "Type 'remove pres' to remove a prescription \n" +
                    "Type 'edit pres' to edit your prescriptions \n" +
                    "Type 'edit' to edit a medicine\n" +
                    "Type 'remove' to remove a medicine\n" +
                    "Type 'set sleep times' to set your sleep and wakeup times \n" +
                    "Type 'set meal times' to set your meal times \n" +
                    "Type 'view' to view the timetable \n" +
                    "Type 'logout' to logout. \n");*/
        return userInput;



    }


    @Override
    public void createView() {

        // Our container for the components (like a board on which you pin things)

        // The layout in which components are placed.
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);

        JButton pres = new JButton("Add Prescription");
        logout = new JButton("Logout");


        JButton rem_pres = new JButton("Remove Prescription");
        JButton edit_pres = new JButton("Edit Prescription");
        JButton edit_med = new JButton("Edit Medicine");
        JButton rem_med = new JButton("Remove Medicine");
        JButton add_med = new JButton("Add Medicine");
        JButton sleep = new JButton("Set Sleep Times");
        JButton view = new JButton("View Timetable");
        JButton meal = new JButton("Set Meal Times");

        buttons = new JButton[]{pres, add_med, sleep, rem_pres, rem_med, view, edit_pres, edit_med, meal};
        int i = 1;
        for (JButton button: buttons){
            button.setSize(152, 90);
            button.setLocation(OFFSET_X + (((i-1)%3))*(182) - (i-1)%3*30, OFFSET_Y + (i-1)/3*(120) - (i-1)/3*30 + info_count*15 );
            i++;
            panel.add(button);
            super.buttonResponses.put(button, button.getText());

        }
        logout.setSize(200, 80);
        logout.setLocation(ObservableFrame.FRAME_WIDTH/2 -100, ObservableFrame.FRAME_HEIGHT/2 - 90 + info_count*15);
        panel.add(logout);

        //reset the count
        info_count = 0;
        super.buttonResponses.put(logout, logout.getText());

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // Set the view for this window.
        super.view = panel;

    }

    @Override
    public void displayInfo(String[] info) {
        for (String pieceOfInfo : info){
            System.out.println(pieceOfInfo);

            JLabel label = new JLabel();
            label.setText(pieceOfInfo);
            label.setLocation(20, 30 + 15*info_count);
            label.setSize(152*3,15);

            panel.add(label);
            info_count ++;
            //System.out.println(info_count);
        }



    }

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
