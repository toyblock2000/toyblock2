package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.CastleBuildCheckUi;
import com.toyblock.toyblockserver.structure.tool.LocBalance;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerCastlePath implements Listener {
    @EventHandler
    public void playerPathBuild(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("실행중");

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"길 건설");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");

            Location point = event.getPlayer().getTargetBlock(100).getLocation();
            Location loc = new LocBalance().balance(point);
            Castle_Path path = new Castle_Path(loc, event.getPlayer());
            path.build();


        }
        else {
            event.getPlayer().chat("실패");
        }
        

    }
}
