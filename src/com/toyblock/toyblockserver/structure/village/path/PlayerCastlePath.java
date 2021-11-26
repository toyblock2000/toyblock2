package com.toyblock.toyblockserver.structure.village.path;

import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.StructrueMap;
import com.toyblock.toyblockserver.tool.LocBalance;
import com.toyblock.toyblockserver.tool.consol;
import com.toyblock.toyblockserver.structure.village.castle.PathRandomNpcBuild;
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
        event.getPlayer().chat("길 실행중");
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("길 실행중");
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"길 건설");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");

            String view = tool.getDirection(player);



            Location point = event.getPlayer().getTargetBlock(100).getLocation();
            Location loc = new LocBalance().balance(point);

            if (!PathLink.LinkCheck(loc,player,view)) {
                return;
            }

            Castle_Path path = new Castle_Path(loc);
            path.build();
            StructrueMap.Chunk.put(loc.getChunk(),"castle");
            PathRandomNpcBuild random = new PathRandomNpcBuild(loc);
            random.pathBuild();
            consol.send("랜덤 길 추가 생성");


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