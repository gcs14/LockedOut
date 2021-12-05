package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * Drop Command Class Second word indicates what item to drop. If item is not in inventory, then
 * return appropriate message
 *
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class DropCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {

        MyPlayer myPlayer = (MyPlayer) player;
        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();

        Item item = myPlayer.getItem(secondWord);

        if (!myPlayer.getInventory().containsValue(item)) {
            return EnhancedMessage.itemNotInInventory(secondWord);
        }
        myPlayer.removeItem(secondWord);
        room.addItem(item);

        return EnhancedMessage.dropSuccess(secondWord);
    }

}
