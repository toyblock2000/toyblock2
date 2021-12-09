package com.toyblock.toyblockserver.difficulty.item;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class test implements Listener {

    @EventHandler
    public void itemgive(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"테스트");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {
            StoneSword stone = new StoneSword();
            WoodenSword wood = new WoodenSword();
            player.getInventory().addItem(stone.StoneSword_Lv1());
            player.getInventory().addItem(stone.StoneSword_Lv2());
            player.getInventory().addItem(stone.StoneSword_Lv3());
            player.getInventory().addItem(stone.StoneSword_Lv4());
            player.getInventory().addItem(stone.StoneSword_Lv5());
            player.getInventory().addItem(stone.StoneSword_Lv6());
            player.getInventory().addItem(stone.StoneSword_Lv7());
            player.getInventory().addItem(stone.StoneSword_Lv8());
            player.getInventory().addItem(stone.StoneSword_Lv9());
            player.getInventory().addItem(stone.StoneSword_Lv10());
            player.getInventory().addItem(wood.woodenSword_Lv1());
            player.getInventory().addItem(wood.woodenSword_Lv2());
            player.getInventory().addItem(wood.woodenSword_Lv3());
            player.getInventory().addItem(wood.woodenSword_Lv4());
            player.getInventory().addItem(wood.woodenSword_Lv5());
            player.getInventory().addItem(wood.woodenSword_Lv6());
            player.getInventory().addItem(wood.woodenSword_Lv7());
            player.getInventory().addItem(wood.woodenSword_Lv8());
            player.getInventory().addItem(wood.woodenSword_Lv9());
            player.getInventory().addItem(wood.woodenSword_Lv10());
            player.getInventory().addItem(wood.upSword_Lv1());



        }
        else {
            player.chat("실패?");
            return;

        }


    }
    
}
