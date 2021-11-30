package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
public class Event implements Comparable<Event>, Serializable {
    /**
     * A class containing a singular occurrence of an event that would appear on a schedule
     * Instance Attributes:
     * name: The name of the current event
     * description: The description of the current event.
     * timeStamp: The time this event takes place. Stored in a LocalDateTime
     *        e.g.
     *        LocalDateTime.parse("2021-12-03T10:15") means the event is on December 3, 2021, at 10:15am.
     *
     * Representation Invariants:
     *  - Each value in time is between 00:00 and 23:59, inclusive.
     *  - name is not an empty string
     *  - description is not an empty string.
     *
     */
    private String name;
    private String description;
    private LocalDateTime timeStamp;


    /**
     * @param name          The name of the event
     * @param description   The description of the event.
     * @param timeStamp         The times the event takes place.
     */
    public Event(String name, String description, LocalDateTime timeStamp){
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

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
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
        return timeStamp.getMonthValue() + "/"+ timeStamp.getDayOfMonth();
    }

    /**
     * Returns the hour of the day this event takes place.
     * @return  The hour this event takes place.
     */
    public String getTime(){
        return timeStamp.getHour() + ":" + timeStamp.getMinute();
    }

    /**
     * Returns the date the event takes place
     * @return Returns a string representing the date in the form YYYY-MM-DDTHH:MM:SS
     */
    public String getDate(){ return timeStamp.toString();}

    /**
     * Compares this Event with the specified Event for order.  Returns a
     * negative integer, zero, or a positive integer as this Event is less
     * than, equal to, or greater than the specified Event.
     *
     * Precondition:
     * - this.timeStamp.getDay().equals(o.getDay)
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
        return this.timeStamp.compareTo(o.timeStamp);
    }

    /**
     * Sets the description of the event.
     * @param description  The description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
