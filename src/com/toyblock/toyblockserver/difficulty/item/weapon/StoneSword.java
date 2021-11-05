package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class StoneSword {
    public ItemStack StoneSword_Lv1() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv2() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv3() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv4() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv5() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.7,AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv6() { //특수능력 1
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv7() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv8() { //특수능력 2
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 4.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv9() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 1.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
    public ItemStack StoneSword_Lv10() { //특수능력 3
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add
                (new AttributeModifier(uuid,"무기 공격력", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add
                (new AttributeModifier(uuid,"무기 공격속도", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        return item;
    }
}