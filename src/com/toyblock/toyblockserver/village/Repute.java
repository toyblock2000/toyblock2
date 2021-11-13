package com.toyblock.toyblockserver.village;

import com.destroystokyo.paper.entity.villager.ReputationType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Repute implements Listener {
  //  @EventHandler
    public void getRepute(PlayerInteractEvent event) {

        if(!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("평판"))) {

            return;
        }

        Villager npc = (Villager) event.getPlayer().getTargetEntity(100);
        npc.shakeHead();
        int a = 0;
        a = a-npc.getReputation(event.getPlayer().getUniqueId()).getReputation(ReputationType.MAJOR_NEGATIVE);
        a = a+npc.getReputation(event.getPlayer().getUniqueId()).getReputation(ReputationType.MAJOR_POSITIVE);
        a = a-npc.getReputation(event.getPlayer().getUniqueId()).getReputation(ReputationType.MINOR_NEGATIVE);
        a = a+npc.getReputation(event.getPlayer().getUniqueId()).getReputation(ReputationType.MINOR_POSITIVE);
        a = a+npc.getReputation(event.getPlayer().getUniqueId()).getReputation(ReputationType.TRADING);

        event.getPlayer().sendMessage("평판은"+a);

    }
}
