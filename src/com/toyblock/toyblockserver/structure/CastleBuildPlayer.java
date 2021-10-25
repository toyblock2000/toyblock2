package com.toyblock.toyblockserver.structure;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CastleBuildPlayer implements Listener {
    @EventHandler
    public void playerItemUse(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("실행중");
        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"성 건설");
        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");
           Location point = event.getPlayer().getTargetBlock(100).getLocation();
            new CastleBuildCheckUi().checkOn(event.getPlayer(),point);




        }
        else {
        }

    }
}
