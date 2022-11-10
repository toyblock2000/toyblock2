package com.toyblock.toyblockserver.structure.village.house;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.tool.LocBalance;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import com.sk89q.worldguard.bukkit.BukkitPlayer;
import org.bukkit.inventory.MainHand;

import java.util.ArrayList;
import java.util.Collection;

public class landInfo implements Listener {
   // @EventHandler
    public void getInfo(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        boolean overlap;
        if (!(event.getHand() == EquipmentSlot.HAND)) {
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!hand.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!hand.getItemMeta().getDisplayName().equals("토지조사")) {
            return;
        }
        Location center = LocBalance.balance(event.getClickedBlock().getLocation());
        overlap = notOverlap(min(center),max(center));
        player.chat("빈땅"+overlap);


    }
        public boolean notOverlap(Location loc1,Location loc2) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get((World) BukkitAdapter.adapt(loc1.getWorld()));
            BlockVector3 min = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
            BlockVector3 max = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());
            ProtectedRegion region = new ProtectedCuboidRegion("overlap", min, max);
            ApplicableRegionSet set = regions.getApplicableRegions(region);
            if(set.getRegions().isEmpty()) {
                return true;
            }
            else {
                return false;
            }

        }
        public Location min(Location c) {
        Location min = new Location(c.getWorld(),c.getBlockX()-2,c.getBlockY()-4,c.getBlockZ()-2);
        return min;
        }
    public Location max(Location c) {
        Location max = new Location(c.getWorld(),c.getBlockX()+2,c.getBlockY()+10,c.getBlockZ()+2);
        return max;
    }


}
