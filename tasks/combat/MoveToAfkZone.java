package FieschWoodcutter.tasks.combat;

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

public class MoveToAfkZone extends TaskNode {

    @Override
    public boolean accept() {
        return !Inventory.isFull() && !Util.currentZone.getAfkArea().contains(getLocalPlayer()) && Util.disableWoodcutter;
    }

    @Override
    public int execute() {
        log("Moving to afk area");
        if(!Util.currentZone.getAfkArea().contains(getLocalPlayer())) {
            Walking.walk(Util.currentZone.getAfkArea().getRandomTile());
            sleepUntil(() -> Util.currentZone.getAfkArea().contains(getLocalPlayer()), 4000);
        }

        return Calculations.random(300, 600);
    }
}
