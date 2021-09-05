package locate;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class log  implements CommandExecutor{

	static HashMap<String , String > log_mode = new HashMap<String , String > () ;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player)sender;
		if(args[0].equals("on")) {
			log_mode.put(player.getName(),"on");
			player.chat("로그모드가 활성화 되었습니다");
			player.chat(log_mode.get(player.getName()));
			return true;
		}
		if(args[0].equals("off")) {
			log_mode.put(player.getName(),"off");
			player.chat("로그모드가 비 활성화 되었습니다");
			player.chat(log_mode.get(player.getName()));
			return true;
		}
		
		return true; 
	}

}
