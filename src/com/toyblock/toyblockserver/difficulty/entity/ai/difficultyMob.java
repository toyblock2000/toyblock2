package com.toyblock.toyblockserver.difficulty.entity.ai;

import com.sk89q.worldguard.bukkit.event.entity.SpawnEntityEvent;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import javax.swing.text.html.parser.Entity;
import java.util.Random;

public class difficultyMob implements Listener {
    public void zombieHurt_x2(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20*2);
        mob.setHealth(40);
    }
    public void zombieDamage_x2(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.5*2);
    }

    public void zombieMoveSpeed_x2(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23*2);
    }
    @EventHandler
    public void nomalSpawn(EntitySpawnEvent event) {
        if(event.getEntity().getType().equals(EntityType.ZOMBIE)) {
            Zombie mob = (Zombie) event.getEntity();
            zombieTear1(mob);
        }
        if(event.getEntity().getType().equals(EntityType.SKELETON)) {
            Skeleton mob = (Skeleton) event.getEntity();
            skeletonTear1(mob);
        }
        if(event.getEntity().getType().equals(EntityType.CREEPER)) {
            Creeper mob = (Creeper) event.getEntity();
            creeperTear1(mob);
        }

    }
    public void zombieTear1(Zombie mob) {
        mobList list = new mobList();
        Random random = new Random();
        int value = random.nextInt(4);
        if(value==1) {
            list.zombieDamage_x05(mob);
        }
        if(value==2) {
            list.zombieHurt_x05(mob);
        }
        if(value==3) {
            list.zombieMoveSpeed_x05(mob);
        }
    }
    public void skeletonTear1(Skeleton mob) {
        mobList list = new mobList();
        Random random = new Random();
        int value = random.nextInt(3);
        if(value==1) {
            list.skeletonHurt_x05(mob);
        }
        if(value==2) {
            list.skeletonMoveSpeed_x05(mob);
        }
    }
    public void creeperTear1(Creeper mob) {
        mobList list = new mobList();
        Random random = new Random();
        int value = random.nextInt(3);
        if(value==1) {
            list.creeperSlient(mob);
        }
        if(value==2) {
            list.creeperMoveSpeed_x05(mob);
        }
    }
}
