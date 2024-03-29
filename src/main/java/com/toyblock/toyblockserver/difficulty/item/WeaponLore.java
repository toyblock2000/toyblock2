package com.toyblock.toyblockserver.difficulty.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class WeaponLore {
    public void setAttributeLore(ItemStack item,int level, int remitLevel ,double damage, double speed) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level);
        lore.add(ChatColor.WHITE+" 레벨제한  : "+remitLevel);

        lore.add(ChatColor.WHITE+" 데미지 : "+ChatColor.YELLOW+damage);
        lore.add(ChatColor.WHITE+" 공격속도 : "+ChatColor.YELLOW+speed);
        item.setLore(lore);
    }
    public void addEnchantLore(ItemStack item) {
        int level = item.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
        double add = 0.5;
        if(level == 0) {
            return;
        }
        for(int i =0;i<level;i++) {
            add = add+0.5;
        }
        List<String> lore = item.getLore();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(addEnchantDamageLore(lore,"+ "+add));
        item.setItemMeta(meta);
    }

    public void lore() {
        List<String> lore = new ArrayList<>();
        for(int i = 0;i<lore.size();i++){
            String str = lore.get(i);
            if(str.contains("드랍확률")) {
                str = str.replaceAll("[^0-9]","");
            }
        }
    }
    public List addEnchantDamageLore(List<String> setlore,String damage) {
        List lore = setlore;
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(str.contains("무기 데미지")) {
                Bukkit.getPlayer("Devil").chat("무기데미지찾음");
                String newstr = str;
                if(str.contains("+")) {
                    newstr = newstr.substring(0,str.indexOf("+"));
                    Bukkit.getPlayer("Devil").chat("플러스 제거");
                }
                newstr = newstr+damage;
                lore.remove(i);
                lore.add(i,newstr);
                Bukkit.getPlayer("Devil").chat("무기데미지찾음"+newstr);
                return  lore;
            }
        }
        return lore;
    }


    public int getTear(ItemStack item) {
        if(item.getType().isEmpty()) {
            return 0;
        }
        List<String>lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            String str = lore.get(i);
            if (str.contains("무기 레벨 :")) {
                str = str.replaceAll("[^0-9]","");
                int gettear = Integer.parseInt(str);
                return gettear;
            }
        }
        int a = 0;
        return a;
    }
    public void setAttribute(ItemStack item,int level,int remitLevel,double damage,double attack_speed) {
        double set_damage = damage - 1;
        double set_speed = attack_speed - 4;
        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", set_damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", set_speed, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        setAttributeLore(item,level,remitLevel,damage,attack_speed);
    }

}
