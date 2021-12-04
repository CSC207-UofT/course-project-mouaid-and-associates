package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditMedicineWindow extends ScheduleInputWindow implements DisplayEntityInformation{
    /**
     * This is a window showing the edit medicine functionality. Allows the user to interact
     * with the program and edit the chosen medicine.
     *
     * Instance Attributes:
     * - changes: A list of text boxes into which the user can input the new values
     * - changeLabels: A list of labels for the text boxes.
     * - panel: The panel on to which we will put components for this window. We need a specific view
     *          for this class because we update the frame after creating it.
     * - userInput: Stores the text the user entered into the text boxes.
     *
     * Precondition:
     *  - The displayInfo method is called before getUserInput.
     *  - ChooseMedicineToEditMethod class is called before this class.
     */
    private List<JTextField> changes;
    private List<JLabel> changeLabels;
    private JPanel panel;
    private List<String> userInput;
    private Font labelFont;
    private List<JLabel> infoOfMed;

    public EditMedicineWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();
        userInput = new ArrayList<>();
        labelFont = new Font("SansSerif", Font.BOLD, 16);
        infoOfMed = new ArrayList<>();
    }

    @Override
    public String[] getUserInput() {
        String[] userResponse = new String[]{"", "", "", "", ""};

        // We want the returned user input to be have length greater than or equal to
        // 5 and for user to have clicked the save button (the variable userResponded is true)
        // Thus in order to wait, we use a while loop. We only set userResponse, the return value
        // when we have userInput to have size greater than or equal to 5. Or else, it might return
        // the preemptively return the input. This is because the main program and the window
        // run on different threads, and getUserInput is a method called in the 'main' thread,
        // while userInput is modified in the 'window' thread, thus userInput might change while
        // getUserInput is running, thus we need to make sure the change is correct before setting
        // and returning userResponse.
        while (!(super.userResponded && userInput.size() >= 5)){

            // Only when we have a valid number of inputs do
            // we get all the user input.
            if (userInput.size() >= 5) {
                userResponse = userInput.toArray(new String[0]);
            }
        }

        // Clear the input list so that we can use it again next time.
        userInput.clear();

        // Empty all the text boxes
        resetTextFields();

        // Return user input.
        return userResponse;
    }

    /**
     * Prints out information, in this case medicine information, onto the screen.
     *
     * @param info      The information to be printed.
     */
    @Override
    public void displayInfo(String[] info) {
        int lastLabelBottomBorder = 0;

        // If this method has been called before (i.e. user has edited medicine before)
        // Then remove the old info so that new info can be put in their place.
        if (!infoOfMed.isEmpty()){
            removeOldMedInfo();
        }

        for (int i = 0; i < info.length; i++){
            JLabel label = new JLabel(info[i]);
            label.setFont(labelFont);
            label.setSize(466, 60);
            // Ensures that there is a 10 pixel padding between each label.
            // Since the height is 60 pixels, + 10 more for padding is how we get 70 pixels.
            label.setLocation(20, 20 + 60 * i);

            // Add to the panel and infoOfMed.
            panel.add(label);
            infoOfMed.add(label);

            // On the last iteration.
            if (i == info.length - 1){
                lastLabelBottomBorder = i * 60 + 20 + 60;
            }
        }

        // Set the locations of the rest of the attributes.
        setLocations(lastLabelBottomBorder);
    }

    private void removeOldMedInfo(){
        for (JLabel prevInfo: infoOfMed){
            panel.remove(prevInfo);
        }
    }

    /**
     * Sets userInput if the source is the save button in this view.
     * This method is synchronized so that when multiple threads
     * access this variable, we can ensure that only one thread
     * can access this method at a time.
     * @param source   The source of the event.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            // Since changes is an ordered list of text boxes, thus
            // we just add the items to the userInput.

            for (int i = 0; i < changes.size(); i++) {
                userInput.add(changes.get(i).getText());
            }

            // Best to do this after you make all your changes.
            super.userResponded = true;
        }
    }


    @Override
    public void createView() {
        // Create a new JPanel
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

        // Create a list of messages to print onto the labels:
        String[] labels = new String[]{"New name of the medicine:",
                "New unit of measurement for the medicine \n " +
                "(e.g. mL, pill, tsp, g, mg, etc.)",
                "New method of administration:",
                "New dosage of the medicine. " +
                "Enter the number only, as it will be measured in the units specified:",
                "New extra instructions:"};

        // Create all the text boxes but don't set their locations yet.
        changes = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            JTextField textField = new JTextField("");
            textField.setSize(286, 60);
            changes.add(textField);
            panel.add(textField);
        }

        // Create all the corresponding labels but don't set their locations yet.
        changeLabels = new ArrayList<>();
        for (String label: labels){
            JLabel changeLabel = new JLabel(label);
            changeLabel.setSize(466, 60);
            changeLabel.setFont(labelFont);

            changeLabels.add(changeLabel);
            panel.add(changeLabel);
        }

        // Create the button.
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setFont(buttonFont);
        saveButton.setSize(400, 70);

        panel.add(saveButton);

        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // set the view to the panel for now, this will change in the displayInfo method
        // which will be called before we actually view the screen.
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * Sets the locations for all the attributes of this class, specifically
     * changes, changeLabels, and saveButton. Since we don't know how much information
     * will need to be printed for the medicine, we need the location of the lowest
     * label on the panel to build of the rest.
     *
     * @param y     The y coordinate of the last label.
     */
    private void setLocations(int y){
        // Ensures there are the same number of labels as there are text boxes.
        assert (changes.size() == changeLabels.size());

        for (int i = 0; i < changes.size(); i++){
            changeLabels.get(i).setLocation(20, y + 20 + 140 * i);
            changes.get(i).setLocation(20, y + 80 + 140 * i);
        }

        for (JButton button: super.buttonResponses.keySet()){
            button.setLocation(43, y + 80 + 140 * changes.size());
        }

        panel.setPreferredSize(new Dimension(486, y + 160 + 140 * changes.size()));

        super.view.revalidate();
    }

    /**
     * Resets all the text fields to contain an empty string.
     */
    private void resetTextFields(){
        for (JTextField textField: changes){
            textField.setText("");
        }
    }
}
