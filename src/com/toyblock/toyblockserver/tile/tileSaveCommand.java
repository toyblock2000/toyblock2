package com.toyblock.toyblockserver.tile;

import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tileSaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player player = (Player) sender;

        if(player.isOp()) {
            player.sendMessage("타일 저장 실행..");

            if (args.length == 0) {
                player.sendMessage("이름을 입력해 주세요");
                return false;
            }
            String name = args[0];
            tileSave(player,name);
            player.sendMessage(name+"타일 저장 완료");

        }
        else {
            player.sendMessage("명령어의 권한이 없습니다.");
        }


        return true;

    }
    public void tileSave(Player player,String name) {
        Location loc = player.getLocation();
        Location min = new Location(player.getWorld(),loc.getX()+1,loc.getY()-1-12,loc.getZ()+1);
        Location max = new Location(player.getWorld(),loc.getX()+1+94,loc.getY()-1+100,loc.getZ()+1+94);
        Location point = new Location(player.getWorld(),loc.getX()+1+47,loc.getY()-1,loc.getZ()+1+47);
        WorldEditAPIController edit = new WorldEditAPIController("TILE","world");
        edit.copy(min,max,point);
        edit.save(name+".schem");

    }
}
