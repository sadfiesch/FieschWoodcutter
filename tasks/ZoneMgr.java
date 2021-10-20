package FieschWoodcutter.tasks;

import FieschWoodcutter.tasks.util.Util;
import FieschWoodcutter.tasks.util.ZoneRequirements;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.TaskNode;

import static FieschWoodcutter.tasks.util.Util.currentZone;
import static FieschWoodcutter.tasks.util.Util.getBestZoneForWcLvl;

public class ZoneMgr extends TaskNode {


    @Override
    public boolean accept() {
        return currentZone != getBestZoneForWcLvl() && System.currentTimeMillis() >= (Util.disableWoodTimer + 5*60*1000); //checks every 10 min if combat level is high enough now to go to new zone
    }

    @Override
    public int execute() {
        ZoneRequirements bestZone = getBestZoneForWcLvl();
        if(bestZone.getMinCombatLvl() > Combat.getCombatLevel()){
             Util.disableWoodcutter = true;
             Util.disableWoodTimer = System.currentTimeMillis();
             log("Failed to change WC Zone not high enough combat level.");
        }else{
            log("Changing WC Zone");
            currentZone = bestZone;
            Util.disableWoodcutter = false;
            Util.disableWoodTimer = 0;
        }

        //check axe level

        return 0;
    }
}
