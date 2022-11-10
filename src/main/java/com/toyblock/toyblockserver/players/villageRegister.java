package com.toyblock.toyblockserver.players;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.tool.developer.bug;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LecternInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class villageRegister implements Listener {
    String pass = ChatColor.GREEN+""+ChatColor.BOLD+"가입 확인서";

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
        meta.setDisplayName(villageName+pass);
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
    //EventHandler
    public void PlayerPassUse(PlayerInteractEvent event) {

        if(!passItemUse(event)) {
            return;
        }
        Player player = event.getPlayer();
        titleChat(player,"마을 가입 완료!","");
        player.getInventory().remove(player.getInventory().getItemInMainHand());
        mapList.AFFILIATION.put(player,"test");
    }
    public static void actionBarChat(Player player,String str) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (str) );
    }
    public static void titleChat(Player player,String title,String subTitle) {
        player.sendTitle(title,subTitle);
    }
    public boolean itemUse(PlayerInteractEvent event,String itemName) {
        Player player = event.getPlayer();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return false;
        }
        ItemStack key = player.getInventory().getItemInMainHand();
        if (key.getItemMeta().getDisplayName().equals(itemName)) {
            return true;
        }
        return false;
    }
    public boolean passItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            return false;
        }
        ItemStack keyItem = player.getInventory().getItemInMainHand();
        String key = keyItem.getItemMeta().getDisplayName();
        if (key.contains(pass)) {
            return true;
        }
        return false;
    }
}
