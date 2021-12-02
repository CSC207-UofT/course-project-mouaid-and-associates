package interface_adapters;
/**
 * An interface representing the observer interface from the Observer Design Pattern
 */
public interface FrameObserver {
    /**
     * Notify the observer of a change
     */
    void update(ObservableFrame frame, Object source);
}
