package com.toyblock.toyblockserver.difficulty.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.ItemStack;

public class NaturalSpawnChest implements Listener {
    @EventHandler
    public void onGeneration(ChunkPopulateEvent e) {
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        BlockState[] tileEntities = e.getChunk().getTileEntities();
        for(BlockState state : tileEntities) {
            if(state.getType() == Material.CHEST) {
                if(!(state instanceof Chest)) {
                    return;
                }
                Chest chest = (Chest) state.getBlock().getState();


//                chest.getBlockInventory().addItem(sword);
//                chest.getBlockInventory().setItem(10,sword);
            }
        }
    }
}
