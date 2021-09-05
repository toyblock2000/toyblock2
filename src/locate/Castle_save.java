
package locate;

import org.bukkit.Location;

public class Castle_save {
//50 칸 떨어져있음
	Location save_block_loc;
	int King_point_x;
	int King_point_y;
	int King_point_z;
	Castle_save_tool tool;
	//왕실
	//창고
	//저금
	//일반상점
	//게시판 출첵 이벤트 공지
	public Castle_save(Location save_block_loc) {
	this.save_block_loc = save_block_loc;
	this.tool = new Castle_save_tool(save_block_loc);
	}
	public void King_point(Location loc,String King) {
		int save_block_x = save_block_loc.getBlockX();
		int save_block_y = save_block_loc.getBlockY();
		int save_block_z = save_block_loc.getBlockZ();
		
		int king_point_x =loc.getBlockX();
		int king_point_y =loc.getBlockY();
		int king_point_z =loc.getBlockZ();
		
		int final_point_x = save_block_x - king_point_x;
		int final_point_y = save_block_y - king_point_y;
		int final_point_z = save_block_z - king_point_z;
		
		this.King_point_x = final_point_x;
		this.King_point_y = final_point_y;
		this.King_point_z = final_point_z;
	}
	public void royal_magistrate_point(Location loc) {
		//왕령 행정관
		int x = tool.npc_point_x(loc.getBlockX());
		int y = tool.npc_point_y(loc.getBlockY());
		int z = tool.npc_point_z(loc.getBlockZ());
		
		
		
	}
}

