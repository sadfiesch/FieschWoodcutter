package FieschWoodcutter.tasks.util;

import org.dreambot.api.methods.map.Area;

public class ZoneRequirements {

    private int minWcLvl;
    private int maxWcLvl;
    private int minCombatLvl;
    private String treeType;
    private String axe;
    private Area chopArea;
    private Area bankArea;
    private Area afkArea;
    private String[] possibleMobs;
    private String[] possibleAfkLoot;
    private boolean needEquip;

    public ZoneRequirements(int minWcLvl, int maxWcLvl, int minCombatLvl, String treeType, String axe, Area chopArea, Area bankArea, Area afkArea, String[] possibleMobs, String[] possibleAfkLoot, boolean needEquip) {
        this.minWcLvl = minWcLvl;
        this.maxWcLvl = maxWcLvl;
        this.minCombatLvl = minCombatLvl;
        this.treeType = treeType;
        this.axe = axe;
        this.chopArea = chopArea;
        this.bankArea = bankArea;
        this.afkArea = afkArea;
        this.possibleMobs = possibleMobs;
        this.possibleAfkLoot = possibleAfkLoot;
        this.needEquip = needEquip;
    }

    public boolean isNeedEquip() {
        return needEquip;
    }

    public void setNeedEquip(boolean needEquip) {
        this.needEquip = needEquip;
    }

    public String[] getPossibleMobs() {
        return possibleMobs;
    }

    public void setPossibleMobs(String[] possibleMobs) {
        this.possibleMobs = possibleMobs;
    }

    public int getMinWcLvl() {
        return minWcLvl;
    }

    public void setMinWcLvl(int minWcLvl) {
        this.minWcLvl = minWcLvl;
    }

    public int getMaxWcLvl() {
        return maxWcLvl;
    }

    public void setMaxWcLvl(int maxWcLvl) {
        this.maxWcLvl = maxWcLvl;
    }

    public int getMinCombatLvl() {
        return minCombatLvl;
    }

    public void setMinCombatLvl(int minCombatLvl) {
        this.minCombatLvl = minCombatLvl;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public String getAxe() {
        return axe;
    }

    public void setAxe(String axe) {
        this.axe = axe;
    }

    public Area getChopArea() {
        return chopArea;
    }

    public void setChopArea(Area chopArea) {
        this.chopArea = chopArea;
    }

    public Area getBankArea() {
        return bankArea;
    }

    public void setBankArea(Area bankArea) {
        this.bankArea = bankArea;
    }

    public Area getAfkArea() {
        return afkArea;
    }

    public void setAfkArea(Area afkArea) {
        this.afkArea = afkArea;
    }

    public String[] getPossibleAfkLoot() {
        return possibleAfkLoot;
    }

    public void setPossibleAfkLoot(String[] possibleAfkLoot) {
        this.possibleAfkLoot = possibleAfkLoot;
    }
}
