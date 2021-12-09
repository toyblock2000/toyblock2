package com.toyblock.toyblockserver.difficulty.item.tool;

import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MakeSword {
    Material swordType ;

    double nomalDamage ;
    double nomalSpeed = 1.6;

    int remitLevel = 10;

    double levelDamage ;
    double levelSpeed ;
    double levelSoulBound ;

    public void setWoodenSword() {
        this.swordType = Material.WOODEN_SWORD;
        this.nomalDamage = 4;
        setLevelValue(0.1,0.05,3);
    }
    public void setStoneSword() {
        this.swordType = Material.STONE_SWORD;
        this.nomalDamage = 5;
        setLevelValue(0.2,0.05,4);
    }
    public void setIronSword() {
        this.swordType = Material.IRON_SWORD;
        this.nomalDamage = 6;
        setLevelValue(0.2,0.05,5);
    }
    public void setGoldSword() {
        this.swordType = Material.GOLDEN_SWORD;
        this.nomalDamage = 4;
        setLevelValue(0.03,0.01,8);
    }
    public void setDiamondSword() {
        this.swordType = Material.DIAMOND_SWORD;
        this.nomalDamage = 7;
        setLevelValue(0.2,0.05,6);
    }
    public void setNetheriteSword() {
        this.swordType = Material.NETHERITE_SWORD;
        this.nomalDamage = 8;
        setLevelValue(0.2,0.05,7);
    }
    public void setRemitLevel(int level) {
        this.remitLevel = level;
    }
    private void setLevelValue(double damage , double speed , double soulBound) {
        this.levelDamage = damage;
        this.levelSpeed = speed;
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

    public ItemStack getSword(int level) {
        if(level == 1) {
            return sword_Lv1();
        }
        if(level == 2) {
            return sword_Lv2();
        }
        if(level == 3) {
            return sword_Lv3();
        }
        if(level == 4) {
            return sword_Lv4();
        }
        if(level == 5) {
            return sword_Lv5();
        }
        if(level == 6) {
            return sword_Lv6();
        }
        if(level == 7) {
            return sword_Lv7();
        }
        if(level == 8) {
            return sword_Lv8();
        }
        if(level == 9) {
            return sword_Lv9();
        }
        if(level == 10) {
            return sword_Lv10();
        }
        if(level == 11) {
            return sword_Lv10();
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
        lore.add(ChatColor.LIGHT_PURPLE+ "레벨당 소울바운드 + "+levelSoulBound);
        item.setLore(lore);
    }
    private ItemStack sword_Lv1() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 1;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv2() {
        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 2;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv3() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 3;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv4() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 4;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv5() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 5;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv6() { //특수능력 1

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 6;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv7() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 7;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv8() { //특수능력 2

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 8;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv9() {

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 9;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    private ItemStack sword_Lv10() { //특수능력 3

        ItemStack item = new ItemStack(this.swordType);
        ToolEdit setting = new ToolEdit();
        int level = 10;
        setting.setSwordAttribute(item,level,remitLevel,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }


}
