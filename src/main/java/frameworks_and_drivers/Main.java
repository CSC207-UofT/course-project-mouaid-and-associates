package frameworks_and_drivers;

import interface_adapters.AppManager;
import interface_adapters.Window;

import java.util.HashMap;
import java.util.Map;

public class Main {
    /**
     * The main method.
     */
    public static void main(String[] args) {
        AppManager appManager = new AppManager();

        Map<String, Window> windows = new HashMap<>();

        windows.put("Add Medicine Window", new AddMedicineWindow());
        windows.put("Create Account Window", new CreateAccountWindow());
        windows.put("Login Window", new LoginWindow());
        windows.put("Start Screen Window", new StartScreenWindow());
        windows.put("TimeTable Window", new TimeTableWindow());
        windows.put("View Account Window", new ViewAccountWindow());

        appManager.run(windows);

    }

}
