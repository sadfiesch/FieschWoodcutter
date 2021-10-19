package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.LvlHelper;
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

    private Area currentChopArea;

    @Override
    public boolean accept() {
        currentChopArea = LvlHelper.areaForLvl(SkillTracker.getStartLevel(Skill.WOODCUTTING));
        return !Inventory.isFull() && !currentChopArea.contains(getLocalPlayer()) && !Util.isChopping() && !Util.isMoving();
    }

    @Override
    public int execute() {
        log("Moving to chop area");
        if(!currentChopArea.contains(getLocalPlayer())) {
            Walking.walk(currentChopArea.getRandomTile());
            sleepUntil(() -> currentChopArea.contains(getLocalPlayer()), 4000);
        }

        return Calculations.random(300, 600);
    }
}
