package interface_adapters;
/**
 * An interface representing an observer of the frame.
 */
public interface FrameObserver {
    /**
     * Notify the observer of a change
     */
    void update(Object source);
}
