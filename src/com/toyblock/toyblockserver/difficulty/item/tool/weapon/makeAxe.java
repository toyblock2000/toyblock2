package com.toyblock.toyblockserver.difficulty.item.tool.weapon;

import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class makeAxe {
    Material axeType ;

    double nomalDamage ;
    double nomalSpeed = 1.6;

    int remitLevel = 10;

    double levelEnergyEfficiency;
    double levelDamage ;
    double levelSpeed ;
    double levelSoulBound ;

    public void setWoodenAxe() {
        this.axeType = Material.WOODEN_AXE;
        this.nomalDamage = 7;
        setLevelValue(0.2,0.05,1,3);
    }
    public void setStoneAxe() {
        this.axeType = Material.STONE_AXE;
        this.nomalDamage = 9;
        setLevelValue(0.3,0.05,2,4);
    }
    public void setIronAxe() {
        this.axeType = Material.IRON_AXE;
        this.nomalDamage = 9;
        setLevelValue(0.3,0.05,3,5);
    }
    public void setGoldAxe() {
        this.axeType = Material.GOLDEN_AXE;
        this.nomalDamage = 7;
        setLevelValue(0.04,0.01,6,8);
    }
    public void setDiamondAxe() {
        this.axeType = Material.DIAMOND_AXE;
        this.nomalDamage = 9;
        setLevelValue(0.3,0.05,4,6);
    }
    public void setNetheriteAxe() {
        this.axeType = Material.NETHERITE_AXE;
        this.nomalDamage = 10;
        setLevelValue(0.3,0.05,5,7);
    }
    public void setRemitLevel(int level) {
        this.remitLevel = level;
    }
    private void setLevelValue(double damage , double speed ,double EnergyEfficiency ,double soulBound) {
        this.levelDamage = damage;
        this.levelSpeed = speed;
        if(EnergyEfficiency > 10 ) {
            this.levelEnergyEfficiency = 10;
            return;
        }
        this.levelEnergyEfficiency = EnergyEfficiency;
        if(soulBound > 10) {
            this.levelSoulBound = 10;
            return;
        }
        this.levelSoulBound = soulBound;
    }

    private double getDamage(int level) {
        double sum = levelDamage*(10-level);
        double value = nomalDamage-sum;
        return value;
    }
    private double getAttackSpeed(int level) {
        double sum = levelSpeed*(10-level);
        double value = nomalSpeed-sum;
        return value;
    }
    private double getSoulBound(int level) {
        return levelSoulBound*level;
    }
    private double getEnergyEfficiency(int level) {
        return levelEnergyEfficiency+level;
    }

    public ItemStack getAxe(int level) {
        if(level == 1) {
            return axe_Lv1();
        }
        if(level == 2) {
            return axe_Lv2();
        }
        if(level == 3) {
            return axe_Lv3();
        }
        if(level == 4) {
            return axe_Lv4();
        }
        if(level == 5) {
            return axe_Lv5();
        }
        if(level == 6) {
            return axe_Lv6();
        }
        if(level == 7) {
            return axe_Lv7();
        }
        if(level == 8) {
            return axe_Lv8();
        }
        if(level == 9) {
            return axe_Lv9();
        }
        if(level == 10) {
            return axe_Lv10();
        }
        if(level == 11) {
            return axe_Lv10();
        }
        return null;
    }
    public ItemStack UpgradeGUI() {

        ItemStack item = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"검 강화");
        item.setItemMeta(meta);
        setUpgradeLore(item);
        return item;
    }
    private void setUpgradeLore(ItemStack item) {
        List lore = new ArrayList();
        lore.add(ChatColor.RED + "레벨당 데미지 + "+levelDamage);
        lore.add(ChatColor.YELLOW + "레벨당 공격속도 + "+levelSpeed);
        lore.add(ChatColor.YELLOW + "레벨당 에너지효율 + "+levelEnergyEfficiency);
        lore.add(ChatColor.LIGHT_PURPLE+ "레벨당 소울바운드 + "+levelSoulBound);
        item.setLore(lore);
    }
    private ItemStack axe_Lv1() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 1;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv2() {
        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 2;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv3() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 3;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv4() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 4;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv5() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 5;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv6() { //특수능력 1

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 6;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv7() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 7;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv8() { //특수능력 2

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 8;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv9() {

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 9;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack axe_Lv10() { //특수능력 3

        ItemStack item = new ItemStack(this.axeType);
        ToolEdit setting = new ToolEdit();
        int level = 10;
        setting.setAxeAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }


}
