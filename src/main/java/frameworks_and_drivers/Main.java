package frameworks_and_drivers;

import interface_adapters.AppManagerFacade;
import interface_adapters.FrameObserver;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * The main method.
     */
    public static void main(String[] args) {
        AppManagerFacade appManagerFacade = new AppManagerFacade();
        ObservableFrame frame = new ObservableFrame();
        Scanner scanner = new Scanner(System.in);
        Map<String, Window> windows = new HashMap<>();
        UserDataAccess dataAccess = new UserDataAccess();
        String accountFile = "src/main/data/users.ser";

        // Create all the different windows.
        SelectTimesWindow selectTimesWindow = new SelectTimesWindow(scanner, frame);
        windows.put("Add Medicine Window", new AddMedicineWindow(scanner, frame, selectTimesWindow));
        windows.put("Add Prescription Window", new AddPrescriptionWindow(scanner, frame));
        windows.put("Remove Prescription Window", new RemovePrescriptionWindow(scanner, frame));
        windows.put("Create Account Window", new CreateAccountWindow(scanner, frame));
        windows.put("Login Window", new LoginWindow(scanner, frame));
        windows.put("Start Screen Window", new StartScreenWindow(scanner, frame));
        windows.put("TimeTable Window", new TimeTableWindow(scanner, frame));
        windows.put("View Account Window", new ViewAccountWindow(scanner, frame));
        windows.put("Remove Medicine Window", new RemoveMedicineWindow(scanner, frame));
        windows.put("Choose Medicine To Edit Window", new ChooseMedicineToEditWindow(scanner, frame));
        windows.put("Choose Prescription To Edit Window", new ChoosePrescriptionToEditWindow(scanner, frame));
        windows.put("Edit Medicine Window", new EditMedicineWindow(scanner, frame));
        windows.put("Edit Prescription Window", new EditPrescriptionWindow(scanner, frame));
        windows.put("Set Sleep Timings Window", new SetSleepTimingsWindow(scanner, frame));
        windows.put("Set Meal Timings Window", new SetMealTimingsWindow(scanner, frame));


        // Add the windows as observers for the frame.
        for (Window observer: windows.values()){
            frame.addObserver(observer);
        }
        frame.addObserver(selectTimesWindow);

        appManagerFacade.run(windows, dataAccess, accountFile);

        scanner.close();

    }

}
