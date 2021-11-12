package com.toyblock.toyblockserver.buildframe;

import locate.WorldEditAPIController;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseBuildFrame implements Listener {
    @EventHandler
    public void spawnEntity(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"집프레임");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (!(castleBuildLore.get(0).equals(checkLore.get(0)))) {
            return;
        }
        World world = event.getPlayer().getWorld();
        Location loc = event.getClickedBlock().getLocation();
        Location setloc = new Location(world,loc.getBlockX()+0.5,loc.getBlockY()+1,loc.getBlockZ()+0.5);
        LivingEntity frame = (LivingEntity) world.spawnEntity(setloc, EntityType.ARMOR_STAND);
        frame.setCustomName("프레임");
        frame.setCustomNameVisible(true);
    }
    @EventHandler
    public void entityClick(PlayerInteractAtEntityEvent event) {
        LivingEntity entity = (LivingEntity) event.getRightClicked();
        if (!(entity.getCustomName().equals("프레임"))) {
            return;
        }
        Player player = event.getPlayer();
        event.getPlayer().teleport(entity.getLocation());
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "프레임");
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Save2X2");
        item.setItemMeta(meta);

        inv.setItem(0, item);
        inv.setItem(1, item);
        player.openInventory(inv);
    }
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        if(!(event.getView().getTitle().equals("프레임"))) {
            return;
        }
        if(!(event.getRawSlot()==0)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        Location loc = event.getWhoClicked().getLocation();
        Location save_loc = new Location(loc.getWorld(),loc.getX(),loc.getY()+4,loc.getZ()+6);
        Location point1 = new Location(loc.getWorld(),save_loc.getX()-3,save_loc.getY()-4,save_loc.getZ()-3);
        Location point2 = new Location(loc.getWorld(),save_loc.getX()+8,save_loc.getY()+5,save_loc.getZ()+8);
        loc.getWorld().spawnParticle(Particle.FLAME,point1.getX(),point1.getY(),point1.getZ(),10);
        loc.getWorld().spawnParticle(Particle.FLAME,point2.getX(),point2.getY(),point2.getZ(),10);
        WorldEditAPIController edit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/House","world");
        edit.copy(point1, point2,save_loc);
        edit.save(event.getWhoClicked().getInventory().getItemInMainHand().getItemMeta().getDisplayName()+".schem");
        player.chat(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
        player.chat(save_loc.getX()+"/"+save_loc.getY()+"/"+save_loc.getZ());
        player.chat(point1.getX()+"/"+point1.getY()+"/"+point1.getZ());
        player.chat(point2.getX()+"/"+point2.getY()+"/"+point2.getZ());
        player.closeInventory();
        player.updateInventory();
    }
}
