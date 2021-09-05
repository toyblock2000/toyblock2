package locate;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class manager_spawn implements CommandExecutor{


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player)sender;
		if(args[0].equals("castle")) {
			   UUID UUID = player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_CRYSTAL).getUniqueId();
			   Entity manager =  player.getWorld().getEntity(UUID);
			   manager.setCustomName("frame_castle_manager");
			   manager.setCustomNameVisible(true);
			   
			   LivingEntity set = (LivingEntity) manager;
			   set.setAI(false);
			   
			return true;
		}
		
		return true; 
	}

}