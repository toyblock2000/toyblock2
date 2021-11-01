package locate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi;

public class castle_manager_class implements Listener{
	Location frame_castle_manager_loc;
	   Inventory frame_castle_manager_inv;
	   UUID UUID ;
	   public void  structrue_center_build_save_frame_manager_inventory () {
		    
		   //이름 안만들어짐
		Inventory inv = Bukkit.createInventory(null,InventoryType.HOPPER,"frame_castle_manager");
		ItemStack item = new ItemStack(Material.END_CRYSTAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("SAVE");
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("구조물 저장");
		meta.addEnchant(Enchantment.DURABILITY,1,true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		inv.setItem(2, item);
		frame_castle_manager_inv = inv;
		   
	   }
		@EventHandler
		public void castle_manager_damage_cancel(EntityDamageEvent event) {
			if(!(event.getEntityType().equals(EntityType.ENDER_CRYSTAL))) {
				return;
			}
			if(!(event.getEntity().getCustomName().equals("frame_castle_manager"))) {
				return;
			}
			event.setCancelled(true);
			this.UUID = event.getEntity().getUniqueId();
		}
	  // @EventHandler
	   public void  strictrue_center_build_save_frame_manager_click (PlayerInteractAtEntityEvent event) {
		   event.getPlayer().chat("??");
		  if (! 	 event.getPlayer().getTargetEntity(10).getCustomName().equals("frame_castle_manager")) {
			   event.getPlayer().chat("??!");
			  return;
		  }
		  if (! event.getPlayer().getTargetEntity(10).getType().equals(EntityType.ENDER_CRYSTAL)) {
			  return;
		  }
		  structrue_center_build_save_frame_manager_inventory ();
		  event.getPlayer().openInventory(frame_castle_manager_inv);
		  frame_castle_manager_loc = event.getPlayer().getTargetEntity(10).getLocation();
		   
	   }
	   @EventHandler
	   public void structrue_center_build_save_frame_manager_inventory_click(InventoryClickEvent event) {
		   if(!event.getView().getTitle().contains("frame_castle_manager")) {
			   return;
			   
		   }
		   if(event.getCurrentItem() == null) {
			   return;
		   }
		   if(event.getCurrentItem().getItemMeta()==null) {
			   return;
		   }
		   Player player = (Player) event.getWhoClicked();
		   event.setCancelled(true);
		   if(event.getClickedInventory().getType() == InventoryType.PLAYER) {
			   return;
		   }
		   if (event.getSlot() == 1) {
			   
			   Location loc = frame_castle_manager_loc;
			   Location save_loc = new Location(loc.getWorld(),loc.getX(),loc.getY()-1,loc.getZ()-55);
			   Location point1 = new Location(loc.getWorld(),save_loc.getX()-52,save_loc.getY()-50,save_loc.getZ()-52);
			   Location point2 = new Location(loc.getWorld(),save_loc.getX()+52,save_loc.getY()+200,save_loc.getZ()+52);
			   WorldEditAPIController edit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle","world");
			   edit.copy(point1, point2,save_loc);
			   edit.save(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()+".schem");
			   player.chat(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
			   player.chat(save_loc.getX()+"/"+save_loc.getY()+"/"+save_loc.getZ());
			   player.chat(point1.getX()+"/"+point1.getY()+"/"+point1.getZ());
			   player.chat(point2.getX()+"/"+point2.getY()+"/"+point2.getZ());
			   player.closeInventory();
			   player.updateInventory();
		   }
		   if(event.getSlot() ==2) {
			   player.getWorld().getEntity(UUID).remove();
			   player.closeInventory();
			   player.updateInventory();
		   }
		   
	   }
}
