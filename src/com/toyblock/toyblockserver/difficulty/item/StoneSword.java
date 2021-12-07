package com.toyblock.toyblockserver.difficulty.item;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class StoneSword {
    public ItemStack StoneSword_Lv1() {
        //공격력 3
        //공속 1.5
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 1");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 0.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        WeaponLore setlore = new WeaponLore();
        setlore.setAttributeLore(item,3,10,1.5,3);
        return item;
    }
    public ItemStack StoneSword_Lv2() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 2");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv3() {

        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 3");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv4() {

        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 4");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 3.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.7, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv5() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 5");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.7, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv6() { //특수능력 1
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 6");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 4, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv7() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 7");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 4.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv8() { //특수능력 2
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 8");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 4.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.9, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);

        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv9() {
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 9");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 1.9, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack StoneSword_Lv10() { //특수능력 3
        UUID uuid = new UUID(000102, 2344);
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("무기레벨 10");
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", 5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", 2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        item.setItemMeta(meta);
        return item;
    }
}
