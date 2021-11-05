package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class WoodenSword {
    public ItemStack woodenSword_Lv1() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv2() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv3() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv4() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv5() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv6() { //특수능력 1
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv7() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv8() { //특수능력 2
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv9() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 0.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack woodenSword_Lv10() { //특수능력 3
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }


}
