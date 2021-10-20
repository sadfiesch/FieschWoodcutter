package FieschWoodcutter.tasks;

import FieschWoodcutter.tasks.util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankMode;
import org.dreambot.api.methods.container.impl.bank.BankType;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class GrandExchangeTask extends TaskNode {

    private int goldAmount = 0;
    private int logAmount = 0;



    @Override
    public boolean accept() {
        return Util.missingEquip() && Util.grandExchange.contains(getLocalPlayer());
    }

    //Yes this is absolute shit. Yes it works. fight me
    @Override
    public int execute() {
        log("Banking at GE");

        if(!Util.bankCheck){
            if(!Bank.isOpen()){
                if(Bank.getClosestBank(BankType.EXCHANGE).interact("Bank")){
                    sleepUntil(() -> Bank.isOpen(), 2000);
                }
            }
            if(Inventory.isFull() || Inventory.fullSlotCount() > 25){
                if(Bank.depositAllItems()){
                    sleepUntil(() -> Inventory.isEmpty(), 2000);
                }
            }
            if(Bank.withdrawAll("Coins")){
                sleep(500); //todo make better
            }
            if(Bank.getWithdrawMode() != BankMode.NOTE){
                if(Bank.setWithdrawMode(BankMode.NOTE)){
                    sleepUntil(() -> (Bank.getWithdrawMode() == BankMode.NOTE), 2000);
                }
            }
            //Todo change hardcoding of logs
            if(Bank.contains("Logs")){
                Bank.withdrawAll("Logs");
                sleepUntil(() -> Inventory.contains("Logs"), 2000);
            }
            Util.bankCheck = true;
        }

        if(Bank.isOpen()){
            if(Bank.close()){
                sleepUntil(() -> !Bank.isOpen(), 2000);
            }
        }

        if(!GrandExchange.isOpen()){
            if(GrandExchange.open()){
                sleepUntil(() -> GrandExchange.isOpen(), 2000);
            }
        }

        //Todo fix hardcoding
        if(!Util.soldLogs && Inventory.contains("Logs")){
            if(GrandExchange.openSellScreen(0)){
                sleepUntil(() -> GrandExchange.isSellOpen() , 2000);

                if(GrandExchange.addSellItem("Logs")){
                    sleep(500);
                    if(GrandExchange.getDecreasePriceFivePercentButton().interact()){
                        sleep(500);
                    }
                    if(GrandExchange.getDecreasePriceFivePercentButton().interact()){
                        sleep(500);
                    }
                }
                if(GrandExchange.confirm()){
                    sleepUntil(() -> !GrandExchange.isSellOpen(), 2000);
                }
            }
            Util.soldLogs = true;
        }

        if(GrandExchange.isReadyToCollect()){
            if(GrandExchange.collect()){
                sleepUntil(() -> !GrandExchange.isReadyToCollect(), 2000);
            }
        }

        if(!Util.hasAxe()){
            if(!GrandExchange.isBuyOpen()) {
                if (GrandExchange.openBuyScreen(1)) {
                    sleepUntil(() -> GrandExchange.isBuyOpen(), 2000);
                }
            }
            if(GrandExchange.addBuyItem(Util.currentZone.getAxe())){
                sleep(500);
                if(GrandExchange.getIncreasePriceFivePercentButton().interact()){
                    sleep(500);
                }
                if(GrandExchange.getIncreasePriceFivePercentButton().interact()){
                    sleep(500);
                }
                if(GrandExchange.confirm()){
                    sleepUntil(() -> !GrandExchange.isBuyOpen(), 2000);
                }
                sleep(2000);
            }
        }


/*
        if(Bank.close()){
            sleepUntil(() -> !Bank.isOpen(), 2000);
        }

        if(GrandExchange.open()){
            sleep(1000);
            GrandExchange.openSellScreen(1);
            sleep(1000);
            GrandExchange.sellItem("Logs", 1, 50);
            sleep(1000);
        }

*/

        //has coins? -> yes take them -> no? sell logs
        //need axe? -> buy one
        //need armor? buy one
        //need
        return Calculations.random(75, 450);
    }

}
