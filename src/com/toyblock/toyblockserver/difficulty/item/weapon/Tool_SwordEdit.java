package com.toyblock.toyblockserver.difficulty.item.weapon;

import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.xml.validation.Validator;
import java.util.List;

public class Tool_SwordEdit {
    public void addEnchantLore(ItemStack item) {
        if(!(item.getItemMeta().hasEnchants())) {
            return;
        }

        if(item.getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL)) {
            double value = getSharpnessDamage(item);
            loreAdd_EnchantDamage(item,""+ ChatColor.RED+value);
        }
        if(item.getItemMeta().hasEnchant(Enchantment.DAMAGE_UNDEAD)) {
            double value = getSmiteDamage(item);
            loreAdd_EnchantDamage(item,""+ ChatColor.WHITE+value);
        }
        if(item.getItemMeta().hasEnchant(Enchantment.DAMAGE_ARTHROPODS)) {
            double value = getArthropodsDamage(item);
            loreAdd_EnchantDamage(item,""+ ChatColor.DARK_PURPLE+value);
        }


    }
    public void loreAdd_EnchantDamage(ItemStack item , String addValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {
            bug.chat("포진입");
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
