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
        if(!WoodCutter.bankArea.contains(getLocalPlayer())) {
            Walking.walk(WoodCutter.bankArea.getRandomTile());
            sleepUntil(() -> WoodCutter.bankArea.contains(getLocalPlayer()), 2000);
        }
        return Calculations.random(75, 450);
    }
}
