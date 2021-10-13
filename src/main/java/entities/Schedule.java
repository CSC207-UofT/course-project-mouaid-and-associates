package entities;

import java.util.List;

public class Schedule {
    /**
     * Instance Attributes:
     * events: The list of events corresponding to this schedule.
     *
     * Representation Invariants:
     * - 0 <= events.size()
     */
    private List<Event> events;

    public Schedule(List<Event> events){
        this.events = events;
    }

    /**
     * Gets the events in this schedule
     * @return  The events in this Schedule
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events in this schedule
     * @param events The events to be set to this schedule
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     *  Adds an event to this schedule.
     * @param event An event to be added to the schedule.
     * @return A boolean indicating if the item has been added.
     */
    public boolean addEvent(Event event){
        return this.events.add(event);
    }

    /**
     * Removes a specific event from the schedule.
     * @param event An event to be removed from the schedule.
     * @return A boolean indicating if the item has been removed.
     */
    public boolean removeEvent(Event event){
        return this.events.remove(event);
    }

}