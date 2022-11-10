package com.toyblock.toyblockserver.structure.village.house;

import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.structureType;
import com.toyblock.toyblockserver.structure.village.path.PathLink;
import com.toyblock.toyblockserver.tool.LocBalance;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import com.toyblock.toyblockserver.tool.consol;
import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Create implements Listener {
    //@EventHandler
    public void houseBuild(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        String key = "집";

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if(!hand.getItemMeta().hasDisplayName()) {
            return;
        }
        if(!(hand.getItemMeta().getDisplayName().equals(key))) {
            return;
        }
        if(hand.getLore().isEmpty()) {
            return;
        }
        String nameKey = hand.getLore().get(0);
        String look = tool.getDirection(player);
        Location click = event.getPlayer().getTargetBlock(5).getLocation();
        Location loc = new LocBalance().balance(click);

        WorldEditAPIController houseedit = new WorldEditAPIController("NULL", "world");
        houseedit.load(nameKey+".schem");
        houseedit.paste(loc, new ExtendedHouse().viewInt(look));
    }

}

