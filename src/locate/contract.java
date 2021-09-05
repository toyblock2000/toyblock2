package locate;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class contract implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		ItemStack contract = new ItemStack(Material.GLOBE_BANNER_PATTERN);
		ItemMeta Meta = contract.getItemMeta();
		
		if (!(sender instanceof Player)) {
			return false;
		}



		ArrayList<String> lore = new ArrayList<String>();
		lore.add(0,"path");
		
			Player player = (Player) sender;
			Meta.setDisplayName("길 계약서");
			Meta.setLore(lore);
			contract.setItemMeta(Meta);
			player.getInventory().addItem(contract);
			Meta.setDisplayName("센터");
			lore.remove(0);
			lore.add(0,"castle");
			Meta.setLore(lore);
			contract.setItemMeta(Meta);
			player.getInventory().addItem(contract);
			
		return true; 
	}

	
	
}
