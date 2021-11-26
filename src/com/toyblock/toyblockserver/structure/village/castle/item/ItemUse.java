package com.toyblock.toyblockserver.structure.village.castle.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemUse implements Listener {
    @EventHandler
    public void item_path_undo(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0, "길 건설");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.setCancelled(true);
            if (player.getInventory().getItemInMainHand().getAmount() == 1) {
                player.getInventory().remove(player.getInventory().getItemInMainHand());
            } else {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }


        }
    }

}
