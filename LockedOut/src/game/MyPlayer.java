package game;

import java.util.HashMap;
import java.util.Map;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * Extension of Player class. Implements ItemHolder class so Player can hold and inventory.
 *
 * 
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class MyPlayer extends Player implements ItemHolder {

    private Map<String, Item> inventory;

    /**
     * 
     * Construct a new MyPlayer object.
     *
     */
    public MyPlayer() {
        super();
        inventory = new HashMap<>();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Map<String, Item> getInventory() {
        return inventory;
    }
}
