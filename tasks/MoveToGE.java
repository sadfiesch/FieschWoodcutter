package FieschWoodcutter.tasks;

import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankType;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;

public class MoveToGE extends TaskNode {

    @Override
    public boolean accept() {
        return Util.missingEquip() && !Util.grandExchange.contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        log("Moving to GE");
        if (!Util.grandExchange.contains(getLocalPlayer())) {
            Walking.walk(Util.grandExchange.getRandomTile());
            sleepUntil(() -> Util.grandExchange.contains(getLocalPlayer()), 4000);
        }
        return Calculations.random(75, 450);
    }
}
