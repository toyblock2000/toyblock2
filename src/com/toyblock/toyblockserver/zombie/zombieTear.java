package com.toyblock.toyblockserver.zombie;

import com.sk89q.worldguard.bukkit.event.entity.SpawnEntityEvent;
import com.toyblock.toyblockserver.difficulty.Zombie;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import javax.sound.sampled.LineListener;
import javax.swing.text.html.parser.Entity;

public class zombieTear implements Listener {

    // 쉬움 2.5 보통 3 어려움 4.5


    public void spawnZombie(EntitySpawnEvent event) {
        bug.chat("좀비스폰");
        if(event.getEntity().getType().equals(EntityType.ZOMBIE)){
            bug.chat("데미지 적용");
            LivingEntity entity = (LivingEntity) event.getEntity();
            bug.chat("damage " + entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
            addAttack((LivingEntity) event.getEntity());
            testName((LivingEntity) event.getEntity());
        }

    }
    public void addAttack (LivingEntity zombie) {
    double random = 0;//(Math.random()*5);
    double damage = 4.5;//zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
    zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(random+damage);
    }
    public void testName (LivingEntity zombie) {
        int a = (int) zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        zombie.setCustomName(""+a);
        zombie.setCustomNameVisible(true);
        bug.chat("데미지"+a);
    }

}
