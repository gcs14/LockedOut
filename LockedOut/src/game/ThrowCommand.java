package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Player;

/**
 * 
 * ThrowCommand class used to throw items specified by secondWord.
 * 
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class ThrowCommand implements Command {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(Player player, String secondWord) {

        // if secondWord isn't available
        if (secondWord == null) {
            return EnhancedMessage.commandIncomplete() + "throw?";
        }
        if (secondWord.equals("screw-driver")) {
            return "Hey! You could've hit somebody with that! Here, take it back.";
        }
        if (secondWord.equals("light-bulb")) {
            return "You feel better now? Here, take it back.?";
        }
        if (secondWord.equals("spare-key")) {
            return "Now why the hell would you do that? Here, take it back.";
        }
        return "You can't throw that.";
    }

}
