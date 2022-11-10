package com.toyblock.toyblockserver.tile;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.sk89q.worldguard.bukkit.event.entity.SpawnEntityEvent;
import com.toyblock.toyblockserver.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;


public class tileOre implements Listener {
    World w;
    public tileOre (String worldString) {
        this.w = new BukkitWorld(Bukkit.getWorld(worldString));
    }
    public void orePattenAdd(RandomPattern pat,double coals , double irons, double coppers, double golds, double diamonds, double redstones, double lapiss, double emeralds) {
        BlockState coal = BukkitAdapter.adapt(Material.COAL_ORE.createBlockData());
        BlockState iron = BukkitAdapter.adapt(Material.IRON_ORE.createBlockData());
        BlockState copper = BukkitAdapter.adapt(Material.COPPER_ORE.createBlockData());
        BlockState gold = BukkitAdapter.adapt(Material.GOLD_ORE.createBlockData());
        BlockState diamond = BukkitAdapter.adapt(Material.DIAMOND_ORE.createBlockData());
        BlockState redstone = BukkitAdapter.adapt(Material.REDSTONE_ORE.createBlockData());
        BlockState lapis = BukkitAdapter.adapt(Material.LAPIS_ORE.createBlockData());
        BlockState emerald = BukkitAdapter.adapt(Material.EMERALD_ORE.createBlockData());

        BlockState stone = BukkitAdapter.adapt(Material.STONE.createBlockData());
        BlockState granite = BukkitAdapter.adapt(Material.GRANITE.createBlockData());
        BlockState diorite = BukkitAdapter.adapt(Material.DIORITE.createBlockData());
        pat.add(coal,coals);
        pat.add(iron, irons);
        pat.add(copper,coppers);
        pat.add(gold,golds);
        pat.add(diamond,diamonds);
        pat.add(redstone,redstones);
        pat.add(lapis,lapiss);
        pat.add(emerald,emeralds);
        pat.add(granite,0.05);
        pat.add(diorite,0.05);
        pat.add(stone,100-(coals+irons+coppers+golds+diamonds+redstones+lapiss+emeralds+0.1));
    }
    public void deepOrePattenAdd(RandomPattern pat,double coals , double irons, double coppers, double golds, double diamonds, double redstones, double lapiss, double emeralds) {
        BlockState coal = BukkitAdapter.adapt(Material.DEEPSLATE_COAL_ORE.createBlockData());
        BlockState iron = BukkitAdapter.adapt(Material.DEEPSLATE_IRON_ORE.createBlockData());
        BlockState copper = BukkitAdapter.adapt(Material.DEEPSLATE_COPPER_ORE.createBlockData());
        BlockState gold = BukkitAdapter.adapt(Material.DEEPSLATE_GOLD_ORE.createBlockData());
        BlockState diamond = BukkitAdapter.adapt(Material.DEEPSLATE_DIAMOND_ORE.createBlockData());
        BlockState redstone = BukkitAdapter.adapt(Material.DEEPSLATE_REDSTONE_ORE.createBlockData());
        BlockState lapis = BukkitAdapter.adapt(Material.DEEPSLATE_LAPIS_ORE.createBlockData());
        BlockState emerald = BukkitAdapter.adapt(Material.DEEPSLATE_EMERALD_ORE.createBlockData());

        BlockState stone = BukkitAdapter.adapt(Material.DEEPSLATE.createBlockData());
        BlockState andesite = BukkitAdapter.adapt(Material.ANDESITE.createBlockData());
        pat.add(coal,coals);
        pat.add(iron, irons);
        pat.add(copper,coppers);
        pat.add(gold,golds);
        pat.add(diamond,diamonds);
        pat.add(redstone,redstones);
        pat.add(lapis,lapiss);
        pat.add(emerald,emeralds);
        pat.add(andesite,0.05);
        pat.add(stone,100-(coals+irons+coppers+golds+diamonds+redstones+lapiss+emeralds+0.05));
    }
    public double p(double count) {
        return count*1.7;
    }

    public void oreCreate(Location tile) {

        Set<BaseBlock> deep = new HashSet<>();
        BlockType deepBlock = BlockTypes.DEEPSLATE;
        BlockState deepState = deepBlock.getDefaultState();
        deep.add(deepState.toBaseBlock());

        Set<BaseBlock> stone = new HashSet<>();
        BlockType stoneBlock = BlockTypes.STONE;
        BlockState stoneState = stoneBlock.getDefaultState();
        stone.add(stoneState.toBaseBlock());

        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
        int min_x = tile.getBlockX();
        int min_z = tile.getBlockZ();

        int max_x = min_x+95;
        int max_z = min_z+95;

        BlockVector3 min_deep = BlockVector3.at(min_x,0,min_z);
        BlockVector3 max_deep = BlockVector3.at(max_x,2,max_z);

        CuboidRegion region_deep = new CuboidRegion(w, min_deep,max_deep);
        RandomPattern pat_deep = new RandomPattern();


        BlockVector3 min_1 = BlockVector3.at(min_x,-64,min_z);
        BlockVector3 max_1 = BlockVector3.at(max_x,-48,max_z);

        CuboidRegion region_1 = new CuboidRegion(w, min_1,max_1);
        RandomPattern pat_1 = new RandomPattern();
        deepOrePattenAdd(pat_1,0,0.020,0,0.034,0.02,0.10,0.085,0);
        editSession.replaceBlocks(region_1,deep,pat_1);

        BlockVector3 min_2 = BlockVector3.at(min_x,-48,min_z);
        BlockVector3 max_2 = BlockVector3.at(max_x,-16,max_z);

        CuboidRegion region_2 = new CuboidRegion(w, min_2,max_2);
            RandomPattern pat_2 = new RandomPattern();
            deepOrePattenAdd(pat_2,0.030,0.030,0,0.034,0.03,0.017,0.017,0);
            editSession.replaceBlocks(region_2,deep,pat_2);


        BlockVector3 min_3_1 = BlockVector3.at(min_x,-16,min_z);
        BlockVector3 max_3_1 = BlockVector3.at(max_x,16,max_z);

        CuboidRegion region_3_1 = new CuboidRegion(w, min_3_1,max_3_1);
            RandomPattern pat_3_1 = new RandomPattern();
            deepOrePattenAdd(pat_3_1,0.090,0.140,0.068,0.034,0.02,0.017,0.170,0);
            editSession.replaceBlocks(region_3_1,deep,pat_3_1);

        BlockVector3 min_3_2 = BlockVector3.at(min_x,-16,min_z);
        BlockVector3 max_3_2 = BlockVector3.at(max_x,16,max_z);

        CuboidRegion region_3_2 = new CuboidRegion(w, min_3_2,max_3_2);
            RandomPattern pat_3_2 = new RandomPattern();
            orePattenAdd(pat_3_2,0.090,0.140,0.068,0.034,0.01,0.017,0.170,0);
            editSession.replaceBlocks(region_3_2,stone,pat_3_2);


        BlockVector3 min_4 = BlockVector3.at(min_x,16,min_z);
        BlockVector3 max_4 = BlockVector3.at(max_x,48,max_z);

        CuboidRegion region_4 = new CuboidRegion(w, min_4,max_4);
            RandomPattern pat_4 = new RandomPattern();
            orePattenAdd(pat_4,0.140,0.140,0.119,0.017,0,0,0,0);
            editSession.replaceBlocks(region_4,stone,pat_4);

        BlockVector3 min_5 = BlockVector3.at(min_x,48,min_z);
        BlockVector3 max_5 = BlockVector3.at(max_x,80,max_z);

        CuboidRegion region_5 = new CuboidRegion(w, min_5,max_5);
            RandomPattern pat_5 = new RandomPattern();
            orePattenAdd(pat_5,0.170,0.030,0.119,0,0,0,0,0);
            editSession.replaceBlocks(region_5,stone,pat_5);

        BlockVector3 min_6 = BlockVector3.at(min_x,80,min_z);
        BlockVector3 max_6 = BlockVector3.at(max_x,112,max_z);

        CuboidRegion region_6 = new CuboidRegion(w, min_6,max_6);
            RandomPattern pat_6 = new RandomPattern();
            orePattenAdd(pat_6,0.270,0.050,0.068,0,0,0,0,0);
            editSession.replaceBlocks(region_6,stone,pat_6);

        BlockVector3 min_7 = BlockVector3.at(min_x,112,min_z);
        BlockVector3 max_7 = BlockVector3.at(max_x,144,max_z);

        CuboidRegion region_7 = new CuboidRegion(w, min_7,max_7);
            RandomPattern pat_7 = new RandomPattern();
            orePattenAdd(pat_7,0.270,0.1,0,0,0,0,0,0);
            editSession.replaceBlocks(region_7,stone,pat_7);
        BlockVector3 min_8 = BlockVector3.at(min_x,144,min_z);
        BlockVector3 max_8 = BlockVector3.at(max_x,176,max_z);

        CuboidRegion region_8 = new CuboidRegion(w, min_8,max_8);
            RandomPattern pat_8 = new RandomPattern();
            orePattenAdd(pat_8,0.220,0.150,0,0,0,0,0,0);
            editSession.replaceBlocks(region_8,stone,pat_8);

        BlockVector3 min_9 = BlockVector3.at(min_x,176,min_z);
        BlockVector3 max_9 = BlockVector3.at(max_x,208,max_z);

        CuboidRegion region_9 = new CuboidRegion(w, min_9,max_9);
            RandomPattern pat_9 = new RandomPattern();
            orePattenAdd(pat_9,0.170,0.200,0,0,0,0,0,0);
            editSession.replaceBlocks(region_9,stone,pat_9);
        BlockVector3 min_10 = BlockVector3.at(min_x,208,min_z);
        BlockVector3 max_10 = BlockVector3.at(max_x,256,max_z);

        CuboidRegion region_10 = new CuboidRegion(w, min_10,max_10);
            RandomPattern pat_10 = new RandomPattern();
            orePattenAdd(pat_10,0.140,0.260,0,0,0,0,0,0);
            editSession.replaceBlocks(region_10,stone,pat_10);
        } catch(MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }

    }
}
