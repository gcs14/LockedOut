package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * ExamineCommand Class. Used to examine items in rooms.
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class ExamineCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {

        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();
        Item item = room.getItem(secondWord);
//        player = (MyPlayer)

        // second word isn't available
        if (secondWord == null) {
            return EnhancedMessage.commandIncomplete() + "examine?";
        }

        // if item is in player inventory
        if (((MyPlayer) player).getInventory().containsKey(secondWord)) {
            return "It's old and a bit rusty, but it still works. "
                + "You might need to use this.";
        }

        // if room doesn't have specified item
        if (!room.hasItem(secondWord)) {
            return EnhancedMessage.objectNotInScope(secondWord);
        }

        // examine shoe-box
        if (secondWord.equals("shoe-box")) {
            if (!room.getInventory().get("shoe-box").isOpen()) {
                return item.getDescription() + " The shoe-box is closed.";
            }
            return item.getDescription() + " The shoe-box is empty.";
        }

        // examine wall-lantern
        if (secondWord.equals("wall-lantern")) {
            if (!room.getInventory().get("wall-lantern").isOpen()) {
                return EnhancedMessage.examineLanternBroken()
                    + " " + EnhancedMessage.lanternTip();
            }
            if (room.getInventory().get("wall-lantern").getContainedItem() == null) {
                return EnhancedMessage.emptyLantern(secondWord);
            }
            return EnhancedMessage.examineLanternFixed() + " Inside you see the light-bulb.";
        }

        // examine doors
        if (secondWord.equals("front-door")) {
            if (room.isDoorLocked()) {
                return item.getDescription() + "The front-door is locked.";
            }
            return item.getDescription();
        }
        if (secondWord.equals("side-door")) {
            if (room.isDoorLocked()) {
                return item.getDescription() + "The side-door is locked.";
            }
            return item.getDescription();
        }

        // examine empty container
        if (item.isContainer() && item.getContainedItem() == null) {
            return EnhancedMessage.examineEmptyContainer(secondWord);
        }

        // examine fake-rock
        if (secondWord.equals("fake-rock")) {
            if (room.isDark()) {
                return item.getDescription() + " " + EnhancedMessage.examineDark()
                    + " " + EnhancedMessage.examineClosedContainer(secondWord);
            }
            if (!room.getInventory().get("fake-rock").isOpen()) {
                return EnhancedMessage.seeClear(secondWord)
                    + " The " + EnhancedMessage.examineClosedContainer(secondWord);
            }
            return EnhancedMessage.seeClear(secondWord)
                + EnhancedMessage.examineEmptyContainer(secondWord);
        }

        return item.getDescription();
    }

}
