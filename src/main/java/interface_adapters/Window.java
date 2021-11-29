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
     */
    protected Scanner scanner;
    private ObservableFrame frame;
    protected Component view;
    protected Map<JButton, String> buttonResponses;
    protected boolean userResponded;

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




