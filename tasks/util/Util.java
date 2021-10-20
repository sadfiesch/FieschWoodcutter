package FieschWoodcutter.tasks.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.skills.Skills;

import static org.dreambot.api.Client.getLocalPlayer;

public class Util {

    public static ZoneRequirements[] zones = {
            new ZoneRequirements(1, 21, 3, "Tree","Bronze axe", new Area(3203, 3256, 3180, 3236), new Area(3210, 3220, 3205, 3217, 2), new Area(3236, 3300, 3225, 3287), new String[]{"Chicken"}, new String[]{"Bones", "Raw Chicken" ,"Feather"}, false ),
            new ZoneRequirements(21, 31, 8, "Oak","Mithril axe", new Area(3255, 3280, 3227, 3248), new Area(3210, 3220, 3205, 3217, 2), new Area(3264, 3295, 3254, 3256), new String[]{"Cow","Cow calf"}, new String[]{"Bones", "Raw Beef", "Cowhide"}, true ),
            new ZoneRequirements(31, 60, 15, "Willow","Adamant axe", new Area(3091, 3239, 3079, 3225), new Area(3094, 3245, 3092, 3242, 0), new Area(3264, 3295, 3254, 3256), new String[]{"Cow","Cow calf"}, new String[]{"Bones", "Raw Beef", "Cowhide"}, true )
    };

    public static Area grandExchange = new Area(3168, 3493, 3161, 3486);

    public static ZoneRequirements currentZone;

    public static Boolean disableWoodcutter = false;

    public static long disableWoodTimer = 0;

    public static Boolean currentAntiban = false;

    public static Boolean needAxe = false;


    //todo ge help find better way to do this:
    public static Boolean bankCheck = false;
    public static Boolean soldLogs = false;

    public static ZoneRequirements getBestZoneForWcLvl(){
        for(ZoneRequirements z : zones) {
            if(z.getMinWcLvl() <= Skills.getRealLevel(Skill.WOODCUTTING) && z.getMaxWcLvl() >= Skills.getRealLevel(Skill.WOODCUTTING)){
                return z;
            }
        }
        //this is just a failsafe
        return zones[0];
    }

    public static ZoneRequirements getBestZone(){
        for(ZoneRequirements z : zones) {
            if(z.getMinWcLvl() <= Skills.getRealLevel(Skill.WOODCUTTING) && z.getMaxWcLvl() >= Skills.getRealLevel(Skill.WOODCUTTING) && z.getMinCombatLvl() < Combat.getCombatLevel()){
                return z;
            }
        }
        //this is just a failsafe
        return zones[0];
    }

    public static boolean hasAxe(){
        return Inventory.contains(currentZone.getAxe());
    }

    //todo make this useless shit be functioning
    //dont hate on me i'm just lazy
    public static boolean missingEquip(){
        /*if(needAxe){
            return true;
        }
        if(currentZone.isNeedEquip()){
            //todo check what armour he is wearing and if there is missing?
            return true;
        }*/
        return !hasAxe();
    }

    public static boolean isChopping() {
        return getLocalPlayer().isAnimating();
    }

    public static boolean isMoving(){
        return getLocalPlayer().isMoving();
    }

    public static boolean isCombat() { return getLocalPlayer().isInCombat(); }
}
