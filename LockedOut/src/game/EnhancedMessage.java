package game;

import edu.vt.cs5044.adventure.Message;

/**
 * 
 * Class for extra preset= messages
 *
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class EnhancedMessage extends Message {

    /**
     * 
     * command incomplete message
     *
     * @return message of status
     */
    public static String commandIncomplete() {
        return "What do you want to ";
    }

    /**
     * 
     * item not in inventory message
     *
     * @param objName name of object
     * @return message of status
     */
    public static String itemNotInInventory(String objName) {
        return "You are not carrying " + objName + ".";
    }

    /**
     * 
     * this door is locked
     *
     * @param string item
     * @return message of status
     */
    public static String doorLocked(String string) {
        return "The locked " + string + " is blocking you!";
    }

    /**
     * 
     * front-door unlocked message
     *
     * @return message of status
     */
    public static String frontDoorUnlocked() {
        return "You unlocked the front-door! You can finally go inside from here!";
    }

    /**
     * 
     * side-door unlocked message
     *
     * @return message of status
     */
    public static String sideDoorUnlocked() {
        return "You unlocked the side-door! You can finally go inside from here!";
    }

    /**
     * 
     * screw-driver used message
     *
     * 
     * @return message of status
     */
    public static String screwDriverUsed() {
        return "You can't see very well, but you manage to unscrew the wall-lantern cover! "
            + "You open the wall-lantern.";
    }

    /**
     * 
     * light bulb found message
     *
     * @return message of status
     */
    public static String foundBulb() {
        return "You found the light-bulb! The light-bulb is now in your garage.";
    }

    /**
     * 
     * screw driver used message
     *
     *
     * @return message of status
     */
    public static String lightBulbUsed() {
        return "You've fixed the wall-lantern! It's much brighter around here now.";
    }

    /**
     * 
     * spare key found message
     *
     * @return message of status
     */
    public static String foundKey() {
        return "You found the spare-key! The spare-key is now in the front yard.";
    }

    /**
     * 
     * drop success message
     *
     * @param objName name of object
     * @return message of status
     */
    public static String dropSuccess(String objName) {
        return "You drop the " + objName + ".";
    }

    /**
     * 
     * action can't be done message
     *
     * @return message of status
     */
    public static String actionCant() {
        return "That action cannot be done to this item.";
    }

    /**
     * 
     * message to inform you can't use specified item
     *
     * @return message of status
     */
    public static String notCarrying() {
        return "You can only use items that you are carrying.";
    }

    /**
     * 
     * no exit message
     *
     * @return message of status
     */
    public static String noExit() {
        return "There is no exit in that direction.";
    }

    /**
     * 
     * message to display tip for fixing lantern
     *
     * @return message of status
     */
    public static String lanternTip() {
        return "You always keep an extra in the garage. The wall-lantern is closed.";
    }

    /**
     * 
     * message to display lantern is open but empty
     *
     * @param objName name of object
     * @return message of status
     */
    public static String emptyLantern(String objName) {
        return "There is no light-bulb in the lantern. You always keep an extra in the garage."
            + " " + EnhancedMessage.examineEmptyContainer(objName);
    }

    /**
     * 
     * message that container is closed
     *
     * @param objName name of object
     * @return message of status
     */
    public static String containerClosed(String objName) {
        return objName + " is closed.";
    }

    /**
     * 
     * message that indicates you can see see item now
     *
     * @param objName name of object
     * @return message of status
     */
    public static String seeClear(String objName) {
        return "You can see the " + objName + " clearly now!";
    }
}
