package com.toyblock.toyblockserver.difficulty.item.tool;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MakePickaxe {
    Material pickaxeType ;

    int remitLevel = 10;



    double levelEnergyEfficiency;
    double levelSoulBound ;

    public void setWoodenPickaxe() {
        this.pickaxeType = Material.WOODEN_PICKAXE;
        setLevelValue(1,3);
    }
    public void setStonePickaxe() {
        this.pickaxeType = Material.STONE_PICKAXE;
        setLevelValue(2,4);
    }
    public void setIronPickaxe() {
        this.pickaxeType = Material.IRON_PICKAXE;
        setLevelValue(3,5);
    }
    public void setGoldPickaxe() {
        this.pickaxeType = Material.GOLDEN_PICKAXE;
        setLevelValue(6,8);
    }
    public void setDiamondPickaxe() {
        this.pickaxeType = Material.DIAMOND_PICKAXE;
        setLevelValue(4,6);
    }
    public void setNetheritePickaxe() {
        this.pickaxeType = Material.NETHERITE_PICKAXE;
        setLevelValue(5,7);
    }
    public void setRemitLevel(int level) {
        this.remitLevel = level;
    }
    private void setLevelValue(double energyEfficiency, double soulBound) {
        this.levelEnergyEfficiency = energyEfficiency;
        if(soulBound > 10) {
            this.levelSoulBound = 10;
            return;
        }
        this.levelSoulBound = soulBound;
    }

    private double getEnergyEfficiency(int level) {
        return levelEnergyEfficiency+level;
    }
    private double getSoulBound(int level) {
        return levelSoulBound*level;
    }

    public ItemStack getPickaxe(int level) {
        if(level == 1) {
            return pickaxe_Lv1();
        }
        if(level == 2) {
            return pickaxe_Lv2();
        }
        if(level == 3) {
            return pickaxe_Lv3();
        }
        if(level == 4) {
            return pickaxe_Lv4();
        }
        if(level == 5) {
            return pickaxe_Lv5();
        }
        if(level == 6) {
            return pickaxe_Lv6();
        }
        if(level == 7) {
            return pickaxe_Lv7();
        }
        if(level == 8) {
            return pickaxe_Lv8();
        }
        if(level == 9) {
            return pickaxe_Lv9();
        }
        if(level == 10) {
            return pickaxe_Lv10();
        }
        if(level == 11) {
            return pickaxe_Lv10();
        }
        return null;
    }
    public ItemStack UpgradeGUI() {

        ItemStack item = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"곡괭이 강화");
        item.setItemMeta(meta);
        setUpgradeLore(item);
        return item;
    }
    private void setUpgradeLore(ItemStack item) {
        List lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "레벨당 에너지효율 + "+levelEnergyEfficiency);
        lore.add(ChatColor.LIGHT_PURPLE+ "레벨당 소울바운드 + "+levelSoulBound);
        item.setLore(lore);
    }
    private ItemStack pickaxe_Lv1() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 1;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv2() {
        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 2;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv3() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 3;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv4() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 4;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv5() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 5;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv6() { //특수능력 1

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 6;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv7() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 7;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv8() { //특수능력 2

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 8;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv9() {

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 9;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }
    private ItemStack pickaxe_Lv10() { //특수능력 3

        ItemStack item = new ItemStack(this.pickaxeType);
        ToolEdit setting = new ToolEdit();
        int level = 10;
        setting.setPickaxeAttribute(item,level,remitLevel,getEnergyEfficiency(level),getSoulBound(level));
        return item;
    }



}
