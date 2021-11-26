package com.toyblock.toyblockserver.tool;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.*;

public class RandomChest implements Listener {
   //@EventHandler
    public void setReplace(PlayerInteractEvent event ) {
        if(!(event.getClickedBlock().getType().equals(Material.CHEST))) {
            return;
        }
        event.getPlayer().sendMessage("!!");
        Chest chest = (Chest) event.getClickedBlock().getState();
        replaceChest(chest);
    }
    public void replaceChest(Chest chest) {
        Inventory inv = chest.getBlockInventory();
        if(!(chest.getCustomName().equals("랜덤"))) {
            return;
        }
        List<Integer> arr = new ArrayList<>();
        for(int i = 0;i<=27;i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        for(int i = 0;i<27;i++) {
            int random = arr.get(i);



            if(inv.getItem(random) == null) {
                continue;
            }

            if(!(inv.getItem(random)).getType().equals(Material.SHULKER_BOX)) {
                continue;
            }
            String name = inv.getItem(random).getItemMeta().getDisplayName();
            ItemStack shulker = inv.getItem(random);
            BlockStateMeta bsm = (BlockStateMeta) shulker.getItemMeta();
            ShulkerBox box = (ShulkerBox) bsm.getBlockState();
            if((box instanceof ShulkerBox)) {
                ItemStack[] items = box.getInventory().getContents();
                chest.setCustomName(name);
                chest.update();
                inv.setContents(items);
                Bukkit.getPlayer("Devil").sendMessage("name:"+name);
                return;


            }

        }
    }
}
