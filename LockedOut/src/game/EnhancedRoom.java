package game;

import java.util.HashMap;
import java.util.Map;
import edu.vt.cs5044.adventure.FormatUtil;
import edu.vt.cs5044.adventure.Room;

/**
 * 
 * EnhancedRoom class. Adds additional elements to the Room class.
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class EnhancedRoom extends Room implements ItemHolder {

    private Map<String, Item> inventory;
    private Map<String, Item> doorMap;
    private boolean dark;
    private boolean doorLocked;

    /**
     * 
     * Construct a new EnhancedRoom object.
     *
     * @param name the name of the room
     */
    public EnhancedRoom(String name) {
        super(name);
        inventory = new HashMap<>();
        doorMap = new HashMap<>();
        dark = false;
        doorLocked = false;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getAdditionalDescription() {
        if (inventory.isEmpty()) {
            return super.getAdditionalDescription();
        }
        return "You see " + FormatUtil.formatCollection(getItemNames(), "the ") + ".";
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Map<String, Item> getInventory() {
        return inventory;
    }

    /**
     * 
     * Method to determine if room is dark or not
     *
     * @return boolean value
     */
    public boolean isDark() {
        return dark;
    }

    /**
     * 
     * Method to set closest light on or off
     *
     * @param dark true or false
     */
    public void setDark(boolean dark) {
        this.dark = dark;
    }

    /**
     * Place a get door at specific direction of room.
     *
     * @param direction direction name
     * @return door
     */
    // door-map getter and setter
    public Item getDoor(String direction) {
        return doorMap.get(direction);
    }

    /**
     * Set door with its direction in the room.
     *
     * @param direction direction name
     * @param door door name
     */
    public void setDoor(String direction, Item door) {
        doorMap.put(direction, door);
    }

    /**
     * Check if door exists in a certain direction.
     *
     * @param dir room direction
     * @return true if door exists in the given direction, otherwise false
     */
    public boolean hasDoor(String dir) {
        return doorMap.containsKey(dir);
    }

    /**
     * Simple getter method.
     *
     * @return the locked value
     */
    public boolean isDoorLocked() {
        return doorLocked;
    }

    /**
     * Simple setter method.
     *
     * @param locked the value to set
     */
    public void setDoorLocked(boolean locked) {
        this.doorLocked = locked;
    }

}
