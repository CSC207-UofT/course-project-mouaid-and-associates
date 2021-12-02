package interface_adapters;

import java.util.List;
import java.util.Scanner;

public abstract class PrescriptionWindow extends Window{
    /**
     * Informal Note: This interface may be useful for graphics (later phases), but for now I couldn't think of any
     * benefits of this interface when interacting with the console - Eren
     */
    public PrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
    }

    public abstract List<String[]> getUserPrescriptionInput(Window addMedicineWindow);
    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {

    }
}
