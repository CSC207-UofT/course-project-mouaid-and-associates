package entities;

import java.util.Map;

public class Event implements Comparable<Event>{
    /**
     * A class containing a singular occurrence of an event that would appear on a schedule
     * Instance Attributes:
     * name: The name of the current event
     * description: The description of the current event.
     * time: The time this event takes place. Maps a day to an hour the event takes
     *        place during that day.
     *        e.g.
     *        {"Monday": 8.0} means the event is on Monday at 8:00.
     *
     * Representation Invariants:
     *  - Each value in time is between 0 and 24, inclusive.
     *  - Each key in time is an element of the set {"Monday", "Tuesday", "Wednesday", "Thursday",
     *                                                  "Friday", "Saturday", "Sunday"}
     *  - name is not an empty string
     *  - description is not an empty string.
     *
     */
    private String name;
    private String description;

    private Map<String, Double> timeStamp;


    /**
     * @param name          The name of the event
     * @param description   The description of the event.
     * @param timeStamp         The times the event takes place.
     */
    public Event(String name, String description, Map<String, Double> timeStamp){
        this.name = name;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the name of the event.
     * @return  The name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the event.
     * @param name  The name of the event
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the event.
     * @return  The description of the event
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns the specific day this event takes place.
     * @return  The day this event takes place.
     */
    public String getDay(){
        return (String) timeStamp.keySet().toArray()[0];
    }

    /**
     * Returns the hour of the day this event takes place.
     * @return  The hour this event takes place.
     */
    public Double getHour(){
        return (Double) timeStamp.values().toArray()[0];
    }

    /**
     * Compares this Event with the specified Event for order.  Returns a
     * negative integer, zero, or a positive integer as this Event is less
     * than, equal to, or greater than the specified Event.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Event o) {
        return this.getHour().compareTo(o.getHour());
    }

    /**
     * Changes the format of the hour into proper hour attribute.
     * @return  The hour in normal time format.
     */
    public String decimalToHourFormat(){
        double time = this.getHour();
        int min = (int) ((((time * 100) % 100) / 100.0) * 60);
        int hour = (int) (((time * 100) - min) / 100.0);

        return (hour + ":" + min);
    }

    //    /**
    //     * Sets the description of the event.
    //     * @param description  The description of the event
    //     */
    //    public void setDescription(String description) {
    //        this.description = description;
    //    }

    //    /**
    //     * Sets the time an event takes place.
    //     * @param day The time an event takes place.
    //     * @param hour The hour this event takes place.
    //     */
    //    public void setTimeStamp(String day, Double hour) {
    //        this.timeStamp = new HashMap<>();
    //        this.timeStamp.put(day, hour);
    //    }

}
