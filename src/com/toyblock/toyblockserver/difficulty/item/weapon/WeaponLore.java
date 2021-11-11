package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeaponLore {
    public void setAttributeLore(ItemStack item,int level, double damage, double speed) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add("무기 레벨 : "+level);
        lore.add("무기 데미지 : "+damage);
        lore.add("무기 공격속도 : "+speed);
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
                Bukkit.getPlayer("toy_block").chat("무기데미지찾음");
                String newstr;
                if(str.contains("+")) {
                    newstr = str.substring(0,str.indexOf("+"));
                }
                newstr = str+damage;
                lore.remove(i);
                lore.add(i,newstr);
                Bukkit.getPlayer("toy_block").chat("무기데미지찾음"+newstr);
                return  lore;
            }
        }
        return lore;
    }
    public ItemStack upgradeTear(ItemStack item) {
        List<String>lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            String str = lore.get(i);
            if (str.contains("무기 레벨 :")) {
                str = str.replaceAll("[^0-9]","");
                int gettear = Integer.parseInt(str);
                if(item.getType().equals(Material.WOODEN_SWORD)) {
                    TearSpawn tear = new TearSpawn();
                    return tear.woodenTearSpawn(item,gettear+1);
                }


            }
        }
        return item;
    }
    public void setAttribute(ItemStack item,int level,double damage,double attack_speed) {
        double set_damage = damage - 1;
        double set_speed = attack_speed - 1;
        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", set_damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", set_speed, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        setAttributeLore(item,level,damage,attack_speed);
    }

}