package com.toyblock.toyblockserver.system;

import com.toyblock.toyblockserver.structure.village.castle.item.CustomHead;
import com.toyblock.toyblockserver.tool.LocBalance;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class buildGui implements Listener {

   // @EventHandler
    public void spawn(PlayerInteractEvent event) {
       on(event.getClickedBlock().getLocation(), event.getPlayer());
       CustomHead head = new CustomHead();
       event.getPlayer().getInventory().addItem(head.house("test"));
    }
    public void on(Location loc, Player player) {
        LocBalance balance = new LocBalance();
        Location center = balance.balance(loc);
        Location spawn = new Location(center.getWorld(),center.getX()+0.5,center.getY()+1.5,center.getZ()+0.5);
        center.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR,spawn,0);
        Particle particle = Particle.END_ROD;
        int speed = 0;
        for(double i = 0; i <= 1;i = i+0.2) {


                Location border = new Location(center.getWorld(), center.getX() + i, center.getY() + 1.1, center.getZ());
            Location border2 = new Location(center.getWorld(), center.getX() + i, center.getY() + 1.1, center.getZ()+1);
            Location border3= new Location(center.getWorld(), center.getX() , center.getY() + 1.1, center.getZ()+i);
        Location border4 = new Location(center.getWorld(), center.getX() + 1, center.getY() + 1.1, center.getZ()+i);
                border.getWorld().spawnParticle(particle, border, 0);
            border.getWorld().spawnParticle(particle, border2, 0);
            border.getWorld().spawnParticle(particle, border3, 0);
            border.getWorld().spawnParticle(particle, border4, 0);

        }
        Location side = new Location(center.getWorld(),center.getX()-2,center.getY()-4,center.getZ()-2);
        Location side3 = new Location(center.getWorld(),center.getX()-2,center.getY()+1.1,center.getZ()-2);
        Location side2 = new Location(center.getWorld(),center.getX()-2,center.getY()+11,center.getZ()-2);
        for(double i = 0; i <= 5;i = i+0.2) {

            Location border = new Location(center.getWorld(), side3.getX() + i, side3.getY() , side3.getZ());
            Location border2 = new Location(center.getWorld(), side3.getX() + i, side3.getY() , side3.getZ()+5);
            Location border3 = new Location(center.getWorld(), side3.getX() , side3.getY() , side3.getZ()+i);
            Location border4 = new Location(center.getWorld(), side3.getX() + 5, side3.getY() , side3.getZ()+i);
            border.getWorld().spawnParticle(particle, border, speed);
            border.getWorld().spawnParticle(particle, border2, speed);
            border.getWorld().spawnParticle(particle, border3, speed);
            border.getWorld().spawnParticle(particle, border4, speed);
        }
        for(double i = 0; i <= 5;i = i+0.2) {

            Location border = new Location(center.getWorld(), side.getX() + i, side.getY() , side.getZ());
            Location border2 = new Location(center.getWorld(), side.getX() + i, side.getY() , side.getZ()+5);
            Location border3 = new Location(center.getWorld(), side.getX() , side.getY() , side.getZ()+i);
            Location border4 = new Location(center.getWorld(), side.getX() + 5, side.getY() , side.getZ()+i);
            border.getWorld().spawnParticle(particle, border, speed);
            border.getWorld().spawnParticle(particle, border2, speed);
            border.getWorld().spawnParticle(particle, border3, speed);
            border.getWorld().spawnParticle(particle, border4, speed);
        }
        for(double i = 0; i <= 5;i = i+0.2) {

            Location border = new Location(center.getWorld(), side2.getX() + i, side2.getY() , side2.getZ());
            Location border2 = new Location(center.getWorld(), side2.getX() + i, side2.getY() , side2.getZ()+5);
            Location border3 = new Location(center.getWorld(), side2.getX() , side2.getY() , side2.getZ()+i);
            Location border4 = new Location(center.getWorld(), side2.getX() + 5, side2.getY() , side2.getZ()+i);
            border.getWorld().spawnParticle(particle, border, speed);
            border.getWorld().spawnParticle(particle, border2, speed);
            border.getWorld().spawnParticle(particle, border3, speed);
            border.getWorld().spawnParticle(particle, border4, speed);
        }
        for(double i = 0; i <= 15;i = i+0.2) {
            Location border = new Location(center.getWorld(), side.getX() , side.getY()+i, side.getZ());
            Location border2 = new Location(center.getWorld(), side.getX() + 5, side.getY()+i, side.getZ());
            Location border3 = new Location(center.getWorld(), side.getX() , side.getY()+i, side.getZ()+5);
            Location border4 = new Location(center.getWorld(), side.getX() + 5, side.getY()+i, side.getZ()+5);
            border.getWorld().spawnParticle(particle, border, speed);
            border.getWorld().spawnParticle(particle, border2, speed);
            border.getWorld().spawnParticle(particle, border3, speed);
            border.getWorld().spawnParticle(particle, border4, speed);
        }


    }
}
