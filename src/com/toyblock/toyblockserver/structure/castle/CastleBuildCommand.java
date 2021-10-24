package com.toyblock.toyblockserver.structure.castle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CastleBuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command , String s, String[] args) {

        Player player = (Player) sender;
        if (player.isOp()) {
            player.sendMessage("건물을 생성합니다");


        }


        return true;
    }



}
