package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.Client;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;

public class MoveToChopArea extends TaskNode {

    @Override
    public boolean accept() {
        return !Inventory.isFull() && !Util.currentZone.getChopArea().contains(getLocalPlayer()) && !Util.isChopping() && Util.hasAxe() && !Util.missingEquip() && !Util.disableWoodcutter;
    }

    @Override
    public int execute() {
        log("Moving to chop area");
        if(!Util.currentZone.getChopArea().contains(getLocalPlayer())) {
            Walking.walk(Util.currentZone.getChopArea().getRandomTile());
            sleepUntil(() -> Util.currentZone.getChopArea().contains(getLocalPlayer()), 4000);
        }

        return Calculations.random(75, 450);
    }
}
