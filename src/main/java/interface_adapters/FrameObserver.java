package interface_adapters;
/**
 * DISCLAIMER: This class is NOT part of the Observer design pattern. Since both
 *             the frame and it's observers (the windows) need to update each other,
 *             the observer design pattern was not used, but the naming scheme stuck.
 *             The ideas are still used, but the relationship is not one way currently.
 */
public interface FrameObserver {
    /**
     * Notify the observer of a change and make it update accordingly.
     */
    void update(Object source);
}
