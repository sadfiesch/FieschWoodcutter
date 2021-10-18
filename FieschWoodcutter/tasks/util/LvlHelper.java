package FieschWoodcutter.tasks.util;

import org.dreambot.api.methods.map.Area;

public class LvlHelper {
    //todo make this better and add other trees

    public static String treeForLvl(int lvl){
        String treeType = "Tree";
        if(lvl >= 15){
            treeType = "Oak";
        }
        return treeType;
    }

    public static Area areaForLvl(int lvl){
        Area chopArea = new Area(3203, 3256, 3180, 3236);
        if(lvl >= 15){
            chopArea = new Area(3255, 3280, 3227, 3248);
        }
        return chopArea;
    }

}
