package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddPrescriptionWindow extends Window {

    JTextField name;
    JTextField medicineNames;
    private ArrayList<String> userInput;
    private JLabel nameLabel;
    private JLabel medicineNamesLabel;

    public AddPrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        userInput = new ArrayList<>();
        createView();
    }

    @Override
    public String[] getUserInput() {
        String[] returnList = new String[2];
        if (super.userResponded && userInput.size() == 2){
                returnList[0] = userInput.get(0);
                returnList[1] = userInput.get(1);
            }
        return returnList;
//        while (!(super.userResponded && userInput.size() == 2)) {
//            if (super.userResponded && userInput.size() == 2){
//                returnList[0] = userInput.get(0);
//            }
//        }
//        return returnList;
    }


    /**
     * Notify the observer of a change
     *
     * @param frame
     * @param source
     */
    @Override
    public void update(ObservableFrame frame, Object source) {
        userInput = new ArrayList<>();
        if (super.buttonResponses.containsKey(source)){
            userInput.add(name.getText());
            userInput.add(medicineNames.getText());
            super.userResponded = true;
        }
    }

    @Override
    public void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);
        name = new JTextField("");
        medicineNames = new JTextField("");

        nameLabel = new JLabel("Name of Prescription:");
        medicineNamesLabel = new JLabel("Name of existing medicines. Separate by comma:");
        nameLabel.setSize(200, 40);
        name.setSize(200, 50);
        nameLabel.setLocation(100, 50);
        name.setLocation(100, 100);

        medicineNamesLabel.setSize(400, 40);
        medicineNames.setSize(200, 50);
        medicineNamesLabel.setLocation(50, 150);
        medicineNames.setLocation(100, 200);

        JButton create = new JButton("Create Medicines");
        create.setLocation(100, 250);
        create.setSize(100, 70);

        panel.add(name);
        panel.add(nameLabel);
        panel.add(medicineNames);
        panel.add(medicineNamesLabel);
        panel.add(create);

        super.buttonResponses.put(create, "0");

        super.view = panel;

    }


}
