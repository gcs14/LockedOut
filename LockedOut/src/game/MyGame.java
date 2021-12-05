package game;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Game;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;
import edu.vt.cs5044.adventure.Room;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Extended Game class. Class that sets up and initiates the game.
 *
 * 
 * @author Garrett Smith 
 * @version Nov 15, 2021
 *
 */
public class MyGame extends Game {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Map<String, Command> setupCommands() {
        Map<String, Command> cmdMap = new HashMap<>();
        // Commands
        cmdMap.put("go", new EnhancedGoCommand());
        cmdMap.put("examine", new ExamineCommand());
        cmdMap.put("take", new TakeCommand());
        cmdMap.put("inventory", new InventoryCommand());
        cmdMap.put("open", new OpenCommand());
        cmdMap.put("use", new UseCommand());
        cmdMap.put("drop", new DropCommand());
        cmdMap.put("throw", new ThrowCommand());

        // Command shortcuts
        cmdMap.put("north", new EnhancedGoCommand("north"));
        cmdMap.put("n", new EnhancedGoCommand("n"));
        cmdMap.put("south", new EnhancedGoCommand("south"));
        cmdMap.put("s", new EnhancedGoCommand("s"));
        cmdMap.put("west", new EnhancedGoCommand("west"));
        cmdMap.put("w", new EnhancedGoCommand("w"));
        cmdMap.put("east", new EnhancedGoCommand("east"));
        cmdMap.put("e", new EnhancedGoCommand("e"));
        cmdMap.put("x", new ExamineCommand());
        cmdMap.put("i", new InventoryCommand());

        return cmdMap;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Room setupRooms() {
        EnhancedRoom driveWay = new EnhancedRoom(Message.roomDriveway());
        EnhancedRoom garage = new EnhancedRoom(Message.roomGarage());
        EnhancedRoom frontYard = new EnhancedRoom(Message.roomFrontYard());
        EnhancedRoom house = new EnhancedRoom(Message.roomHouse());

        Item screwDriver = new ItemBuilder()
            .setName("screw-driver")
            .setDescription(
                "It's old and a bit rusty, but it still works. You might need to use this.")
            .setPortable(true)
            .build();

        Item lightBulb = new ItemBuilder()
            .setName("light-bulb")
            .setDescription(
                "This bulb is rated for high brightness and low power usage. "
                    + "You can definitely use this to fix the wall-lantern.")
            .setPortable(true)
            .build();

        Item lightSwitch = new ItemBuilder()
            .setName("light-switch")
            .setDescription("This is a light-switch. Flip it on to see in the garage.")
            .build();

        Item spareKey = new ItemBuilder()
            .setName("spare-key")
            .setDescription(
                "This is the spare-key!!!")
            .setPortable(true)
            .build();

        Item shoeBox = new ItemBuilder()
            .setName("shoe-box")
            .setDescription("This box is so old it's almost falling apart.")
            .setContainer(true)
            .setContainedItem(lightBulb)
            .setOpenable(true)
            .setOpen(false)
            .setPortable(false)
            .build();

        Item wallLantern = new ItemBuilder()
            .setName("wall-lantern")
            .setDescription("This is an empty wall-lantern. It needs a light-bulb to turn it on.")
            .setContainer(true)
            .setLockable(true)
            .setOpenable(true)
            .build();

        Item fakeRock = new ItemBuilder()
            .setName("fake-rock")
            .setDescription("This looks just like one of the many real rocks around here.")
            .setContainer(true)
            .setContainedItem(spareKey)
            .setOpenable(true)
            .setOpen(false)
            .build();

        Item frontDoor = new ItemBuilder()
            .setName("front-door")
            .setDescription(
                "This is the main entry into your house, "
                    + "but you'll need to find your spare-key to get in!")
            .setLockable(true)
            .setLocked(true)
            .setOpenable(true)
            .build();

        Item sideDoor = new ItemBuilder()
            .setName("side-door")
            .setDescription(
                "This is the way into your house from the garage. "
                    + "It uses the same key as the front-door! The side-door is locked.")
            .setLockable(true)
            .setLocked(true)
            .setOpenable(true)
            .build();

        driveWay.addExit("west", frontYard);
        driveWay.addExit("north", garage);

        frontYard.addExit("east", driveWay);
        frontYard.addExit("north", house);
        frontYard.getInventory().put("wall-lantern", wallLantern);
        frontYard.getInventory().put("fake-rock", fakeRock);
        frontYard.getInventory().put("front-door", frontDoor);
        frontYard.setDoor("north", frontDoor);
        frontYard.setDoorLocked(true);
        frontYard.setDark(true);

        house.addExit("east", garage);
        house.addExit("south", frontYard);
        house.getInventory().put("light-switch", lightSwitch);

        garage.addExit("west", house);
        garage.addExit("south", driveWay);
        garage.getInventory().put("screw-driver", screwDriver);
        garage.getInventory().put("shoe-box", shoeBox);
        garage.getInventory().put("side-door", sideDoor);
        garage.setDoor("west", sideDoor);
        garage.setDoorLocked(true);

        return driveWay;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    // extend
    public Player setupPlayer() {
        return new MyPlayer();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getWelcomeMessage() {
        return "You're locked out of the house! Your goal is to get back inside, if you can...";
    }

    /**
     * 
     * Method to play the game
     *
     * @param args stream
     */
    public static void main(String[] args) {
        Game game = new MyGame();
        game.initialize();
        game.play(args);
    }

}
