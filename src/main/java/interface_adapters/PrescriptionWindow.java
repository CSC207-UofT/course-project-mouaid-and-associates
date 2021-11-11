package interface_adapters;

import java.util.List;
import java.util.Scanner;

public abstract class PrescriptionWindow extends Window{
    /**
     * Informal Note: This interface may be useful for graphics (later phases), but for now I couldn't think of any
     * benefits of this interface when interacting with the console - Eren
     */
    public PrescriptionWindow(Scanner scanner){
        super(scanner);
    }

    public abstract List<String[]> getUserPrescriptionInput();

    /**
     * Checks to see whether str can be converted into a double
     * @param str what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }

    }
}