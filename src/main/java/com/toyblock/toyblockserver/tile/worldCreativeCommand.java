package com.toyblock.toyblockserver.tile;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class worldCreativeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player player = (Player) sender;

        if (player.isOp()) {
            player.sendMessage("월드 생성 실행 권한확인");

            if (args.length == 0) {
                player.sendMessage("월드생성 실행");
                worldCreative world = new worldCreative();
                world.worldCreative(player);

            }
            else if (args[0].equals("random")) {
                tileSpawn spawn = new tileSpawn();
                spawn.randomTileCreate();
            }

        } else {
            player.sendMessage("명령어의 권한이 없습니다.");
        }


        return true;
    }

}
