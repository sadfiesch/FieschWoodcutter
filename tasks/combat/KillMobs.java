package FieschWoodcutter.tasks.combat;

import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.combat.CombatStyle;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

public class KillMobs extends TaskNode {

    @Override
    public boolean accept() {
        return !Inventory.isFull() && Util.currentZone.getAfkArea().contains(getLocalPlayer()) && Util.disableWoodcutter && !Util.isMoving();
    }

    @Override
    public int execute() {
        log("Killing in AFK area");
        //todo make this better
        if(Skills.getRealLevel(Skill.ATTACK) > Skills.getRealLevel(Skill.DEFENCE)){
            Combat.setCombatStyle(CombatStyle.DEFENCE);
        }
        if(Skills.getRealLevel(Skill.DEFENCE) > Skills.getRealLevel(Skill.STRENGTH)){
            Combat.setCombatStyle(CombatStyle.STRENGTH);
        }
        if(Skills.getRealLevel(Skill.STRENGTH) > Skills.getRealLevel(Skill.ATTACK)){
            Combat.setCombatStyle(CombatStyle.ATTACK);
        }
        //NPCs mob = NPCs.closest(object -> object.getName().equalsIgnoreCase(Util.currentZone.getPossibleMobs()[0]) && object.hasAction("Attack"));
        NPC mob = NPCs.closest(NPC -> NPC.getName().equalsIgnoreCase(Util.currentZone.getPossibleMobs()[0]) && NPC.canAttack());
        sleep(1000);
        if (mob == null){
            return Calculations.random(75, 450);
        }

        if(getLocalPlayer().isInCombat()){
            sleepUntil(() -> !getLocalPlayer().isInCombat(), 5000);
            return Calculations.random(75, 450);
        }

        if(!Util.currentZone.getAfkArea().contains(mob) || !Util.currentZone.getAfkArea().contains(getLocalPlayer())){
            return Calculations.random(75, 450);
        }

        if(Combat.getHealthPercent() < 50){
            GroundItem g = GroundItems.closest(object -> object.getName().equalsIgnoreCase(Util.currentZone.getPossibleAfkLoot()[2]) && object.hasAction("Take"));
            if(g == null){
                return Calculations.random(75, 450);
            }
            if(g.distance(getLocalPlayer()) > 7){
                Walking.walk(g);
                sleepUntil(() -> g.distance(getLocalPlayer()) < 7, 1000);
            }
            if(g.interact("Take")){
                sleepUntil(()-> !g.exists(), 2000);
            }

        }else{
            if(mob.distance(getLocalPlayer()) > 5 || !mob.isOnScreen()) {
                Walking.walk(mob);
                sleepUntil(() -> mob.distance(getLocalPlayer()) < 5, 3000);
            }

            //kill this bitch
            if (mob.distance(getLocalPlayer()) < 5 && mob.interact("Attack")) {
                sleepUntil(() -> !getLocalPlayer().isInCombat(), 3000);
            }
        }

        return Calculations.random(75, 450);
    }
}
