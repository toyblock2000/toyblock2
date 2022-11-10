package com.toyblock.toyblockserver.npcjob;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpawnJob {
    @EventHandler
    public void setSecretary(VillagerCareerChangeEvent event) {

        Villager villager = (Villager) event.getEntity();
        Location jobLoc = villager.getMemory(MemoryKey.JOB_SITE);

        if(!jobLoc.getBlock().getType().equals(Material.LECTERN)) {
            return;
        }
        Location jobLocTear = new Location(jobLoc.getWorld(),jobLoc.getX(),jobLoc.getBlockY()-1,jobLoc.getBlockZ());
        if(!jobLocTear.getBlock().getType().equals(Material.IRON_BLOCK)) {
            return;
        }
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(jobLoc.getWorld()));
        BlockVector3 pos = BlockVector3.at(jobLoc.getX(), jobLoc.getY(), jobLoc.getZ());
        List<String > list = manager.getApplicableRegionsIDs(pos);
        if(list.isEmpty()) {
            return;
        }
        for(int i=0;i< list.size();i++){
            String str = list.get(i);
            if(mapList.VILLAGER_LIST.contains(str)) {
                setShop(villager,str);
                return;
            }
        }
    }
    public void setShop(Villager villager,String name) {
        villager.setCustomName(name+"서기관");
        villager.setCustomNameVisible(true);
        setSecretaryShop(villager);
    }
    public void setSecretaryShop(Villager shop) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        String villageName = shop.getName();
        villageName = villageName.replace("서기관","");
        item.setItemMeta(meta);

        MerchantRecipe recipe = new MerchantRecipe(item, 10);
        recipe.addIngredient(new ItemStack(Material.BREAD, 1));
        List<MerchantRecipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                shop.setRecipes(recipes);
                this.cancel();
            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class),5,5);

    }

}
