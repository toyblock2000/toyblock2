package com.toyblock.toyblockserver.quest;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.block.TileState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class questInventory implements Listener {
    @EventHandler
    public void openInv(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if(!event.getClickedBlock().getType().equals(Material.CHEST)) {
            return;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"quest");
        TileState state = (TileState) event.getClickedBlock().getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        if(!container.has(key, PersistentDataType.STRING)) {

            return;
        }
    }
    @EventHandler
    public void chestKey(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(!event.getBlock().getType().equals(Material.CHEST)) {
            player.chat(""+event.getBlock().getType());
            return;
        }

        String name = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(!name.equals("quest")) {
            return;
        }
        Chest chest = (Chest)event.getBlock().getState();
        player.chat("!11");
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"quest");
        TileState state = (TileState) event.getBlock().getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING,name);
        state.update();
        event.getPlayer().chat(name+"  keyset!");
        createInv(chest.getBlockInventory());
        mapList.QUEST.add(chest.getLocation());
    }
    @EventHandler
    public void questTime() {

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for(int i=0;i<mapList.QUEST.size();i++) {
                    Chest chest = (Chest)mapList.QUEST.get(i).getBlock().getState();
                    chest.getBlockInventory().clear();
                    createInv(chest.getBlockInventory());
                    chest.getLocation().getWorld().spawnEntity(chest.getLocation(),EntityType.FIREWORK);
                }

            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class),300,300);
    }
    public void createInv (Inventory inv) {

        questItem q = new questItem();

        inv.setItem(0,q.walls());
        inv.setItem(9,q.walls());
        inv.setItem(18,q.walls());

        inv.setItem(1,q.copper());
        inv.setItem(10,q.iron());
        inv.setItem(19,q.gold());

        inv.setItem(2,q.walls());
        inv.setItem(11,q.walls());
        inv.setItem(20,q.walls());

        inv.setItem(6,q.walls());
        inv.setItem(15,q.walls());
        inv.setItem(24,q.walls());

        inv.setItem(7,q.copper_reward());
        inv.setItem(16,q.iron_reward());
        inv.setItem(25,q.gold_reward());

        inv.setItem(8,q.walls());
        inv.setItem(17,q.walls());
        inv.setItem(26,q.walls());
        quest(inv);
    }
    public void quest(Inventory inv) {

        questItem q = new questItem();
        Random r = new Random();

        int copper_random = r.nextInt(100);
        if(copper_random < 70) {
            inv.setItem(3,q.quest_zombie_5());
        }
        if(copper_random < 50) {
            inv.setItem(4,q.quest_zombie_5());
        }
        if(copper_random < 30 ) {
            inv.setItem(5,q.quest_zombie_5());
        }


        int iron_random = r.nextInt(100);
        if(iron_random < 70) {
            inv.setItem(12,q.quest_zombie_5());
        }
        if(iron_random < 50) {
            inv.setItem(13,q.quest_zombie_5());
        }
        if(iron_random < 30 ) {
            inv.setItem(14,q.quest_zombie_5());
        }


        int gold_random = r.nextInt(100);
        if(gold_random < 70) {
            inv.setItem(21,q.quest_zombie_5());
        }
        if(gold_random < 50) {
            inv.setItem(22,q.quest_zombie_5());
        }
        if(gold_random < 30 ) {
            inv.setItem(23,q.quest_zombie_5());
        }


    }
}
