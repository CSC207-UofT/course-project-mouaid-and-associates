package frameworks_and_drivers;

import interface_adapters.AppManager;
import interface_adapters.Window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * The main method.
     */
    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        Scanner scanner = new Scanner(System.in);
        Map<String, Window> windows = new HashMap<>();

        windows.put("Add Medicine Window", new AddMedicineWindow(scanner));
        windows.put("Edit Prescription Window", new EditPrescriptionWindow(scanner));
        windows.put("Add Prescription Window", new AddPrescriptionWindow(scanner));
/*        windows.put("Remove Prescription Window", new RemovePrescriptionWindow(scanner));*/
        windows.put("Create Account Window", new CreateAccountWindow(scanner));
        windows.put("Login Window", new LoginWindow(scanner));
        windows.put("Start Screen Window", new StartScreenWindow(scanner));
        windows.put("TimeTable Window", new TimeTableWindow(scanner));
        windows.put("View Account Window", new ViewAccountWindow(scanner));
        windows.put("Remove Medicine Window", new RemoveMedicineWindow(scanner));
        windows.put("Choose Medicine To Edit Window", new ChooseMedicineToEditWindow(scanner));
        windows.put("Edit Medicine Window", new EditMedicineWindow(scanner));
        windows.put("Set Sleep Timings Window", new SetSleepTimingsWindow(scanner));

        appManager.run(windows);

        scanner.close();

    }

}
