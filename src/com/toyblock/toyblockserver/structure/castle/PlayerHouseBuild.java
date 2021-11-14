package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.LocBalance;
import com.toyblock.toyblockserver.structure.tool.consol;
import locate.WorldEditAPIController;
import locate.tool;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerHouseBuild implements Listener {
    @EventHandler
    public void houseBuild(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("집 실행중");
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"집 건설");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");

            String view = tool.getDirection(player);



            Location point = event.getPlayer().getTargetBlock(100).getLocation();
            Location loc = new LocBalance().balance(point);

            if (!PathLink.LinkCheck(loc,player,view)) {
                return;
            }


            if (new ExtendedHouse().houseCheck2x2(loc,view)) {
                WorldEditAPIController houseedit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/House", "world");
                houseedit.load("2x2house_test.schem");
                houseedit.paste(loc, new ExtendedHouse().viewInt(view));
                new ExtendedHouse().houseMapPut(loc,view);
                structureHashMap.Chunk.put(loc.getChunk(),"castle");
                consol.send("집완성");
            }


        }
        else {
            event.getPlayer().chat("실패");
            Location loc = player.getLocation();
            String locs = new LocationSave().locSave(loc);
            player.chat(locs);
            player.chat(""+ new LocationSave().x(locs));
            player.chat(""+ new LocationSave().y(locs));
            player.chat(""+ new LocationSave().z(locs));
            player.chat(""+ new LocationSave().world(locs));


        }
    }
}
