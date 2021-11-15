package com.toyblock.toyblockserver.difficulty.item.weapon;

import com.toyblock.toyblockserver.structure.castle.Castle_Path;
import com.toyblock.toyblockserver.structure.castle.PathLink;
import com.toyblock.toyblockserver.structure.castle.PathRandomNpcBuild;
import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.LocBalance;
import com.toyblock.toyblockserver.structure.tool.consol;
import com.toyblock.toyblockserver.testclass.zombieAttack;
import locate.tool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class test implements Listener {
    @EventHandler
    public void zombietuch(PlayerInteractAtEntityEvent event) {
        event.getPlayer().sendMessage("실행 박살");
        if(!(event.getRightClicked().getType().equals(EntityType.ZOMBIE))) {
            event.getPlayer().sendMessage("노좀비");
            return;
        }
        event.getPlayer().sendMessage("좀비");
        LivingEntity entity = (LivingEntity) event.getRightClicked();
        LivingEntity creeper = (LivingEntity) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.CREEPER);
        if (entity.getType().equals(EntityType.ZOMBIE)) {
            zombieAttack zombie = new zombieAttack();
            zombie.zombieRiding(entity,creeper);
            zombie.zombieCreeper(entity,creeper);
            }
        }
    @EventHandler
    public void boom(EntityDamageEvent event) {
        Bukkit.getPlayer("toy_block").sendMessage("데미지"+event.getCause()+"엔티티"+event.getEntity().getType());
        if(event.getEntity().getCustomName().equals("이야")) {
            event.setCancelled(true);
            return;
        }
        if(event.getEntity().getCustomName().equals("boom")) {
            if(event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                Creeper creeper = (Creeper) event.getEntity();
                creeper.explode();
                return;
            }
        }
    }

    @EventHandler
    public void itemgive(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("물건 실행중");
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0,"테스트");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {
            StoneSword stone = new StoneSword();
            WoodenSword wood = new WoodenSword();
            player.chat("물건");
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
            player.chat("물건2");
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



        }
        else {
            player.chat("실패?");
            return;

        }


    }
    
}
