package entities;

import java.util.Dictionary;

public class Event {
    /**
     * Instance Attributes:
     * name: The name of the current event
     * description: The description of the current event.
     * time: The time this event takes place. Maps a day to an hour the event takes
     *        place during that day.
     *        e.g.
     *        {"Monday": 8} means the event is on Monday at 8:00.
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

    private Dictionary<String, Double> timeStamp;


    /**
     *
     * @param name          The name of the event
     * @param description   The description of the event.
     * @param timeStamp         The times the event takes place.
     */
    public Event(String name, String description, Dictionary<String, Double> timeStamp){
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
     * Sets the description of the event.
     * @param description  The description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the time an event takes place.
     * @return the time an event takes place.
     */
    public Dictionary<String, Double> getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time an event takes place.
     * @param times The time an event takes place.
     */
    public void setTimeStamp(Dictionary<String, Double> time) {
        this.timeStamp = time;
    }
}
