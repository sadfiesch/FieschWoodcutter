package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class Banking extends TaskNode {

    @Override
    public boolean accept() {
        return (!Util.hasAxe() || Inventory.isFull()) && !WoodCutter.dropLogs && Util.currentZone.getBankArea().contains(getLocalPlayer()) && !Util.missingEquip();
    }

    @Override
    public int execute() {
        log("Banking");
        if(Bank.isOpen()){
            if(Util.hasAxe()){
                Bank.depositAllExcept(item -> item.getName().contains("axe"));
            }else{
                if(Bank.contains(Util.currentZone.getAxe())){
                    Bank.withdraw(Util.currentZone.getAxe());
                }else{
                    Util.needAxe = true;
                }
            }
        }
        if(!Bank.isOpen()){
            GameObject bankBooth = GameObjects.closest(object -> object.getName().equalsIgnoreCase("Bank booth") && object.hasAction("Bank"));
            if(bankBooth.distance(getLocalPlayer()) > 2) {
                Walking.walk(bankBooth);
                sleepUntil(() -> bankBooth.distance(getLocalPlayer()) < 2, 3000);
            }
            if (bankBooth.distance(getLocalPlayer()) < 2 && bankBooth.interact("Bank") && bankBooth.isOnScreen()) {
                sleepUntil(() -> Bank.isOpen(), 2000);
            }
        }

        return Calculations.random(300, 600);
    }
}
