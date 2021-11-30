package interface_adapters;

import application_business_rules.ManagementSystemFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppManagerHelpers {
    /**
     * A Class that has the helpers used by methods in AppManager
     */

    private ManagementSystemFacade managementSystemFacade;

    public AppManagerHelpers(ManagementSystemFacade managementSystemFacade){
        this.managementSystemFacade = managementSystemFacade;
    }
    public void addMedicineHelper(String[] data) {
        String name = data[0];
        String methodOfAdmin = data[1];
        String unitOfMeasurement = data[2];
        int amount;
        String extraInstruct = data[4];
        String wOrD = data[5]; // Stores frequency. Weekly/Daily
        String startDay = data[6];
        String startMonth = data[7];
        List<LocalDateTime> times = new ArrayList<>();

        // In case the user decided to not enter a proper value.
        try {
            amount = Integer.parseInt(data[3]);
        } catch (NumberFormatException e) {
            amount = -1;
        }

        formatTimes(data, wOrD, startDay, startMonth, times);

        //Done: call managementSystemFacade.addNewMedicine() and pass in this information.
        managementSystemFacade.addNewMedicine(name, amount, unitOfMeasurement, methodOfAdmin, extraInstruct, times);
    }

    /**
     * Formats the times returned by user input.
     * @param data      The user input
     * @param wOrD      A variable determining weekly OR daily.
     * @param startDay  The day the first time occurs.
     * @param times     The mapping on which we will save the formatted times.
     */
    public void formatTimes(String[] data, String wOrD, String startDay, String startMonth, List<LocalDateTime> times) {
        for(int i = 0; i < (data.length - 8); i++) {
            if (startDay.length() == 1){
                startDay = "0" + startDay;
            }
            if (wOrD.equals("weekly")) {
                times.add(LocalDateTime.parse("2021-" + startMonth + "-" + startDay + "T" +  data[i + 8]));
            } else {
                for (int j = 0; j <8; j++) {
                    startDay = Integer.toString(Integer.parseInt(startDay) + 1);
                    if (startDay.length() == 1){
                        startDay = "0" + startDay;
                    }
                    times.add(LocalDateTime.parse("2021-" + startMonth + "-" + startDay + "T" +  data[i + 8]));
                }
            }
        }
    }

    public String[] getFormattedList(String title, String[] list, int startIndex, int endIndex){
        // First, start with a list of the correct length
        String[] formattedList = new String[endIndex - startIndex + 1];
        formattedList[0] = title;

        for (int i = 1; i < formattedList.length; i++){
            formattedList[i] = " - " + list[i + startIndex - 1];
        }

        return formattedList;
    }
}
