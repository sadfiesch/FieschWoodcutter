package FieschWoodcutter.tasks;

import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.script.TaskNode;

public class AntiBan extends TaskNode {

    private long timeSinceSmallAntiban;//small antiban every 5 min
    //private long timeSinceBigAntiban; //big antiban every 30 min

    @Override
    public boolean accept() {
        if(timeSinceSmallAntiban == 0){
            timeSinceSmallAntiban = System.currentTimeMillis();
        }

       /* if(timeSinceBigAntiban == 0){
            timeSinceBigAntiban = System.currentTimeMillis();
        }*/

        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis >= (timeSinceSmallAntiban + (Calculations.random(6, 9))*60*1000) && !Util.disableWoodcutter && !Util.currentAntiban) {
            return true;
        }

        return false;
    }

    //Dont judge this is not done yet.
    @Override
    public int execute() {
        log("Small antiban executing");
        Util.currentAntiban = true;
        int antibanRnd = Calculations.random(1, 6);

        switch (antibanRnd){
            case 1:
                Skills.open();
                sleep(Calculations.random(0*1000, 2*1000));
                Skills.hoverSkill(Skill.WOODCUTTING);
                sleep(Calculations.random(0*1000, 2*1000));
                Skills.hoverSkill(Skill.ATTACK);
                sleep(Calculations.random(0*1000, 2*1000));
                Skills.hoverSkill(Skill.DEFENCE);
                sleep(Calculations.random(0*1000, 2*1000));
                Tabs.open(Tab.INVENTORY);
                break;

            default:
                sleep(Calculations.random(30*1000, 90*1000));
                break;
        }
        timeSinceSmallAntiban = System.currentTimeMillis();
        Util.currentAntiban = false;
        return Calculations.random(75, 450);
    }
}
