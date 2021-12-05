package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.FormatUtil;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * Class to check the inventory of the player.
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class InventoryCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {
        MyPlayer myPlayer = (MyPlayer) player;

        if (myPlayer.isEmpty()) {
            return Message.inventoryEmpty();
        }

        return "You are carrying  " + FormatUtil.formatCollection(myPlayer.getItemNames(), "the ")
            + ".";
    }

}
