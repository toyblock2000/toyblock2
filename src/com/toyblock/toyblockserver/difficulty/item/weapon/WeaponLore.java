package com.toyblock.toyblockserver.difficulty.item.weapon;

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
        List<String> lore = item.getLore();
        lore.add("");
        lore.add("무기 레벨 : "+level);
        lore.add("무기 데미지 : "+damage);
        lore.add("무기 공격속도 : "+speed);
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
        item.setLore(addEnchantDamageLore(lore,"+ "+add));

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
    public List<String> addEnchantDamageLore(List<String> lore,String damage) {
        for(int i = 0;i<lore.size();i++){
            String str = lore.get(i);
            if(str.contains("무기 데미지 :")) {
                if(str.contains("+")) {
                    str = str.substring(0,str.indexOf("+"));
                }
                str = str+damage;
                lore.remove(i);
                lore.add(i,str);
                return  lore;
            }
        }
        return lore;
    }
    public void setAttribute(ItemStack item,double damage,double attack_speed) {
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
    }

}
