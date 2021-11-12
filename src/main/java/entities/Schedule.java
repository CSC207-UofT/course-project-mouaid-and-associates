package entities;
import java.util.*;
public class  Schedule {
    /**
     * The Schedule that contains all the events.
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
     *  Adds a list of events to this schedule.
     * @param events List of events to be added to the schedule.
     * @return A boolean indicating if the item has been added.
     */
    public boolean addEvents(List<Event> events){
        return this.events.addAll(events);
    }

    /**
     * Removes a specific event from the schedule.
     * @param event An event to be removed from the schedule.
     * @return A boolean indicating if the item has been removed.
     */
    public boolean removeEvent(Event event){
        return this.events.remove(event);
    }

    /**
     *  A toString method that returns a string representation of the schedule. Formatted to
     *  look nice.
     *
     * @return  A string representation of the Schedule.
     */
    @Override
    public String toString() {
        Map<String, List<Event>> sortedEvents = sortEvents();
        StringBuilder scheduleRep = new StringBuilder();
        List<String> days = getOrderedEventDays(events);
        for (String day: days){
            scheduleRep.append(day).append(": \n");
            for (Event event: sortedEvents.get(day)){
                String eventDescription = new String(event.getDescription() + " \n");
                String eventHour = event.getTime();
                // Add the strings to the string builder.
                scheduleRep.append(eventHour);
                scheduleRep.append(" - ").append(eventDescription);

            }
        }

        return scheduleRep.toString();
    }

    public List<String> getOrderedEventDays(List<Event> events){
        Collections.sort(events);
        ArrayList<String> daysSoFar = new ArrayList<>();
        for (Event event : events){
            if (!daysSoFar.contains(event.getDay())){
                daysSoFar.add(event.getDay());
            }
        }
        return daysSoFar;
    }
    /**
     *  This method organizes the events so that events occurring in the same
     *  day are grouped together. And events occurring on the same day are also
     *  organized according to what time they occur. Earliest to latest.
     *
     * @return A map mapping a day to the events occurring in that day, with each
     *         associated event list being sorted according to time.
     */
    public Map<String, List<Event>> sortEvents(){
        // Sort the events by day.
        Map<String, List<Event>> newDictOfEvents = sortEventsByDay();

        // Sort the events by hour:
        for (String day: newDictOfEvents.keySet()){
            // get the list of events for that day.
            List<Event> eventList = newDictOfEvents.get(day);

            //sort the list.
            Collections.sort(eventList);
        }

        return newDictOfEvents;
    }

    /**
     * Sorts the events in the events attribute into a dictionary mapping a
     * day to events taking place on that day.
     *
     * @return The dictionary made from organizing the events by day.
     */
    private Map<String, List<Event>> sortEventsByDay() {
        // Start and accumulator collection. This is a dictionary mapping
        // days to events occurring on that day.
        Map<String, List<Event>> newDictOfEvents = new HashMap<>();
        // iterate through the list of events:
        for(Event event : this.events){
            // First get the Event's day.
            String day = event.getDay();
            // Check if the key already exists in the mapping
            if (newDictOfEvents.containsKey(day)){
                // if it exists, add to it.
                newDictOfEvents.get(day).add(event);
            } else {
                // if it doesn't, make a new mapping.
                List<Event> eventList = new ArrayList<>();
                eventList.add(event);
                newDictOfEvents.put(day, eventList);
            }
        }

        return newDictOfEvents;
    }


}
