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

public class EditMedicineWindow extends Window implements DisplayEntityInformation{
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
     * - timesInputLabels: The labels for when the user decides to change the medicine times
     * - timesInput: The text boxes for when the user decides to change the medicine times.
     * - infoOfMed: The information of the medicine displayed onto the window.
     *
     * Precondition:
     *  - The displayInfo method is called before getUserInput.
     *  - ChooseMedicineToEditMethod class is called before this class.
     */
    private List<JTextField> changes;
    private List<JLabel> changeLabels;
    private JLabel[] timesInputLabels;
    private JTextField[] timesInput;
    private JPanel panel;
    private volatile List<String> userInput;
    private Font labelFont;
    private List<JLabel> infoOfMed;
    private boolean askedChangeTimes;
    private SelectTimesWindow selectTimes;

    public EditMedicineWindow(Scanner scanner, ObservableFrame frame, SelectTimesWindow selectTimesWindow) {
        super(scanner, frame);
        createView();
        userInput = new ArrayList<>();
        labelFont = new Font("SansSerif", Font.BOLD, 16);
        infoOfMed = new ArrayList<>();
        selectTimes = selectTimesWindow;
    }

    /**
     * Returns user input
     * @return  Returns a list of user input, must be of size greater than
     * or equal to 5.
     */
    @Override
    public String[] getUserInput() {
        String[] userResponse = new String[]{"", "", "", "", ""};
        String[] times;
        // We want the returned user input to be have length greater than or equal to
        // 5 and for user to have clicked the save button (the variable userResponded is true)
        // Thus in order to wait, we use a while loop. We only set userResponse, the return value
        // when we have userInput to have size greater than or equal to 5. Or else, it might return
        // the preemptively return the input. This is because the main program and the window
        // run on different threads, and getUserInput is a method called in the 'main' thread,
        // while userInput is modified in the 'window' thread, thus userInput might change while
        // getUserInput is running, thus we need to make sure the change is correct before setting
        // and returning userResponse.
        while (!(super.userResponded && userInput.size() >= 5 && !askedChangeTimes)){
            if (askedChangeTimes){
                selectTimes.setNumTimes(Integer.parseInt(timesInput[3].getText()));
                selectTimes.updateFrame();
                times = selectTimes.getUserInput();

                if (userInput.size() == 5){
                    if (!checker.isWeekOrDaily(timesInput[0].getText())){
                            userInput.add(timesInput[0].getText());
                    }
                    if (!checker.isValidDay(timesInput[1].getText(), timesInput[2].getText())){
                        userInput.add(timesInput[1].getText());
                    }
                    if (!checker.isValidMonth(timesInput[2].getText())){
                        userInput.add(timesInput[2].getText());
                    }
                    userInput.addAll(Arrays.asList(times));
                }

                // Set it to false to indicate that we have finished asking times.
                askedChangeTimes = false;
            }

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

        // Set this boolean to false (i.e. the user did not ask to change the times)
        askedChangeTimes = false;

        // Set the locations of the rest of the attributes.
        setLocations(lastLabelBottomBorder);
    }

    private void removeOldMedInfo(){
        for (JLabel prevInfo: infoOfMed){
            panel.remove(prevInfo);
        }
        infoOfMed.clear();
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

            if (super.buttonResponses.get((JButton) source).equals("add times")){
                askedChangeTimes = true;
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

        String[] timeLabels = new String[]{"Do you want to take the medicine weekly or daily?",
                "Enter the day of the month you will start taking the medication",
                "Enter the month in terms of 01 for January, 10 for October, etc.",
                "Enter the number of times you will take the medicine in a day:"};

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

        // Create the labels for the times, as well as their text boxes:
        timesInput = new JTextField[timeLabels.length];
        timesInputLabels = new JLabel[timeLabels.length];
        for (int i = 0; i < timeLabels.length; i++) {
            JLabel timeLabel = new JLabel(timeLabels[i]);
            JTextField timeTextField = new JTextField("");
            timeTextField.setSize(286, 60);
            timeLabel.setSize(466, 60);
            timeLabel.setFont(labelFont);

            // Add time label and the time text field to the array
            timesInput[i] = timeTextField;
            timesInputLabels[i] = timeLabel;

            // Add time label and the time text field to the panel
            panel.add(timeLabel);
            panel.add(timeTextField);
        }

        // Create the button to return to edit medicine.
        Font buttonFont = new Font("SansSerif", Font.BOLD, 10);
        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setFont(buttonFont);
        saveButton.setSize(200, 70);

        // Create the button to edit the times of the medicine.
        JButton addTimes = new JButton("Change Med Times");
        addTimes.setFont(buttonFont);
        addTimes.setSize(200, 70);

        // Add the two buttons to the panel.
        panel.add(saveButton);
        panel.add(addTimes);

        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "save and return");
        super.buttonResponses.put(addTimes, "add times");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // set the view to the panel for now, this will change in the displayInfo method
        // which will be called before we actually view the screen.
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        for (int i = 0; i < timesInput.length; i++){
            timesInputLabels[i].setLocation(20, y + 20 + 140 * (changes.size() + i));
            timesInput[i].setLocation(20, y + 80 + 140 * (changes.size() + i));
        }

        for (JButton button: super.buttonResponses.keySet()){
            if (super.buttonResponses.get(button).equals("add times")){
                button.setLocation(245, y + 40 + 140 * (changes.size() + timesInput.length));
            } else {
                button.setLocation(43, y + 40 + 140 * (changes.size() + timesInput.length));
            }
        }

        panel.setPreferredSize(new Dimension(486,
                y + 160 + 140 * (changes.size() + timesInput.length)));

        super.view.revalidate();
        super.view.repaint();
    }

    /**
     * Resets all the text fields to contain an empty string.
     */
    private void resetTextFields(){
        for (JTextField textField: changes){
            textField.setText("");
        }
        for (JTextField textField: timesInput){
            textField.setText("");
        }
    }
}
