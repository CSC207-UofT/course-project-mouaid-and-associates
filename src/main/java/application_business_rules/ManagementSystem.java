package application_business_rules;

import java.util.*;

import entities.Medicine;
import entities.Schedule;
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

    public ManagementSystem(UserManager userManager, ScheduleManager scheduleManager){
        this.userManager = userManager;
        this.scheduleManager = scheduleManager;
    }

    /**
     * Creates a new ManagementSystem instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystem(){
        this.userManager = new UserManager();
        this.scheduleManager = new ScheduleManager();
    }

    /**
     * Creates a new user instance and stores it in the userDatabase
     * @param name The name of the user
     * @param username the username that the user will use to login
     */
    public void createNewUser(String name, String username){
        this.userDatabase.put(username, this.userManager.addNewUser(name, username));
    }

    // TODO: findUser is not required for Phase 0. Needs to be discussed further.

    /**
     * Gets the info of the user using the username that the user uses
     * @param username the username the user uses
     * @return returns a list that contains the user's username, name and list of medicines
     */
    public List<Object> getUserInfo(String username){
        User user = this.userDatabase.get(username);
        List<Object> info = new ArrayList <Object>();
        info.add(user.getName());
        info.add(username);
        info.add(user.getMedicineList());
        return info;
    }

    /**
     * Gets the info of the user using the username that the user uses
     * @return returns a list that contains the user's username, name and list of medicines (names only).
     */
    public List<String> getUserInfo(){
        List<String> userInfo = new ArrayList<>();
        userInfo.add(userManager.getName());
        userInfo.add(userManager.getUserName());

        // Get specifically the names of the medicine.
        userInfo.addAll(List.copyOf(userManager.getMedicines().keySet()));

        return userInfo;
    }

    /**
     * Uses the userManager to get a list of medicines and merges it with the master schedule
     * @return the compiled schedule.
     */
    public Schedule makeSchedule(){
        HashMap<String, Medicine> medicinesDict = userManager.getMedicineUser();
        List<Medicine> medicineList = new ArrayList<>(medicinesDict.values());
        List<Schedule> scheduleList = new ArrayList<>();
        for (Medicine meds: medicineList){
            scheduleList.add(meds.getMyMedicineSchedule());
        }
        return scheduleManager.compileSchedule(scheduleList);
    }

    //TODO add a method called addNewMedicine. Use this to call UserManager.createMedicine()



}
