package com.toyblock.toyblockserver.difficulty.entity;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class laserTower implements Listener {
   // @EventHandler
    public void laserTowerSpawn(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("수호자")) {
            return;
        }
        Location loc = event.getClickedBlock().getLocation();
        Location spawnLoc = new Location(loc.getWorld(), loc.getX(), loc.getBlockY() + 1, loc.getBlockZ());
        IronGolem entity = (IronGolem) loc.getWorld().spawnEntity(spawnLoc, EntityType.IRON_GOLEM);
        entity.isPlayerCreated();
        entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(500);
        entity.setCustomName("수호자");
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
    }
   // @EventHandler
    public void setLaserTower(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("타워")) {
            return;
        }
        Location loc = event.getClickedBlock().getLocation();
        mapList.LASERTOWER.add(loc);

    }
    @EventHandler
    public void laserTowerTime() {
        bug.chat("레이저 실행");
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for(int i=0;i<mapList.LASERTOWER.size();i++) {
                    for (Entity entitys : mapList.LASERTOWER.get(i).getNearbyLivingEntities(100)) {
                        LivingEntity entity = (LivingEntity) entitys;
                        if(entity.getType().equals(EntityType.PLAYER)) {
                            return;
                        }
                        Laser laser = null;
                        Location loc = new Location(mapList.LASERTOWER.get(i).getWorld(),mapList.LASERTOWER.get(i).getX()+0.5,mapList.LASERTOWER.get(i).getBlockY()+1.5,mapList.LASERTOWER.get(i).getBlockZ()+0.5);
                        try {
                            laser = new Laser.GuardianLaser(loc,entity.getLocation(),10,10);
                        } catch (ReflectiveOperationException e) {
                            e.printStackTrace();
                        }
                        laser.start(Main.getPlugin(Main.class));
                        try {
                            ((Laser.GuardianLaser) laser).attachEndEntity(entity);
                        } catch (ReflectiveOperationException e) {
                            e.printStackTrace();
                        }
                        towerKill(entity);
                        bug.chat("죽이기");
                        break;
                    }
                }
                bug.chat("포문 나오기 성공");
            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class),200,200);
    }
    public void towerKill(LivingEntity entity) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                entity.damage(50);
                entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_CAT_HISS,1,1);
                this.cancel();
            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class),200,200);

    }
    @EventHandler
    public void TowerLaser(EntityTargetLivingEntityEvent event) {
        bug.chat("이벤트 발생");
        if(!event.getEntity().getType().equals(EntityType.IRON_GOLEM)) {
            bug.chat("1");
            return;
        }

        if(!event.getEntity().getCustomName().equals("수호자")) {
            bug.chat("3");
            return;
        }
        bug.chat("통과");
        Laser laser = null;
        try {
           laser = new Laser.GuardianLaser(event.getEntity().getLocation(),event.getTarget().getLocation(),10,10);

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        laser.start(Main.getPlugin(Main.class));
        bug.chat("레이저실행");
        try {
            ((Laser.GuardianLaser) laser).attachEndEntity(event.getTarget());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        bug.chat("실행완료");
    }
}
