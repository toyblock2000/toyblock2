package com.toyblock.toyblockserver.difficulty.item;

import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantUpgrade implements Listener {
    private InventoryClickEvent event;

    @EventHandler
    public void enchantEvent(EnchantItemEvent event) {
        WeaponLore lore = new WeaponLore();
        ItemStack item = event.getItem();
        lore.addEnchantLore(item);
    }

    @EventHandler
    public void enchantclick(EnchantItemEvent event) {
        bug.chat("이벤트 생성");
        ItemStack item = event.getItem();
        int value;
        if(event.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ALL)) {
            value = event.getEnchantsToAdd().get(Enchantment.DAMAGE_ALL);
            loreAdd_EnchantDamage(item, ChatColor.YELLOW+""+value);
        }
        if(event.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_UNDEAD)) {
            value = event.getEnchantsToAdd().get(Enchantment.DAMAGE_UNDEAD);
            loreAdd_EnchantDamage(item, ChatColor.WHITE+""+value);
        }
        if(event.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
            value = event.getEnchantsToAdd().get(Enchantment.DAMAGE_ARTHROPODS);
            loreAdd_EnchantDamage(item, ChatColor.GRAY+""+value);
        }
        if(event.getItem().getItemMeta().hasEnchants()) {
            bug.chat("잇음");
        }
        else{
            bug.chat("없음");
        }
    }
    public void loreAdd_EnchantDamage(ItemStack item , String addValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {

            String loreStr = lore.get(i);
            if (!(loreStr.contains("무기 데미지")) ) {
                continue;
            }
            String addLore = loreStr+" + "+addValue;
            lore.remove(i);
            lore.add(i,addLore);
            item.setLore(lore);
            return;
        }
    }
    public double getSharpnessDamage (ItemStack item) {

        int sharpnessLevel = item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL);
        double sharpnessDamage = 0.5+(0.5*sharpnessLevel);
        return sharpnessDamage;

    }
    public double getArthropodsDamage (ItemStack item) {

        int level = item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ARTHROPODS);
        double damage = (2.5*level);
        return damage;
    }
    public double getSmiteDamage (ItemStack item) {

        int level = item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_UNDEAD);
        double damage = (2.5*level);
        return damage;
    }
}
