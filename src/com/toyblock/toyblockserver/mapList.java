package com.toyblock.toyblockserver;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class mapList {
    //키 : toy_Block__energy 값 : 100
    public static HashMap<String , Float > ENERGY = new HashMap<String , Float > () ;
    public static HashMap<String , Boolean> ENERGY_REGEN = new HashMap<String , Boolean> () ;

    public static HashMap<String , Integer > COUNT = new HashMap<String , Integer>() ;

    public static HashMap<Player, Integer > TAX = new HashMap<Player , Integer>() ;
    public static HashMap<Player, String > BELONG = new HashMap<Player , String> () ;

    public static HashMap<Player, String > AFFILIATION = new HashMap<Player,String>();

    public static HashMap<UUID, String > VILLAGER_AFFILIATION = new HashMap<UUID,String>();

    public static ArrayList<String> VILLAGER_LIST = new ArrayList<>();
    public static ArrayList<Location> LASERTOWER = new ArrayList<>();

}
