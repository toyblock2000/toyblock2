package com.toyblock.toyblockserver.tile;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
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
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.tool.View;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import com.toyblock.toyblockserver.tool.tool;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class tileSpawn implements Listener {
    World w =new BukkitWorld(Bukkit.getWorld("world"));

    @EventHandler
    public void testSpawn (SpawnerSpawnEvent event) {
        for ( Player player : event.getLocation().getNearbyPlayers(100)) {
            player.sendMessage(event.getEventName());
        }

    }
    @EventHandler
    public void testspawner(PlayerInteractEvent event) {
        if(!(event.getClickedBlock().getType().equals(Material.IRON_BLOCK))) {
            return;
        }
        mythicmobSpawnerControll con = new mythicmobSpawnerControll();
        con.test(event.getClickedBlock().getLocation());
        event.getPlayer().sendMessage("생성 성공");
    }
    @EventHandler
    public void standArms(EntitySpawnEvent event) {
        if(!(event.getEntity().getType().equals(EntityType.ARMOR_STAND))) {
            return;
        }
        ArmorStand stand = (ArmorStand) event.getEntity();
        stand.setArms(true);
    }

    @EventHandler
    public void testmob(EntitySpawnEvent event) {
        if(!(event.getEntity().getType().equals(EntityType.ARMOR_STAND))) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();
        if(!entity.getEquipment().getItemInMainHand().getType().equals(Material.SPAWNER)) {
            return;
        }
        test(entity.getLocation(),"teste",entity.getEquipment().getHelmet().getItemMeta().getDisplayName());

        event.getEntity().getWorld().spawnEntity(event.getLocation(),EntityType.FIREWORK);

    }
    public void test (Location loc,String name , String mobName) {
        SpawnerManager manager = new SpawnerManager(MythicBukkit.inst());
        manager.createSpawner(name,loc,mobName).ActivateSpawner();
        MythicBukkit.inst().getSpawnerManager().reload();
    }
    @EventHandler
    public void test(EntitySpawnEvent event) {
        if(event.getEntity().getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG)) {
            if(event.getEntity().getType().equals(EntityType.VILLAGER)) {
                ((LivingEntity)event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
                ((LivingEntity)event.getEntity()).setHealth(100);
            }
        }
    }
    public void spawnHandle(PlayerInteractEvent event) {
        if(event.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)) {
            Location loc = event.getClickedBlock().getLocation();
            int x = loc.getBlockX();
            int z = loc.getBlockZ();

            if(!((x%95)==0)) {
                event.getPlayer().chat("아님");
                return;
            }
            if(!((z%95)==0)) {
                event.getPlayer().chat("아님");
                return;
            }
            if(!(loc.getBlockY()==64)) {
                event.getPlayer().chat("아님");
                return;
            }
            event.getPlayer().chat("정답");
        }
        else{
            event.getPlayer().teleport(getTileCenter(event.getClickedBlock().getLocation()));
        }
    }
    public void spawn(Location spawnLoc,int a) {
        int random = (int) (Math.random() * 3) + 1;
        int rotate = (int) (Math.random() * 4) + 1;
        if(rotate == 1) {
            rotate = 0;
        }
        if(rotate == 2) {
            rotate = 90;
        }
        if(rotate == 3) {
            rotate = 180;
        }
        if(rotate == 4) {
            rotate = 270;
        }
        Location spawn = new Location(spawnLoc.getWorld(),1,100,1);
        for(Player players : spawnLoc.getNearbyPlayers(50)) {

            players.teleport(spawn);
        }
        for(LivingEntity entity  : spawnLoc.getNearbyLivingEntities(50)) {

            if(entity.getType().equals(EntityType.PLAYER)) {
                continue;
            }
            if(entity.getType().equals(EntityType.VILLAGER)) {
                Villager villager = (Villager) entity;
                if(villager.getMemory(MemoryKey.JOB_SITE) == null) {
                    entity.remove();
                    continue;
                }
                if(!villager.getMemory(MemoryKey.JOB_SITE).getBlock().equals(Material.BLAST_FURNACE)) {
                    entity.remove();
                    continue;
                }
                villager.teleport(Objects.requireNonNull(villager.getMemory(MemoryKey.JOB_SITE)));
                continue;
            }
            entity.remove();
        }
        WorldEditAPIController worldEdit = new WorldEditAPIController("TILE","world");
        addTileRemove(spawnLoc);

        //이지

        worldEdit.load(random+".schem");
        worldEdit.paste(spawnLoc,rotate);
        worldGuard(spawnLoc,a+"tile",a+"5타일");
        spawnEntity(spawnLoc);


    }
    public void addTileRemove(Location center) {
        Location loc = new Location(center.getWorld(),center.getX(),-64,center.getZ());
        Material type = center.getWorld().getBlockAt(loc).getType();
        if(type.isEmpty()){
            return;
        }
        else{
            if(type == Material.RED_CONCRETE) {
                Location loc1 = new Location(loc.getWorld(),loc.getX()-47,50,loc.getZ()-47);
                Location loc2 = new Location(loc.getWorld(),loc.getX()+47,0,loc.getZ()+47);
                BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
                BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());
                CuboidRegion regions = new CuboidRegion(pos1,pos2);
                setAir(regions);
            }
        }


    }

    public void randomTileCreate() {
        int random = (int) (Math.random() * 50) + 1;
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        BlockVector3 vector = manager.getRegion(random+"tile").getMinimumPoint();
        Location loc = new Location(Bukkit.getWorld("world"),vector.getX()+47,63,vector.getX()+47);
        spawn(loc,random);
        Bukkit.getPlayer("toy_block").chat("랜덤한 타일 생성"+random);

    }

    public void worldGuard(Location loc,String name, String tilename) {
        Location loc1 = new Location(loc.getWorld(),loc.getX()-47,50,loc.getZ()-47);
        Location loc2 = new Location(loc.getWorld(),loc.getX()+47,163,loc.getZ()+47);

        BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
        BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());

        CuboidRegion regions = new CuboidRegion(pos1,pos2);
        replaceOre(regions);

        ProtectedRegion region = new ProtectedCuboidRegion(name, pos1, pos2);
        //region.setFlag(Flags.GREET_TITLE,"\n"+ ChatColor.GREEN +tilename + ChatColor.RED + "@ 11월21일 제거대상입니다.");
        String str = "11@11";//region.getFlag(Flags.GREET_TITLE);
        int idx = str.indexOf("@");
        str = str.substring(0, idx);
        Bukkit.getConsoleSender().sendMessage(str);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(loc1.getChunk().getWorld()));
        if(!(manager.getRegion(tilename) == null)) {
            manager.removeRegion(tilename);
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
    public void spawnEntity(Location loc) {

        for(LivingEntity e : loc.getNearbyLivingEntities(100)) {

            if(!e.getType().equals(EntityType.ARMOR_STAND)) {
                continue;
            }
            if(e.getEquipment().getItemInMainHand().getType().isEmpty()) {
                continue;
            }
            if(!e.getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) {
                continue;
            }
            if(!e.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals("villager")) {
                continue;
            }
            e.remove();
            e.getWorld().spawnEntity(e.getLocation(),EntityType.VILLAGER);
            e.getLocation().getBlock().setType(Material.BLAST_FURNACE);
            String nw = getDirection(e);
            Directional data = (Directional)e.getLocation().getBlock().getBlockData();
            if(nw.equals("N")) {
                data.setFacing(BlockFace.NORTH);
            }
            if(nw.equals("W")) {
                data.setFacing(BlockFace.WEST);
            }
            if(nw.equals("S")) {
                data.setFacing(BlockFace.SOUTH);
            }
            if(nw.equals("E")) {
                data.setFacing(BlockFace.EAST);
            }
            e.getLocation().getBlock().setBlockData(data);
            NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"npc");
            TileState state = (TileState) e.getLocation().getBlock().getState();
            PersistentDataContainer container = state.getPersistentDataContainer();
            container.set(key, PersistentDataType.STRING,"npc");
            state.update();
            return;
        }


    }
    public static String getDirection(LivingEntity entity) {
        double rotation = (entity.getLocation().getYaw() - 180) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
    public Location getTileCenter(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        x = x-(x%95)+48;
        z = z-(z%95)+48;
        Location newLoc = new Location(loc.getWorld(),x,63,z);
        return newLoc;
    }
    public void deepOrePattenAdd(RandomPattern pat, double golds, double diamonds, double redstones, double lapiss, double emeralds) {
        BlockState gold = BukkitAdapter.adapt(Material.DEEPSLATE_GOLD_ORE.createBlockData());
        BlockState diamond = BukkitAdapter.adapt(Material.DEEPSLATE_DIAMOND_ORE.createBlockData());
        BlockState redstone = BukkitAdapter.adapt(Material.DEEPSLATE_REDSTONE_ORE.createBlockData());
        BlockState lapis = BukkitAdapter.adapt(Material.DEEPSLATE_LAPIS_ORE.createBlockData());
        BlockState emerald = BukkitAdapter.adapt(Material.DEEPSLATE_EMERALD_ORE.createBlockData());
        pat.add(gold,golds);
        pat.add(diamond,diamonds);
        pat.add(redstone,redstones);
        pat.add(lapis,lapiss);
        pat.add(emerald,emeralds);
    }
    public void replaceOre(CuboidRegion region) {
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
        Set<BaseBlock> ore = new HashSet<>();
        BlockType oreBlock = BlockTypes.RED_CONCRETE;
        BlockState orestate = oreBlock.getDefaultState();
        ore.add(orestate.toBaseBlock());
        RandomPattern pat = new RandomPattern();
        deepOrePattenAdd(pat,0.034,0.02,0.10,0.085,0);
        editSession.replaceBlocks(region,ore,pat);
        } catch(MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }

    public void setAir(CuboidRegion region) {
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {

            BlockType airBlock = BlockTypes.AIR;
            BlockState airstate = airBlock.getDefaultState();


           editSession.setBlocks((Region) region,airstate);
        } catch(MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }

}
