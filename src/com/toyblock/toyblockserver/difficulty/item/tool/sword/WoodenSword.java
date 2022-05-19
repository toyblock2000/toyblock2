package com.toyblock.toyblockserver.difficulty.item.tool.sword;

import com.toyblock.toyblockserver.difficulty.item.tool.ItemEdit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class WoodenSword {

    double playerDamage = 1;
    double playerSpeed = 4;

    double maxDamage = 4 - playerDamage;
    double startDamage = 2.2 - playerDamage;
    double damageUp = 0.2;

    double maxSpeed = 1.6 - playerSpeed;
    double startSpeed = 0.7 - playerSpeed;
    double speedUp = 0.1;

    double maxSoul = 9;
    double startSoul = 0;
    double soulUp = 1;

    Material upgradeItem = Material.CHARCOAL;
    ItemStack sword = new ItemStack(Material.WOODEN_SWORD);

    public double damage(int level) {
        double damage = startDamage+(damageUp*(level-1));
        return damage;
    }
    public double speed(int level) {
        double speed = startSpeed+(speedUp*(level-1));
        return speed;
    }
    public double soul(int level) {
        double soul = startSoul+(soulUp*(level-1));
        return soul ;
    }
    public ItemStack get(int level) {
        ItemEdit edit = new ItemEdit();
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        edit.setSwordAttribute(item,damage(level),speed(level));
        edit.setSwordLore(item,level,damage(level)+playerDamage,speed(level)+playerSpeed,soul(level),false);
        return item;
    }



}
