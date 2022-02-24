package com.toyblock.toyblockserver.difficulty.entity.ai;

import com.sk89q.worldguard.bukkit.event.entity.SpawnEntityEvent;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
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
        if(!(event.getEntity().getType().equals(EntityType.ZOMBIE))) {
            return;
        }
        Zombie mob = (Zombie) event.getEntity();
        randomSpawn(mob);
    }
    public void randomSpawn(Zombie mob) {
        Random random = new Random();
        int value = random.nextInt(3);
        if(value==0) {
            zombieDamage_x2(mob);
            bug.chat("데미지");
        }
        if(value==1) {
            zombieHurt_x2(mob);
            bug.chat("체력");
        }
        if(value==2) {
            zombieMoveSpeed_x2(mob);
            bug.chat("속도");
        }
    }
}
