package com.toyblock.toyblockserver.structure.village.path;

import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.tool.LocBalance;
import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerCastlePath implements Listener {
    @EventHandler
    public void playerPathBuild(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"길 건설");
        if(!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore())) {
            return;
        }
        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            String view = tool.getDirection(player);

            Location point = event.getPlayer().getTargetBlock(5).getLocation();
            Location loc = new LocBalance().balance(point);

            if (!PathLink.LinkCheck(loc,view)) {
                return;
            }

            CastlePathBuild path = new CastlePathBuild(loc);
            path.build();
            StructureMap.Chunk.put(loc.getChunk(),"castle");


        }
        else {

            Location loc = player.getLocation();
            String locs = new LocationSave().locSave(loc);



        }
        

    }
}
