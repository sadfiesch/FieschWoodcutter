package FieschWoodcutter;

import FieschWoodcutter.tasks.*;
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

    public static Area chopArea = new Area(3203, 3256, 3180, 3236);
    public static Area bankArea = new Area(3212, 3229, 3205, 3217, 2);
    public static Area stairsArea = new Area(3206, 3229, 3205, 3228);

    @Override
    public void onStart(){
        SkillTracker.start(Skill.WOODCUTTING);
        addNodes(new WoodCutTask(), new DropTask(), new MoveToChopArea(), new MoveToBank(), new Banking());

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

    }
}
