package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * UseCommand class used to use items specified by secondWord.
 * 
 *
 * 
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class UseCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {
        MyPlayer myPlayer = (MyPlayer) player;
        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();

        // if secondWord is not available
        if (secondWord == null) {
            return EnhancedMessage.commandIncomplete() + "use?";
        }

        // if you aren't carrying the specified item
        if (!myPlayer.getInventory().containsKey(secondWord)) {
            return EnhancedMessage.notCarrying() + " You aren't carrying the " + secondWord + ".";
        }

        // using screw-driver
        if (secondWord.equals("screw-driver")
            && room.getInventory().containsKey("wall-lantern")) {
            if (room.getInventory().get("wall-lantern").isOpen()) {
                return EnhancedMessage.openAlready("wall-lantern");
            }
            room.getInventory().get("wall-lantern").setOpen(true);
            return EnhancedMessage.screwDriverUsed();
        }

        // using light-bulb
        if (myPlayer.getInventory().containsKey("light-bulb")) {
            if (room.getInventory().containsKey("wall-lantern")
                && !room.getInventory().get("wall-lantern").isOpen()) {
                return EnhancedMessage.actionCant() + " "
                    + EnhancedMessage.containerClosed("wall-lantern");
            }
            if (room.hasDoor("west")) {
                return "The light-bulb might be useful, but not here.";
            }
            room.getInventory().get("wall-lantern")
                .setContainedItem(myPlayer.getInventory().get("light-bulb"));
            myPlayer.getInventory().remove("light-bulb");
            room.setDark(false);

            return EnhancedMessage.lightBulbUsed();
        }

        // using spare-key
        if (room.isDoorLocked() && secondWord.equals("spare-key")) {
            room.setDoorLocked(false);
            if (room.hasDoor("north")) {
                return EnhancedMessage.frontDoorUnlocked();
            }
            return EnhancedMessage.sideDoorUnlocked();
        }
        if (!room.isDoorLocked()) {
            return EnhancedMessage.openAlready("front-door");
        }
        return EnhancedMessage.actionCant();
    }

}
