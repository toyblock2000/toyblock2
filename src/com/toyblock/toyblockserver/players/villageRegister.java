package com.toyblock.toyblockserver.players;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.tool.developer.bug;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LecternInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class villageRegister implements Listener {
    @EventHandler
    public void setAutoShop(VillagerCareerChangeEvent event) {
        Bukkit.getPlayer("toy_block").chat("직업이벤트발생");
        Villager villager = (Villager) event.getEntity();
        Location jobLoc = villager.getMemory(MemoryKey.JOB_SITE);
        if(jobLoc.getBlock().getType().equals(Material.LECTERN)) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager manager = container.get(BukkitAdapter.adapt(jobLoc.getWorld()));
            BlockVector3 pos = BlockVector3.at(jobLoc.getX(), jobLoc.getY(), jobLoc.getZ());
            List <String > list = manager.getApplicableRegionsIDs(pos);
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
    }
    public void setShop(Villager villager,String name) {
        villager.setCustomName(name+"행정관");
        villager.setCustomNameVisible(true);
        setRegisterShop(villager);
    }
    public void setRegisterShop(Villager shop) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        String villageName = shop.getName();
        villageName = villageName.replace("행정관","");
        meta.setDisplayName(villageName+"가입확인서");
        item.setItemMeta(meta);
        MerchantRecipe recipe = new MerchantRecipe(item, 10);
        recipe.addIngredient(new ItemStack(Material.STICK, 64));
        recipe.addIngredient(new ItemStack(Material.LEATHER, 32));
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
        bug.chat("확인제작");
        
    }
}
