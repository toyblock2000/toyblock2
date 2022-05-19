package com.toyblock.toyblockserver.structure.village.castle.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomHead {
    public  ItemStack getSkull(String base64) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        if (base64 == null || base64.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public ItemStack house (String id) {
        ItemStack item = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE2NDhlMzU3YTYwYzI1N2YzYmYzODQwZmE1MzJjZmQ2YWRjZTJmODhkYWI5ZmQwYzBmOTMwM2NmNWFlYjg4OSJ9fX0=");
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("집");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(id);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
