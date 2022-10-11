package com.toyblock.toyblockserver.players;

import com.toyblock.toyblockserver.mapList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class affiliation implements Listener {
    //@EventHandler
    public void put(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("소속가입")) {
            putAffiliation(event.getPlayer(), "마을소속");
            event.getPlayer().chat("가입완료"+getAffiliation(event.getPlayer()));
        }

    }


    public void putAffiliation (Player player , String affiliationName ) {
        mapList.AFFILIATION.put(player,affiliationName);
    }
    public String getAffiliation (Player player) {
        if(!mapList.AFFILIATION.containsKey(player)) {
            return "false";
        }
        return mapList.AFFILIATION.get(player);
    }
}
