package game;

import edu.vt.cs5044.adventure.GoCommand;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * Extension of the GoCommand. Adds shortcut direction commands.
 *
 *
 * @author Garrett Smith
 * @version Nov 15, 2021
 *
 */
public class EnhancedGoCommand extends GoCommand {

    private final String direction;

    /**
     * 
     * Construct a new EnhancedGoCommand object.
     *
     */
    public EnhancedGoCommand() {
        this.direction = "";
    }

    /**
     * 
     * Construct a new EnhancedGoCommand object.
     *
     * @param dir direction of the command
     */
    public EnhancedGoCommand(String dir) {
        direction = dir;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {
        String dir = direction;
        EnhancedRoom room = (EnhancedRoom) player.getCurrentRoom();

        if (secondWord == null) {
            if (dir.equals("n") || dir.equals("north")) {
                secondWord = "north";
            }
            if (dir.equals("s") || dir.equals("south")) {
                secondWord = "south";
            }
            if (dir.equals("w") || dir.equals("west")) {
                secondWord = "west";

            }
            if (dir.equals("e") || dir.equals("east")) {
                secondWord = "east";
            }
        }
        if (room.hasDoor(secondWord)) {
            Item dirDoor = (Item) room.getDoor(secondWord);
            if (room.isDoorLocked()) {
                return EnhancedMessage.doorLocked(dirDoor.getName());
            }
        }
        return super.execute(player, secondWord);
    }
}
