package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * TakeCommand class used to take items specified by secondWord.
 * 
 *
 * 
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class TakeCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {

        MyPlayer myPlayer = (MyPlayer) player;
        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();

        Item item = room.getItem(secondWord);

        // if secondWord isn't available
        if (secondWord == null) {
            return EnhancedMessage.commandIncomplete() + "take?";
        }

        // if specified item has already been taken
        if (myPlayer.getInventory().containsKey(secondWord)) {
            return EnhancedMessage.takeAlready(secondWord);
        }

        // if room doesn't have specified item
        if (!room.hasItem(secondWord)) {
            return EnhancedMessage.objectNotInScope(secondWord);
        }

        // if specified item isn't portable
        if (!item.isPortable()) {
            return EnhancedMessage.takeCant(secondWord);
        }

        // taking specified item out of it's container
        if (secondWord.equals("light-bulb")) {
            room.getInventory().get("shoe-box").setContainedItem(null);
        }
        if (secondWord.equals("spare-key")) {
            room.getInventory().get("fake-rock").setContainedItem(null);
        }

        // removing item from room
        room.getInventory().remove(secondWord);

        // adding item into player's inventory
        myPlayer.getInventory().put(secondWord, item);
        return EnhancedMessage.takeSuccess(secondWord);
    }
}
