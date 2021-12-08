package interface_adapters;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Window implements FrameObserver{
    /**
     * The main class from which the rest of the windows in the program inherit from.
     *
     * Instance Attributes:
     * - frame: The Window or frame on which we are building our GUI
     * - view: The view associated with this window. Since this is an abstract class,
     *         the view will be implemented by the subclasses of this class.
     * - buttonResponses: Also related to the subclasses of this class. This is a map of the buttons
     *                    in the view of a Window to the response if that button is pressed.
     * - userResponded: Used to check if the user has responded.
     * - checker: An object from ValidInputChecker that can check to see if input is valid.
     */
    protected Scanner scanner;
    protected ObservableFrame frame;
    protected Component view;
    protected Map<JButton, String> buttonResponses;
    public static ValidInputChecker checker = new ValidInputChecker();

    // The volatile keyword is used to ensure that multiple threads (in this case the frame
    // and the main program run on separate threads, can use the same object safely.
    // Tells the compiler that userResponded may change unexpectedly.
    protected volatile boolean userResponded;

    public Window(Scanner scanner, ObservableFrame frame){
        this.frame = frame;
        this.scanner = scanner;
        buttonResponses = new HashMap<>();
        userResponded = false;
    }

    public abstract String[] getUserInput();

    /**
     * Updates the window view.
     *
     * Precondition:
     * - view is not null.
     */
    public void updateFrame(){
        frame.updateView(view);

        // Update Frame is called when we want to update the window.
        // Thus we should reset userResponed so that we can get user
        // input again.

        userResponded = false;
    }

    /**
     * Sets the view.
     */
    public abstract void createView();

    /**
     * Sets the size of the panel equal to the frame.
     * @param panel     The panel/container for which we are setting the size for.
     */
    public void setPanelSize(Component panel){
        panel.setMaximumSize(new Dimension(ObservableFrame.FRAME_WIDTH, ObservableFrame.FRAME_HEIGHT));
        panel.setMinimumSize(new Dimension(ObservableFrame.FRAME_WIDTH, ObservableFrame.FRAME_HEIGHT));
        panel.setPreferredSize(new Dimension(ObservableFrame.FRAME_WIDTH, ObservableFrame.FRAME_HEIGHT));
    }

    /**
     * Adds an action listener for all the buttons associated with this window.
     * This allows us to know when a button has been clicked.
     */
    public void addActionListenerToAllButtons(){
        // As long as the map is not empty.
        if (!buttonResponses.isEmpty()) {

            // for each button, add the frame as an action listener.
            for (JButton button : buttonResponses.keySet()) {
                button.addActionListener(frame);
            }
        }
    }


}




