package com.toyblock.toyblockserver.difficulty.natural_spawn;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class natural_spawn implements Listener {

	@EventHandler
	public void random_zombie(EntitySpawnEvent event) {
		Random random = new Random();
		if (event.getEntityType().equals(EntityType.ZOMBIE)) {
			LivingEntity entity = (LivingEntity) event.getEntity();
			random_speed(entity,2);
			random_damage(entity,3);
			random_health(entity,4);
		}
	}
	public void random_speed(LivingEntity entity,double maxvalue) {
		int max = (int) (maxvalue*10);
		Random random = new Random();
		double a = random.nextInt(max);
		a = a/10;
		if( a < 1) {
			a = 1;
		}
		double value = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
		value = a * value ;
		entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(value);
	}
	public void random_damage(LivingEntity entity,double maxvalue) {
		int max = (int) (maxvalue*10);
		Random random = new Random();
		double a = random.nextInt(max);
		a = a/10;
		double value = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
		value = value * a;
		entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(value);
	}
	public void random_health(LivingEntity entity,double maxvalue) {
		int max = (int) (maxvalue*10);
		Random random = new Random();
		double a = random.nextInt(max);
		a = a/10;
		double value = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		value = value * a;
		entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
	}
}
