package com.toyblock.toyblockserver.tool;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;


import org.bukkit.inventory.ItemStack;

import java.util.List;


public class tool {
	public static char XYZcanter(char XYZ) {
		if (XYZ=='5'||
				XYZ=='1'||
				XYZ=='2'||
				XYZ=='3'||
				XYZ=='4') {
				return '3';
			}

			else return '8';
	}	
	public static char XYZdowncanter(char XYZ) {
		if (XYZ=='0'||
				XYZ=='1'||
				XYZ=='2'||
				XYZ=='3'||
				XYZ=='4') {
				return '2';
			}

			else return '7';
	}
	@EventHandler
	public static int change_xyz (int xyz) {
		//X끝자리 변환
		String x1 = Integer.toString(xyz);
		StringBuilder newx = new StringBuilder(x1);
		int xlength = newx.length();
		char getx= newx.charAt(xlength-1);
		char check = newx.charAt(0);
		char putx;
		if(check=='-') {
			putx= XYZdowncanter(getx);
		}
		else {
			putx= XYZcanter(getx);
		}
		newx.setCharAt(xlength-1, putx);
		String newx1 = newx.toString();
		int change_xyz = Integer.parseInt(newx1);

		return change_xyz;
	}

	public static float loreFinder(ItemStack item, String findStr) {
		List lore = item.getItemMeta().getLore();
		for(int i = 0;i<lore.size();i++){
			String str = (String)lore.get(i);
			if(!(str.contains(findStr))) {
				continue;
			}
			return Integer.parseInt(str.replaceAll("[^0-9]", ""));
		}
		return 0f;
	}
	public static void loreChanger(ItemStack item,String changeLore , float changeValue) {
		if(!(item.getItemMeta().hasLore()) ) {
			return;
		}
		List<String> lore = item.getItemMeta().getLore();
		for(int i = 0;i<lore.size();i++) {
			String loreStr = lore.get(i);
			if (!(loreStr.contains(changeLore)) ) {
				continue;
			}
			if(!(loreStr.contains("+")) ) {
				continue;
			}
			String findLore;
			findLore = loreStr.substring(0,loreStr.indexOf("+"));
			findLore = findLore+changeValue;
			lore.remove(i);
			lore.add(i,findLore);
			return;
		}
	}
	public static String getDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 180) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
	public static void player_center_chat(Player player , String text) {
		player.sendMessage(ChatMessageType.ACTION_BAR,TextComponent.fromLegacyText(text));
	}
}
