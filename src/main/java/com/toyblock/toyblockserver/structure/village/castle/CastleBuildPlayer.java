package com.toyblock.toyblockserver.structure.village.castle;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CastleBuildPlayer implements Listener {
    // @EventHandler
    public void playerItemUse(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        ItemStack key = event.getPlayer().getInventory().getItemInMainHand();
        if (key.getItemMeta().getDisplayName().equals("성 건설")) {
           Location point = event.getPlayer().getTargetBlock(5).getLocation();
            new CastleBuildCheck().checkOn(point,"castle_test");
        }
    }
}
