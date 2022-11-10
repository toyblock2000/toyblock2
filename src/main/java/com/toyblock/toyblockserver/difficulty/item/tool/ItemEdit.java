package com.toyblock.toyblockserver.difficulty.item.tool;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemEdit {
    // 레벨 1/ 10
    // 데미지
    // 공격속도
    // 소울바운드

    String str_level = ChatColor.WHITE+" 레벨 : ";
    String str_damage = ChatColor.WHITE+" 데미지 : ";
    String str_speed = ChatColor.WHITE+" 공격속도 : ";
    String str_soul = ChatColor.WHITE+" 소울바운드 : ";
    String str_remit = ChatColor.WHITE+" / 10 ";
    String str_noUpgrade = ChatColor.WHITE+" / 업그레이드 제한";
    String str_percent ="%";
    public void setSwordLore (ItemStack item ,int level , double damage , double speed , double soul, boolean remit) {
        List lore = new ArrayList();
        if(remit==true) {
            lore.add(str_level + ChatColor.GREEN + level + str_noUpgrade);
        }
        else {
            lore.add(str_level + ChatColor.GREEN + level + str_remit);
        }
        lore.add(str_damage + ChatColor.RED + math(damage));
        lore.add(str_speed + ChatColor.YELLOW + math(speed));
        lore.add(str_soul + ChatColor.DARK_PURPLE + math(soul) + str_percent);
        item.setLore(lore);
        itemFlag(item);
    }
    public void itemFlag(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
    }

    public double math(double value) {
        return Math.floor(value*100)/100.0;
    }
    public void setSwordAttribute(ItemStack item, double damage, double speed) {
        ItemMeta meta = item.getItemMeta();

        AttributeModifier setDamage = new AttributeModifier(UUID.randomUUID(), "데미지", damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setDamage);

        AttributeModifier setSpeed  = new AttributeModifier(UUID.randomUUID(), "공격속도", speed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setSpeed);
        item.setItemMeta(meta);
    }
    public float loreFinder_level(ItemStack item) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(str_level))) {
                continue;
            }
            if(!(str.contains("/"))) {
                continue;
            }
            String[] array = str.split("\\/");



            return Float.parseFloat(array[0].replaceAll("[^0-9.]", ""));
        }
        return 0f;
    }
}
