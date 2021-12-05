package interface_adapters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the frame and window used to display the app.
 *
 * Instance Attributes:
 * - currentView: The current view that is being shown on the frame.
 * - observers: All the observers that will be affected by an action identified by action listener.
 * -
 */
public class ObservableFrame extends JFrame implements ActionListener {
    private Component currentView;
    private List<FrameObserver> observers;
    public static final int FRAME_WIDTH = 486;
    public static final int FRAME_HEIGHT = 1000;
    public ObservableFrame(){
        super("Medicine Management App");
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setResizable(false);
        this.setVisible(true);
        observers = new ArrayList<>();
    }

    /**
     * Updates the view on the screen.
     * @param view  The new view that is to be displayed.
     */
    public void updateView(Component view){
        if (currentView != null){
            // Remove the old view from the screen.
            this.remove(currentView);
        }
        // Add the new view onto the screen.
        this.add(view);
        // Set the current view to this new view.
        currentView = view;
        // Validate the change (or else the change won't show on the screen)
//        this.validate();
        // Validate the change and resize the window. Used to reliably update the frame.
        this.pack();
    }



    /**
     * Adds a new observer to this class
     * @param newObserver   The new observer.
     */
    public void addObserver(FrameObserver newObserver){
        observers.add(newObserver);
    }

    /**
     * Removes all observers from this observable class
     */
    public void removeAllObservers(){
        observers.clear();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        notifyObservers(e);
    }

    /**
     * Notifies all the observers of a change. In this case, a button being
     * pressed.
     * @param e The event corresponding to a button being pressed.
     */
    public void notifyObservers(ActionEvent e){
        for (FrameObserver observer: observers){
            // Sends an instance of this observable class and the source (i.e. the button)
            // that caused the ActionEvent e.
            observer.update(e.getSource());
        }
        repaint();
    }
}
