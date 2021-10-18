package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

public class MoveToBank extends TaskNode {

    @Override
    public boolean accept() {
        return Inventory.isFull() && !WoodCutter.dropLogs && !WoodCutter.bankArea.contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        log("Moving to bank area");
        if(!WoodCutter.stairsArea.contains(getLocalPlayer())){
            Walking.walk(WoodCutter.stairsArea.getRandomTile());
            sleep(Calculations.random(350, 700)); //todo make better
        }else {
            //he will do this 2 times
            GameObject stairs = GameObjects.closest(object -> object.getName().equalsIgnoreCase("Staircase") && object.hasAction("Climb-up"));
            if (stairs.interact("Climb-up")) {
                sleep(Calculations.random(1000, 1100)); //todo make better
            }
            GameObject stairs2 = GameObjects.closest(object -> object.getName().equalsIgnoreCase("Staircase") && object.hasAction("Climb-up") && object.hasAction("Climb-down"));
            if (stairs2.interact("Climb-up")) {
                sleep(Calculations.random(1000, 1100)); //todo make better
            }
        }
        return Calculations.random(4100, 5110);
    }
}
