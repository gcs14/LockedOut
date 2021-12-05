package game;

/**
 * 
 * Item class. This class is constructed from information from ItemBuilder class.
 *
 * 
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class Item {

    private final String name;
    private String description;
    private boolean isContainer;
    private Item containedItem;
    private boolean lockable;
    private boolean locked;
    private boolean portable;
    private boolean openable;
    private boolean open;

    /**
     * 
     * Construct a new Item object.
     *
     * @param name of item
     * @param description of item
     * @param isContainer default false
     * @param containedItem default null
     * @param lockable default false
     * @param locked default false
     * @param portable default false
     * @param openable default false
     * @param open default false
     */
    public Item(String name, String description, boolean isContainer, Item containedItem,
        boolean lockable, boolean locked, boolean portable, boolean openable, boolean open) {
        this.name = name;
        this.description = description;
        this.isContainer = isContainer;
        this.containedItem = containedItem;
        this.lockable = lockable;
        this.locked = locked;
        this.portable = portable;
        this.openable = openable;
        this.open = open;
    }

    /**
     * Simple getter method.
     *
     * @return the name value
     */
    public String getName() {
        return name;
    }

    /**
     * Simple getter method.
     *
     * @return the description value
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Simple getter method.
     *
     * @return the lockable value
     */
    public boolean isLockable() {
        return lockable;
    }

    /**
     * Simple setter method.
     *
     * @param lockable the value to set
     */
    public void setLockable(boolean lockable) {
        this.lockable = lockable;
    }

    /**
     * Simple getter method.
     *
     * @return the locked value
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Simple setter method.
     *
     * @param locked the value to set
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Simple getter method.
     *
     * @return the portable value
     */
    public boolean isPortable() {
        return portable;
    }

    /**
     * Simple setter method.
     *
     * @param portable the value to set
     */
    public void setPortable(boolean portable) {
        this.portable = portable;
    }

    /**
     * Simple getter method.
     *
     * @return the openable value
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     * Simple setter method.
     *
     * @param openable the value to set
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     * Simple getter method.
     *
     * @return the isContainer value
     */
    public boolean isContainer() {
        return isContainer;
    }

    /**
     * Simple setter method.
     *
     * @param isContainer the value to set
     */
    public void setContainer(boolean isContainer) {
        this.isContainer = isContainer;
    }

    /**
     * Simple getter method.
     *
     * @return the containedItem value
     */
    public Item getContainedItem() {
        return containedItem;
    }

    /**
     * Simple setter method.
     *
     * @param containedItem the value to set
     */
    public void setContainedItem(Item containedItem) {
        this.containedItem = containedItem;
    }

    /**
     * Simple getter method.
     *
     * @return the open value
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Simple setter method.
     *
     * @param open the value to set
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

}
