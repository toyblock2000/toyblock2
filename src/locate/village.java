package locate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class village {

	Location build_loc;
	public village(Location loc) {
	
		this.build_loc = loc;
		
	}
	public void castle_build(String Castle_name , String rotate , String name) {
		WorldEditAPIController edit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle", "world");
		edit.load(Castle_name);
		edit.paste(build_loc, 0);
		Main.villge_index_loc.put(build_loc,"Castle");
		int x_loc = tool.change_xyz(build_loc.getBlockX());
		int z_loc = tool.change_xyz(build_loc.getBlockZ());
		int y_start = build_loc.getBlockY();
		int x_start = x_loc+50;
		int z_start = z_loc+50;
		for(int x=-100; x<1; x=x+5)
		{
			for(int y=-200; y<200; y=y+10) {
				for(int z=-100; z<1; z=z+5) {
		
					Location loc = new Location(build_loc.getWorld(), x_start+x,y_start+y,z_start+z);
					Main.villge_index_loc.put(loc,"Castle_part");
				//	loc.getBlock().setType(Material.WHITE_WOOL);
				}
			}
		}
		for(int x=-100; x<1; x=x+5)
		{

				for(int z=-100; z<1; z=z+5) {
		
					Location loc = new Location(build_loc.getWorld(), x_start+x,y_start,z_start+z);
					Main.villge_index_loc.put(loc,"Castle");
					//loc.getBlock().setType(Material.RED_WOOL);
				}
		}
		
		
	}
}
