package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SelectTimesWindow extends Window {
    /**
     * This is the window where you can create the times you want to take a medicine
     */
    private ArrayList<String> userInput;
    private JTextField selectTimes = new JTextField();
    private JPanel panel;

    public SelectTimesWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        createView();
        userInput = new ArrayList<>();
    }

    @Override
    public String[] getUserInput(){
        String[] userInput = new String[1];
        return userInput;
    }

    @Override
    public void createView(){
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);
    }


    @Override
    public void update(Object source) {

    }
}
