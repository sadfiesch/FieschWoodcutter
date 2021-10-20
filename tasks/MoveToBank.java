package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

public class MoveToBank extends TaskNode {

    @Override
    public boolean accept() {
        return (!Util.hasAxe() || Inventory.isFull()) && !WoodCutter.dropLogs && !Util.currentZone.getBankArea().contains(getLocalPlayer()) && !Util.missingEquip();
    }

    @Override
    public int execute() {
        log("Moving to bank area");
        if(!Util.currentZone.getBankArea().contains(getLocalPlayer())) {
            Walking.walk(Util.currentZone.getBankArea().getRandomTile());
            sleepUntil(() -> Util.currentZone.getBankArea().contains(getLocalPlayer()), 2000);
        }
        return Calculations.random(75, 450);
    }
}
