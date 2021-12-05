package game;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * Class to hold items. Puts items into an inventory.
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public interface ItemHolder {

    /**
     * 
     * Method to create inventory map
     *
     * @return map
     */
    public Map<String, Item> getInventory();

    /**
     * 
     * Method to add item to inventory map
     *
     * @param item to be added
     */
    public default void addItem(Item item) {
        getInventory().put(item.getName(), item);
    }

    /**
     * 
     * Method to get the names of items in inventory
     *
     * @return names of items
     */
    public default Collection<String> getItemNames() {
        return getInventory().keySet();
    }

    /**
     * 
     * Method to determine if inventory contains specified item
     *
     * @param name of item
     * @return boolean value
     */
    public default boolean hasItem(String name) {
        return getInventory().containsKey(name);
    }

    /**
     * 
     * Method to get an item from inventory by name
     *
     * @param name of item
     * @return Item
     */
    public default Item getItem(String name) {
        return getInventory().get(name);
    }

    /**
     * 
     * Method to remove item from inventory
     *
     * @param name of item
     */
    public default void removeItem(String name) {
        getInventory().remove(name);
    }

    /**
     * 
     * Method to determine if inventory is empty of not
     *
     * @return boolean value
     */
    public default boolean isEmpty() {
        return getInventory().isEmpty();
    }
}
