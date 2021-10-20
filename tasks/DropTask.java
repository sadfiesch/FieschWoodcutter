package FieschWoodcutter.tasks;

import FieschWoodcutter.WoodCutter;
import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.script.TaskNode;

public class DropTask extends TaskNode {

    @Override
    public boolean accept(){
        return Inventory.isFull() && WoodCutter.dropLogs && !Util.disableWoodcutter;
    }

    @Override
    public int execute(){
        //Drop everything
        log("Dropping logs");
        //todo drop pattern?
        Inventory.dropAll(item -> item.getName().contains("Logs"));
        return Calculations.random(300, 600);
    }
}
