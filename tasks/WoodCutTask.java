package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.LvlHelper;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

public class WoodCutTask extends TaskNode {

    private Area currentChopArea;

    @Override
    public boolean accept(){
        currentChopArea = LvlHelper.areaForLvl(SkillTracker.getStartLevel(Skill.WOODCUTTING));
        return !Inventory.isFull() && !isChopping() && currentChopArea.contains(getLocalPlayer());
    }

    @Override
    public int execute(){
        log("Cutting trees");
        GameObject tree = getTargetTree();

        if (tree == null){
            return Calculations.random(500, 1000);
        }

        if (tree.interact("Chop down")) { // If we successfully click on the rock
            sleepUntil(this::isChopping, 2500); // Wait until we're mining, with a max wait time of 2,500ms (2.5 seconds)
        }

        return Calculations.random(500, 1000);
    }

    private GameObject getTargetTree() {
        return GameObjects.closest(object -> object.getName().equalsIgnoreCase(LvlHelper.treeForLvl(SkillTracker.getStartLevel(Skill.WOODCUTTING))) && object.hasAction("Chop down"));
    }


    private boolean isChopping() {
        return getLocalPlayer().isAnimating();
    }
}
