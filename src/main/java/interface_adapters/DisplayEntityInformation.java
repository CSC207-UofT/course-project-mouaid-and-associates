package interface_adapters;

public interface DisplayEntityInformation {
    /** This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     */

    void displayInfo(String[] info);

}
