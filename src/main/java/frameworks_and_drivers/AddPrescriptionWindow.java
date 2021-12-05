package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.PrescriptionWindow;
import interface_adapters.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddPrescriptionWindow extends PrescriptionWindow {

    Window getMedicineWindow;
    JTextField name;
    JTextField numMedicine;
    private ArrayList<String> userInput;
    public AddPrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        getMedicineWindow = new AddMedicineWindow(scanner, frame);
    }

    @Override
    public String[] getUserInput() {
        String[] returnList =  {name.getText()};
        return returnList;
    }

    /**
     * Return the user input on their new prescription.
     *
     * Preconditions:
     *  - addMedicineWindow is an instance of AddMedicineWindow.
     *
     * @param addMedicineWindow  The window that allows users to add a medicine
     * @return   The user's new prescription information.
     */
    @Override
    public List<String[]> getUserPrescriptionInput(Window addMedicineWindow) {
        int accumulator = 0;
        List<String[]> medicines = new ArrayList<>();
        while(accumulator != Integer.parseInt(numMedicine.getText())){
            medicines.add(getMedicineWindow.getUserInput());
            accumulator += 1;
        }
        return medicines;

    }
    /**
     * Notify the observer of a change
     *
     * @param frame
     * @param source
     */
    @Override
    public void update(ObservableFrame frame, Object source) {
        userInput.add(name.getText());
    }

    @Override
    public void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

        JLabel nameLabel = new JLabel("Name of Prescription:");
        JLabel numMedicineLabel = new JLabel("Number of medicine:");
        nameLabel.setSize(200, 40);
        name.setSize(200, 50);
        nameLabel.setLocation(100, 50);
        name.setLocation(100, 100);

        numMedicineLabel.setSize(200, 40);
        numMedicine.setSize(200, 50);
        numMedicineLabel.setLocation(100, 50);
        numMedicine.setLocation(100, 100);

        JButton login = new JButton("Create Medicines");
        login.setLocation(100, 150);
        login.setSize(100, 70);

        panel.add(name);
        panel.add(nameLabel);
        panel.add(numMedicineLabel);
        panel.add(numMedicine);
    }
}
