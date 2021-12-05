package game;

/**
 * 
 * Item builder class used a more efficient way to build items
 *
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class ItemBuilder {
    private String name;
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
     * Construct a new ItemBuilder object.
     *
     */
    public ItemBuilder() {
        name = null;
        description = null;
        isContainer = false;
        containedItem = null;
        lockable = false;
        portable = false;
        openable = false;
        open = false;
    }

    /**
     * 
     * build method to craft an item
     *
     * @return new item
     */
    public Item build() {
        return new Item(name, description, isContainer, containedItem, lockable, locked, portable,
            openable, open);
    }

    /**
     * Simple setter method.
     *
     * @param name the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param description the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param isContainer the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setContainer(boolean isContainer) {
        this.isContainer = isContainer;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param containedItem the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setContainedItem(Item containedItem) {
        this.containedItem = containedItem;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param lockable the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setLockable(boolean lockable) {
        this.lockable = lockable;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param portable the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setPortable(boolean portable) {
        this.portable = portable;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param openable the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setOpenable(boolean openable) {
        this.openable = openable;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param locked the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * Simple setter method.
     *
     * @param open the value to set
     * @return item builder with updated info
     */
    public ItemBuilder setOpen(boolean open) {
        this.open = open;
        return this;
    }
}
