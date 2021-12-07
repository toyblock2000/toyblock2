package com.toyblock.toyblockserver.difficulty.item;

import com.toyblock.toyblockserver.tool.tool;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwordTool {
    float woodSwordNomalDamage = 4f;

    float stoneSwordNomalDamage = 5f;

    float ironSwordNomalDamage = 4f;

    float goldSwordNomalDamage = 4f;

    float diamondSwordNomalDamage = 4f;

    float netheriteSwordNomalDamage = 4f;

    float allSwordNomalSpeed = 1.6f;

    public void makeTearSwordLore(ItemStack item) {
            List lore = item.getItemMeta().getLore();
            lore.add(" ");
            lore.add("무기 레벨 : 0");
            lore.add("무기 공격력 : 0");
            lore.add("무기 공격속도 : 0");
            item.getItemMeta().lore(lore);
    }
    public void addSwordLore(ItemStack item,String str) {
        List lore = item.getItemMeta().getLore();
        lore.add(str);
        item.getItemMeta().lore(lore);
    }
    public double getSharpnessDamage (ItemStack item) {
        if(!(item.getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL)) ) {
            return 0f;
        }
        int sharpnessLevel = item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL);
        double sharpnessDamage = 0.5+(0.5*sharpnessLevel);
        return sharpnessDamage;

    }
    public double getArthropodsDamage (ItemStack item) {
        if(!(item.getItemMeta().hasEnchant(Enchantment.DAMAGE_ARTHROPODS)) ) {
            return 0f;
        }
        int level = item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ARTHROPODS);
        double damage = (2.5*level);
        return damage;
    }
    public void setWoodSwordAttribute(ItemStack item , int level) {
        int point = 10-level;
        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력",woodSwordNomalDamage-(0.1*point), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", allSwordNomalSpeed-(0.1+point), AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
    }
    public ItemStack getTearWoodSword(int level) {
        ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
        makeTearSwordLore(sword);
        setWoodSwordAttribute(sword,level);
        setTearWoodSwordLore(sword,level);
        return sword;
    }
    public void setEnchantTearWoodSword(ItemStack item) {

    }
    public void setTearWoodSwordLore(ItemStack item , int level) {
        int point = 10 - level;
        float damage = (float) (woodSwordNomalDamage - (0.1 * point));
        float speed = (float) (allSwordNomalSpeed - (0.1 * point));
        loreChanger(item, "무기 공격력", damage);
        loreChanger(item, "무기 공격속도", speed);
    }


    public void loreChanger(ItemStack item,String changeLore , float changeValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            String loreStr = lore.get(i);
            if (!(loreStr.contains(changeLore)) ) {
                continue;
            }
            if(!(loreStr.contains(":")) ) {
                continue;
            }
            String findLore;
            findLore = loreStr.substring(0,loreStr.indexOf(":"));
            findLore = findLore+changeValue;
            lore.remove(i);
            lore.add(i,findLore);
            return;
        }
    }
    public void loreAdd_EnchantDamage(ItemStack item , float addValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            String loreStr = lore.get(i);
            if (!(loreStr.contains("무기 공격력")) ) {
                continue;
            }
            if(!(loreStr.contains("+")) ) {
                continue;
            }
            String findLore;
            findLore = loreStr.substring(0,loreStr.indexOf("+"));
            findLore = findLore+"+ "+addValue;
            lore.remove(i);
            lore.add(i,findLore);
            return;
        }
    }

    public void loreChanger_enchant(ItemStack item,String changeLore , float changeValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            String loreStr = lore.get(i);
            if (!(loreStr.contains(changeLore)) ) {
                continue;
            }
            if(!(loreStr.contains(":")) ) {
                continue;
            }
            String findLore;
            findLore = loreStr.substring(0,loreStr.indexOf(":"));
            findLore = findLore+changeValue;
            lore.remove(i);
            lore.add(i,findLore);
            return;
        }
    }
}
