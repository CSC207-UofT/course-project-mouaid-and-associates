package entities;

import java.util.Dictionary;
import java.util.List;

public class Event {
    /**
     * Instance Attributes:
     * name: The name of the current event
     * description: The description of the current event.
     * times: The times this event takes place. Maps a day to a list of hours the events take
     *        place during that day.
     *        e.g.
     *        {"Monday": {8, 16, 23}} means the event is on Mondays at 8:00, 16:00 and 23:00.
     *
     * Representation Invariants:
     *  - Each value in times is between 0 and 24, inclusive.
     *  - Each key in times is an element of the set {"Monday", "Tuesday", "Wednesday", "Thursday",
     *                                                  "Friday", "Saturday", "Sunday"}
     *  - name is not an empty string
     *  - description is not an empty string.
     *
     */
    private String name;
    private String description;
    private Dictionary<String, List<Double>> times;


    /**
     *
     * @param name          The name of the event
     * @param description   The description of the event.
     * @param times         The times the event takes place.
     */
    public Event(String name, String description, Dictionary<String, List<Double>> times){
        this.name = name;
        this.description = description;
        this.times = times;
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
     * Gets the times an event takes place.
     * @return the times an event takes place.
     */
    public Dictionary<String, List<Double>> getTimes() {
        return times;
    }

    /**
     * Sets the times an event takes place.
     * @param times The times an event takes place.
     */
    public void setTimes(Dictionary<String, List<Double>> times) {
        this.times = times;
    }
}