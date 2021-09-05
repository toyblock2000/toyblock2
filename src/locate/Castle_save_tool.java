package locate;

import org.bukkit.Location;

public class Castle_save_tool {

	Location save_block_loc;
	
	public Castle_save_tool(Location save_block_loc) {
		
		this.save_block_loc = save_block_loc;
	}
	public int npc_point_x(int loc) {
		int save_block_x = save_block_loc.getBlockX();

		
		int king_point_x =loc;
		
		int final_point_x = save_block_x - king_point_x;
		
		return final_point_x;
	}
	public int npc_point_y(int loc) {
		int save_block_y = save_block_loc.getBlockY();

		
		int king_point_y =loc;
		
		int final_point_y = save_block_y - king_point_y;
		
		return final_point_y;
	}
	public int npc_point_z(int loc) {
		int save_block_z = save_block_loc.getBlockZ();

		
		int king_point_z =loc;
		
		int final_point_z = save_block_z - king_point_z;
		
		return final_point_z;
	}
}
