package com.toyblock.toyblockserver.item;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class customItem {
    String ws = "WoodenSword";
    public ItemStack ws_damageUp (int value) {
        List lore = new ArrayList();
        lore.add(ChatColor.RED+"데미지 I");
        ItemStack damageUp = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta)damageUp.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE+"*클릭시 재료를 사용해 강화*");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.setColor(Color.RED);
        meta.addCustomEffect(new PotionEffect( PotionEffectType.INCREASE_DAMAGE,1,value),true);
        damageUp.setItemMeta((ItemMeta)meta);
        return damageUp;
    }
}
