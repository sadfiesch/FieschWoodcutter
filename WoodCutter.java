package FieschWoodcutter;

import FieschWoodcutter.tasks.*;
import FieschWoodcutter.tasks.combat.KillMobs;
import FieschWoodcutter.tasks.combat.MoveToAfkZone;
import FieschWoodcutter.tasks.util.Util;
import FieschWoodcutter.tasks.util.ZoneRequirements;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

import java.awt.*;

@ScriptManifest(category = Category.WOODCUTTING, name = "FieschWoodcutter", author = "fiesch", version = 0.5)
public class WoodCutter extends TaskScript {

    //todo set this from GUI
    public static boolean dropLogs = false;


    @Override
    public void onStart(){
        SkillTracker.start(Skill.WOODCUTTING);
        addNodes(new GrandExchangeTask(), new MoveToGE(), new ZoneMgr(), new AntiBan(), new WoodCutTask(), new DropTask(), new MoveToChopArea(), new MoveToBank(), new Banking(), new MoveToAfkZone(), new KillMobs());

        //Get Optimal zone for Wc Lvl on start.

        Util.disableWoodcutter = false;
        Util.disableWoodTimer = 0;
        Util.currentAntiban = false;
        Util.currentZone = Util.getBestZone();
    }

    @Override
    public void onPaint(Graphics g) {

        String xpGained = String.format(
                "Woodchopping Experience: %d (%d per hour)", // The paint's text format. '%d' will be replaced with the next two arguments.
                SkillTracker.getGainedExperience(Skill.WOODCUTTING),
                SkillTracker.getGainedExperiencePerHour(Skill.WOODCUTTING)
        );

        String lvlGained = String.format(
                "WC Lvl started: %d  Lvl current: %d", // The paint's text format. '%d' will be replaced with the next two arguments.
                SkillTracker.getStartLevel(Skill.WOODCUTTING),
                SkillTracker.getGainedLevels(Skill.WOODCUTTING) + SkillTracker.getStartLevel(Skill.WOODCUTTING)
        );

        g.drawString("FieschWoodCutter", 5, 35);
        g.drawString(xpGained, 5, 50);
        g.drawString(lvlGained, 5, 65);


        if(Util.currentAntiban){
            g.drawString("Antiban Currently active", 250, 200);
        }

        if(Util.disableWoodcutter){
            g.drawString("Your current combat level is too low to change zone. Leveling up in AFK Zone.", 50, 210);
        }

    }
}
