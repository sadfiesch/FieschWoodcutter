package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class Banking extends TaskNode {

    @Override
    public boolean accept() {
        return Inventory.isFull() && !WoodCutter.dropLogs && WoodCutter.bankArea.contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        log("Banking");
        if(Bank.isOpen()){
            Bank.depositAllExcept(item -> item.getName().contains("axe"));
        }else{
            GameObject bankBooth = GameObjects.closest(object -> object.getName().equalsIgnoreCase("Bank booth") && object.hasAction("Bank"));
            if (bankBooth.interact("Bank")) {
                sleep(Calculations.random(350, 700)); //todo make better
            }
        }

        return Calculations.random(4100, 5110);
    }
}
