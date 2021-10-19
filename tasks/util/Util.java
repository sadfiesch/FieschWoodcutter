package FieschWoodcutter.tasks.util;

import static org.dreambot.api.Client.getLocalPlayer;

public class Util {

    public static boolean isChopping() {
        return getLocalPlayer().isAnimating();
    }

    public static boolean isMoving(){
        return getLocalPlayer().isMoving();
    }
}
