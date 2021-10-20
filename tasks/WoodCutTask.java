package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

public class WoodCutTask extends TaskNode {

    @Override
    public boolean accept(){
        return !Inventory.isFull() && Util.currentZone.getChopArea().contains(getLocalPlayer()) && !Util.isChopping() && !Util.isMoving() && !Util.isCombat() && Util.hasAxe() && !Util.missingEquip() && !Util.disableWoodcutter;
    }

    @Override
    public int execute(){
        log("Cutting trees");
        GameObject tree = getTargetTree();

        if (tree == null){
            log("tree null");
            return Calculations.random(75, 450);
        }

        if(!Util.currentZone.getChopArea().contains(tree) || !Util.currentZone.getChopArea().contains(getLocalPlayer())){
            Walking.walk(Util.currentZone.getChopArea().getRandomTile());
            sleepUntil(() -> Util.currentZone.getChopArea().contains(getLocalPlayer()), 4000);
            return Calculations.random(75, 450);
        }

        if(tree.distance(getLocalPlayer()) >= 5 || !tree.isOnScreen()) {
            Walking.walk(tree);
            sleepUntil(() -> tree.distance(getLocalPlayer()) <= 5, 3000);
        }

        //chop this bitch
        if (tree.distance(getLocalPlayer()) < 5 && tree.interact("Chop down")) {
            sleepUntil(this::isChopping, 3000);
        }

        return Calculations.random(75, 450);
    }

    private GameObject getTargetTree() {
        return GameObjects.closest(object -> object.getName().equalsIgnoreCase(Util.currentZone.getTreeType()) && object.hasAction("Chop down"));
    }

    //todo make better // use util functions
    private boolean isChopping() {
        return getLocalPlayer().isAnimating();
    }

    private boolean isMoving(){
        return getLocalPlayer().isMoving();
    }
}
