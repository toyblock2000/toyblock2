package com.toyblock.toyblockserver.tile;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.entity.Entity;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class dungeonTile implements Listener {
    @EventHandler
    public void test(PlayerInteractEvent event) {
        if(!event.getClickedBlock().getType().equals(Material.SPAWNER)) {
            return;
        }
        Bukkit.getPlayer("toy_block").chat("테스트 시작");
        Location loc = getTileCenter(event.getClickedBlock().getLocation());



        setDungeonSpawner(loc,getLocationRegionName(loc));
        Bukkit.getPlayer("toy_block").chat("테스트 통과");
    }
    public Location getTileCenter(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        x = x-(x%95)+48;
        z = z-(z%95)+48;
        Location newLoc = new Location(loc.getWorld(),x,63,z);
        return newLoc;
    }
    public void setDungeonSpawner(Location loc,String name) {

            Bukkit.getPlayer("toy_block").chat("트라이");
        Bukkit.getPlayer("toy_block").chat(""+loc.getNearbyLivingEntities(100).size());
                for(LivingEntity e : loc.getNearbyLivingEntities(100)) {

                    if(!e.getType().equals(EntityType.ARMOR_STAND)) {
                        continue;
                    }
                    if(e.getEquipment().getItemInMainHand().getType().isEmpty()) {
                        continue;
                    }
                    if(!e.getEquipment().getItemInMainHand().getType().equals(Material.SPAWNER)) {
                        continue;
                    }
                    if(!e.getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) {
                        continue;
                    }
                    if(!e.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals("spawner")) {
                        continue;
                    }
                    setSpawner(e.getLocation(),name,e.getEquipment().getHelmet().getItemMeta().getDisplayName());
                    e.remove();
                    e.getWorld().spawnEntity(e.getLocation(),EntityType.FIREWORK);
        }


    }
    public void setSpawner (Location loc, String name , String mobName) {
        SpawnerManager manager = new SpawnerManager(MythicBukkit.inst());
        manager.createSpawner(name,loc,mobName).ActivateSpawner();
        MythicBukkit.inst().getSpawnerManager().reload();
    }
    public void worldGuard(Location loc,String name) {
        Location loc1 = new Location(loc.getWorld(),loc.getX()-47,50,loc.getZ()-47);
        Location loc2 = new Location(loc.getWorld(),loc.getX()+47,163,loc.getZ()+47);

        BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
        BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());

        CuboidRegion regions = new CuboidRegion(pos1,pos2);

        ProtectedRegion region = new ProtectedCuboidRegion(name, pos1, pos2);
        region.setFlag(Flags.GREET_TITLE,"/"+name);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(loc1.getChunk().getWorld()));
        if(!(manager.getRegion(name) == null)) {
            manager.removeRegion(name);
            manager.addRegion(region);
        }
        else{
            manager.addRegion(region);
        }
        try {
            manager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }
    public String getLocationRegionName(Location loc) {
        BlockVector3 vector = BlockVector3.at(loc.getX(),loc.getY(),loc.getZ());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get((World) BukkitAdapter.adapt(loc.getWorld()));
        ApplicableRegionSet set = regions.getApplicableRegions(vector);
        String name = "0";
        if(set.getRegions().isEmpty()) {
            name = "0";
        }
        else{
            for(ProtectedRegion region : set) {
                name = region.getId();
                break;
            }
        }
        return name;



    }

}
