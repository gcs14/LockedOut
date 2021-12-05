package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * OpenCommand class used to open containers and doors specified by secondWord.
 * 
 *
 * 
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class OpenCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {

        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();
        Item item = room.getItem(secondWord);

        Item lightBulb = new ItemBuilder()
            .setName("light-bulb")
            .setDescription(
                "This bulb is rated for high brightness and low power usage. "
                    + "You can definitely use this to fix the wall-lantern.")
            .setPortable(true)
            .build();

        Item spareKey = new ItemBuilder()
            .setName("spare-key")
            .setDescription("This is the spare-key!!!")
            .setPortable(true)
            .build();

        // if secondWord isn't available
        if (secondWord == null) {
            return EnhancedMessage.commandIncomplete() + "open?";
        }

        if (!room.getInventory().containsKey(secondWord)) {
            return EnhancedMessage.objectNotInScope(secondWord);
        }

        if (secondWord.equals("front-door") || secondWord.equals("side-door")) {
            return EnhancedMessage.openCant(secondWord);
        }

        // if container is already open
        if (item.isOpen()) {
            return EnhancedMessage.openAlready(secondWord);
        }

        // if room is dark
        if (room.isDark()) {
            return EnhancedMessage.openDark();
        }

        // opening containers
        if (secondWord.equals("shoe-box")) {
            item.setOpen(true);
            room.addItem(lightBulb);
            return EnhancedMessage.openSuccess(secondWord) + " " + EnhancedMessage.foundBulb();
        }
        if (secondWord.equals("fake-rock")) {
            item.setOpen(true);
            room.addItem(spareKey);
            return EnhancedMessage.openSuccess(secondWord) + " " + EnhancedMessage.foundKey();
        }
        return EnhancedMessage.openCant(secondWord);
    }

}
