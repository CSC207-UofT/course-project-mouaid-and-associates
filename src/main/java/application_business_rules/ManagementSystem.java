package application_business_rules;
import Entities.User;

import java.util.HashMap;
import java.util.Map;

public class ManagementSystem {
    Map<String, User> usernameUser = new HashMap<String, User>();
    UserManager userManager;
    ScheduleManager scheduleManager;
    AlertSystem alerts;

    public ManagementSystem(UserManager umanager, ScheduleManager smanager, AlertSystem aSystem){
        userManager = umanager;
        scheduleManager = smanager;
        alerts = aSystem;
    }
    public void addUser(String name,String username) {
        User newUser = userManager.addNewUser(name, username);
        usernameUser.put(newUser.getName(), newUser);
    }
}
