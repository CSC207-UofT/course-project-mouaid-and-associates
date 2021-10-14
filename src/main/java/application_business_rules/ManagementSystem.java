package application_business_rules;

import java.util.*;

import entities.User;

import javax.lang.model.type.NullType;

public class ManagementSystem {
    /**
     * The main manager class that manages all the other manager classes
     * Instance Attributes:
     * UserDatabase: Stores a map of usernames to User entities
     * UserManager: Stores an instance of UserManager class
     * ScheduleManager: Stores an instance of ScheduleManager
     * ScheduleCompiler: Stores an instance ScheduleCompiler
     */
    private HashMap<String, User> userDatabase;
    private UserManager userManager;
    private ScheduleManager scheduleManager;
    private ScheduleCompiler scheduleCompiler;

    public ManagementSystem(UserManager userManager, ScheduleManager scheduleManager,
                            ScheduleCompiler scheduleCompiler){
        this.userManager = userManager;
        this.scheduleManager = scheduleManager;
        this.scheduleCompiler = scheduleCompiler;
    }

    /**
     * Creates a new user instance and stores it in the userDatabase
     * @param name The name of the user
     * @param username the username that the user will use to login
     */
    public void createNewUser(String name, String username){
        this.userDatabase.put(username, this.userManager.addNewUser(name, username));
    }

    /**
     * Finds the user entity using the username
     * @param username the username that the user uses
     * @return returns a User object
     */
    public Object findUser(String username){
        return this.userDatabase.get(username);
    }

    /**
     * Gets the info of the user using the username that the user uses
     * @param username the username the user uses
     * @return returns a list that contains the user's username, name and list of medicines
     */
    public List getUserInfo(String username){
        User user = this.userDatabase.get(username);
        List<Object> info = new ArrayList <Object>();
        info.add(user.getName());
        info.add(username);
        info.add(user.getMedicineList());
        return info;
    }
}
