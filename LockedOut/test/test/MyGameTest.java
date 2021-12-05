package test;


import edu.vt.cs5044.adventure.Room;
import game.EnhancedMessage;
import game.EnhancedRoom;
import game.MyGame;
import game.MyPlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class MyGameTest {

    private MyGame game;
    private EnhancedRoom room;

    @Before
    public void setUp() {
        game = new MyGame();
        game.initialize();
    }

    @Test
    public void testInitialRoom() {
        assertEquals(EnhancedMessage.roomDriveway(), getRoomName());

    }

    @Test
    public void testSetupPlayer() {
        assertEquals(game.getPlayer(), getPlayer());
    }

    @Test
    public void testCcwTour() {

        String message = processInputList("go north", "go west", "go south", "go east");

        assertEquals(EnhancedMessage.noExit(), message);
        assertEquals("", getRoomAdditional());
        assertTrue(game.getPlayer().getCurrentRoom().hasExit("west"));
    }

    @Test
    public void testCwTour() {

        String message = processInputList("go west", "go north", "go east", "go south");

        assertEquals(EnhancedMessage.noExit(), message);
        assertEquals("", getRoomAdditional());
    }

    @Test
    public void testDirectionShortcuts() {

        String message = processInputList("north", "south", "west", "east", "n", "s", "w", "e");

        assertEquals("You move east.", message);
        assertEquals(EnhancedMessage.roomDriveway(), getRoomName());
        assertEquals("", getRoomAdditional());
    }

    @Test
    public void testInventoryEmpty() {
        String message = processInputList("inventory");
        assertEquals(EnhancedMessage.inventoryEmpty(), message);
        assertFalse(((MyPlayer) game.getPlayer()).getInventory().containsKey("screw-driver"));
    }

    @Test
    public void testOpenNotInScope() {
        String message = processInputList("open fake-rock");
        assertEquals(EnhancedMessage.objectNotInScope("fake-rock"), message);
    }

    @Test
    public void testOpenFrontDoorFail() {
        String message = processInputList("w", "open front-door");
        assertEquals(EnhancedMessage.openCant("front-door"), message);
    }

    @Test
    public void testExamineScrewdriver() {

        String message = processInputList("go north", "examine screw-driver");

        assertEquals("It's old and a bit rusty, but it still works. You might need to use this.",
            message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(true, getRoomAdditional().contains("screw-driver"));
    }

    @Test
    public void testTakeScrewdriver() {

        testExamineScrewdriver();

        String message = processInputList("take screw-driver");

        assertEquals(EnhancedMessage.takeSuccess("screw-driver"), message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(false, getRoomAdditional().contains("screw-driver"));
    }

    @Test
    public void testExamineScrewdriverInventory() {

        testTakeScrewdriver();

        String message = processInputList("x screw-driver");

        assertEquals("It's old and a bit rusty, but it still works. You might need to use this.",
            message);
    }

    @Test
    public void testTakeScrewdriverAgain() {

        testTakeScrewdriver();

        String message = processInputList("take screw-driver");

        assertEquals(EnhancedMessage.takeAlready("screw-driver"), message);
    }

    @Test
    public void testExamineShoeBox() {

        String message = processInputList("go north", "examine shoe-box");

        assertEquals("This box is so old it's almost falling apart. The shoe-box is closed.",
            message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(true, getRoomAdditional().contains("shoe-box"));
    }

    @Test
    public void testTakeShoeBox() {
        testExamineShoeBox();

        String message = processInputList("take shoe-box");

        assertEquals("You can't take the shoe-box.", message);
        assertEquals(true, getRoomAdditional().contains("shoe-box"));
    }

    @Test
    public void testOpenShoeBox() {

        testExamineShoeBox();

        String message = processInputList("open shoe-box");

        assertEquals(
            "You open the shoe-box. You found the light-bulb! The light-bulb is now in your garage.",
            message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(true, getRoomAdditional().contains("shoe-box"));
        assertEquals(true, getRoomAdditional().contains("light-bulb"));
    }

    @Test
    public void testOpenShoeBoxExamine() {

        testOpenShoeBox();

        String message = processInputList("examine shoe-box");

        assertEquals("This box is so old it's almost falling apart. The shoe-box is empty.",
            message);

    }

    @Test
    public void testExamineLightBulb() {

        testOpenShoeBox();

        String message = processInputList("examine light-bulb");

        assertEquals(
            "This bulb is rated for high brightness and low power usage. You can definitely use this to fix the wall-lantern.",
            message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(true, getRoomAdditional().contains("light-bulb"));
    }

    @Test
    public void testTakeLightBulb() {
        testExamineLightBulb();

        String message = processInputList("take light-bulb");

        assertEquals(EnhancedMessage.takeSuccess("light-bulb"), message);
        assertEquals(EnhancedMessage.roomGarage(), getRoomName());
        assertEquals(false, getRoomAdditional().contains("light-bulb"));
    }

    @Test
    public void testExamineEmptyShoeBox() {
        testTakeLightBulb();
        String message = processInputList("examine shoe-box");
        assertEquals("This box is so old it's almost falling apart. The shoe-box is empty.",
            message);
    }

    @Test
    public void testInventoryInDriveway() {
        testTakeScrewdriver();
        testTakeLightBulb();

        String message = processInputList("go south", "inventory");

        assertEquals(true, message.contains("screw-driver"));
        assertEquals(true, message.contains("light-bulb"));
        assertEquals(EnhancedMessage.roomDriveway(), getRoomName());
        assertEquals(false, getRoomAdditional().contains("screw-driver"));
        assertEquals(false, getRoomAdditional().contains("light-bulb"));
    }

    @Test
    public void testExamineBrokenWallLantern() {

        String message = processInputList("w", "examine wall-lantern");
        assertEquals(EnhancedMessage.examineLanternBroken() + " " + EnhancedMessage.lanternTip(),
            message);
    }

    @Test
    public void testOpenWallLantern() {
        testTakeScrewdriver();

        String message = processInputList("s", "w", "use screw-driver");
        assertEquals(EnhancedMessage.screwDriverUsed(), message);
    }

    @Test
    public void testExamineOpenedWallLantern() {
        testOpenWallLantern();

        String message = processInputList("x wall-lantern");
        assertEquals(EnhancedMessage.emptyLantern("wall-lantern"), message);
    }

    @Test
    public void testWallLanternUseBulb() {
        testTakeScrewdriver();
        testTakeLightBulb();

        String message = processInputList("s", "w", "use screw-driver", "use light-bulb");
        assertEquals(EnhancedMessage.lightBulbUsed(), message);
        assertEquals(false, getRoomAdditional().contains("light-bulb"));
    }

    @Test
    public void testClosedLanternUseBulb() {
        testTakeScrewdriver();
        testTakeLightBulb();

        String message = processInputList("s", "w", "use light-bulb");
        assertEquals(EnhancedMessage.actionCant() + " "
            + EnhancedMessage.containerClosed("wall-lantern"), message);
    }

    @Test
    public void testClosedLanternUseBulbExamineRock() {
        testClosedLanternUseBulb();
        room = (EnhancedRoom) getPlayer().getCurrentRoom();

        String message = processInputList("x fake-rock");
        assertEquals(room.getInventory().get("fake-rock").getDescription()
            + " " + EnhancedMessage.examineDark()
            + " The fake-rock is closed.", message);
    }

    @Test
    public void testExamineFixedLantern() {
        testWallLanternUseBulb();

        String message = processInputList("x wall-lantern");
        assertEquals(EnhancedMessage.examineLanternFixed() + " Inside you see the light-bulb.",
            message);
    }

    @Test
    public void testExamineRockAfterFixedLantern() {
        testWallLanternUseBulb();

        String message = processInputList("x fake-rock");
        assertEquals(EnhancedMessage.seeClear("fake-rock")
            + " The " + EnhancedMessage.examineClosedContainer("fake-rock"),
            message);
    }

    @Test
    public void testOpenRockAfterFixedLantern() {
        testWallLanternUseBulb();

        String message = processInputList("open fake-rock");
        assertEquals(EnhancedMessage.openSuccess("fake-rock")
            + " " + EnhancedMessage.foundKey(),
            message);
    }

    @Test
    public void testOpenRockUseKey() {
        testWallLanternUseBulb();

        String message = processInputList("use spare-key");
        assertEquals(EnhancedMessage.notCarrying() + " You aren't carrying the spare-key.",
            message);
    }

    @Test
    public void testExamineRockAfterOpenRockAfterFixedLantern() {
        testOpenRockAfterFixedLantern();

        String message = processInputList("x fake-rock");
        assertEquals(EnhancedMessage.seeClear("fake-rock")
            + EnhancedMessage.examineEmptyContainer("fake-rock"),
            message);
    }

    @Test
    public void testExamineFakeRockNoLight() {

        String message = processInputList("w", "x fake-rock");
        assertEquals(
            "This looks just like one of the many real rocks around here. It's too dark around here to examine anything. The fake-rock is closed.",
            message);
    }

    @Test
    public void testExamineFakeRockWithLight() {
        testWallLanternUseBulb();

        String message = processInputList("x fake-rock");
        assertEquals(EnhancedMessage.seeClear("fake-rock")
            + " The " + EnhancedMessage.examineClosedContainer("fake-rock"),
            message);
    }

    @Test
    public void testOpenFakeRockNoLight() {
        String message = processInputList("w", "open fake-rock");
        assertEquals(EnhancedMessage.openDark(), message);
    }

    @Test
    public void testOpenFakeRock() {
        testWallLanternUseBulb();
        String message = processInputList("w", "open fake-rock");
        assertEquals(EnhancedMessage.openSuccess("fake-rock") + " " + EnhancedMessage.foundKey(),
            message);
    }

    @Test
    public void testUseSpareKeyWithTakingIt() {
        testOpenFakeRock();
        String message = processInputList("use spare-key");
        assertEquals(EnhancedMessage.notCarrying() + " You aren't carrying the spare-key.",
            message);
    }

    @Test
    public void testExamineOpenedFakeRock() {
        testOpenFakeRock();
        String message = processInputList("x fake-rock");
        assertEquals(EnhancedMessage.seeClear("fake-rock")
            + EnhancedMessage.examineEmptyContainer("fake-rock"),
            message);
    }

    @Test
    public void testTakeFakeRock() {
        testWallLanternUseBulb();

        String message = processInputList("take fake-rock");

        assertEquals(EnhancedMessage.takeCant("fake-rock"), message);
        assertEquals(true, getRoomAdditional().contains("fake-rock"));
    }

    @Test
    public void testTakeSpareKey() {
        testOpenFakeRock();

        String message = processInputList("examine spare-key");
        assertEquals("This is the spare-key!!!", message);

        message = processInputList("take spare-key");
        assertEquals(EnhancedMessage.takeSuccess("spare-key"), message);
    }

    @Test
    public void testFailedTakeSpareKey() {
        testExamineFixedLantern();

        String message = processInputList("take spare-key");
        assertEquals(EnhancedMessage.objectNotInScope("spare-key"), message);
    }

    @Test
    public void testOpenFrontDoor() {
        testTakeSpareKey();
        String message = processInputList("use spare-key");

        assertEquals(EnhancedMessage.frontDoorUnlocked(), message);

        message = processInputList("go north");
        assertEquals(EnhancedMessage.roomHouse(), getRoomName());
    }

    @Test
    public void testUseKeyAgain() {
        testTakeSpareKey();
        String message = processInputList("use spare-key");
        message = processInputList("use spare-key");

        assertEquals(EnhancedMessage.openAlready("front-door"), message);
    }

    @Test
    public void testOpenSideDoor() {
        testTakeSpareKey();
        String message = processInputList("e", "n", "use spare-key");

        assertEquals(EnhancedMessage.sideDoorUnlocked(), message);
    }

    @Test
    public void testOpenDoorNoKey() {
        testDropSpareKey();

        String message = processInputList("use spare-key");

        assertEquals(EnhancedMessage.notCarrying() + " You aren't carrying the spare-key.",
            message);
    }

    @Test
    public void testDropSpareKey() {
        testTakeSpareKey();
        String message = processInputList("drop spare-key");

        assertEquals(EnhancedMessage.dropSuccess("spare-key"), message);
    }

    @Test
    public void testUseSpareKeyFail() {
        testWallLanternUseBulb();
        String message = processInputList("use spare-key");
        assertEquals(EnhancedMessage.notCarrying() + " You aren't carrying the spare-key.",
            message);

        room = (EnhancedRoom) getPlayer().getCurrentRoom();
        message = processInputList("open fake-rock", "take spare-key", "use spare-key");
        assertEquals(EnhancedMessage.frontDoorUnlocked(), message);
    }

    @Test
    public void testUseSpareKeyFail2() {
        String message = processInputList("go west", "use spare-key");
        assertEquals(EnhancedMessage.notCarrying() + " You aren't carrying the spare-key.",
            message);

        room = (EnhancedRoom) getPlayer().getCurrentRoom();
        message = processInputList("go north");
        assertEquals(EnhancedMessage.doorLocked("front-door"), message);
    }

    @Test
    public void testDropFail() {

        String message = processInputList("drop spare-key");
        assertEquals(EnhancedMessage.itemNotInInventory("spare-key"), message);
    }

    @Test
    public void testEnterFrontLocked() {
        testTakeSpareKey();
        room = (EnhancedRoom) getPlayer().getCurrentRoom();
        String message = processInputList("go north");

        assertEquals(EnhancedMessage.doorLocked("front-door"), message);
    }

    @Test
    public void testExamineFrontDoor() {
        testTakeSpareKey();

        String message = processInputList("use spare-key", "x front-door");
        assertEquals(
            "This is the main entry into your house, but you'll need to find your spare-key to get in!",
            message);
    }

    @Test
    public void pushOnLockedFrontDoor() {
        String message = processInputList("w", "n");
        assertEquals("The locked front-door is blocking you!", message);
    }

    @Test
    public void testThrowItems() {
        testTakeSpareKey();

        String message = processInputList("throw screw-driver");
        assertEquals("Hey! You could've hit somebody with that! Here, take it back.", message);

        message = processInputList("throw light-bulb");
        assertEquals("You feel better now? Here, take it back.?", message);

        message = processInputList("throw spare-key");
        assertEquals("Now why the hell would you do that? Here, take it back.", message);

        message = processInputList("throw wall-lantern");
        assertEquals("You can't throw that.", message);

    }

    // HELPER METHODS:

    /**
     * Process a list of input lines through the game object.
     *
     * This method will assert fail() if any command is unrecognized by the parser.
     *
     * @param commands one or more command lines to be executed as commands.
     * @return the result message of the last command.
     */
    private String processInputList(String... commands) {
        String result = null;
        for (String command : commands) {
            result = game.processInput(command);
            if (result == null) {
                fail("Command not processed: '" + command + "'");
            }
        }
        return result;
    }

    /**
     * Return the player.
     *
     * @return the player.
     */
    private MyPlayer getPlayer() {
        return (MyPlayer) game.getPlayer();
    }

    /**
     * Return the current room.
     *
     * @return the current room.
     */
    private Room getRoom() {
        return getPlayer().getCurrentRoom();
    }

    /**
     * Return the name of the current room.
     *
     * @return the name of the current room.
     */
    private String getRoomName() {
        return getRoom().getName();
    }

    /**
     * Return the additional description of the current room.
     *
     * @return the additional description of the current room.
     */
    private String getRoomAdditional() {
        return getRoom().getAdditionalDescription();
    }

    /**
     * Test to cover the main() method, using the "test" option to suppress input/output
     */
    @Test
    public void testMyGameMain() {
        MyGame.main(new String[] { "test" });
    }
}
