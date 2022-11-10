package com.toyblock.toyblockserver.structure.village.house;

import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.structure.structureType;
import com.toyblock.toyblockserver.tool.LocBalance;
import com.toyblock.toyblockserver.tool.consol;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import com.toyblock.toyblockserver.structure.village.house.ExtendedHouse;
import com.toyblock.toyblockserver.structure.village.path.PathLink;
import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerHouseBuild implements Listener {
   //@EventHandler
    public void houseBuild(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"집 건설");
        if(!(player.getInventory().getItemInMainHand().getItemMeta().hasLore())) {
            return;
        }
        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");

            String view = tool.getDirection(player);



            Location point = event.getPlayer().getTargetBlock(100).getLocation();
            Location loc = new LocBalance().balance(point);

            if (!PathLink.LinkCheck(loc,view)) {
                event.getPlayer().chat("생성미완료");
                return;
            }


            if (new ExtendedHouse().houseCheck2x2(loc,view)) {
                WorldEditAPIController houseedit = new WorldEditAPIController(structureType.VILLAGE_2X2HOUSE.name(), "world");
                houseedit.load("2x2house_test.schem");
                houseedit.paste(loc, new ExtendedHouse().viewInt(view));
                new ExtendedHouse().houseMapPut(loc,view);
                StructureMap.Chunk.put(loc.getChunk(),"castle");
                consol.send("집완성");
                event.getPlayer().chat("집 건섫");
            }


        }
        else {

            Location loc = player.getLocation();
            String locs = new LocationSave().locSave(loc);



        }
    }
}
