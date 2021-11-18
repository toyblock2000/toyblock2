package com.toyblock.toyblockserver.structure.protect;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.HashMap;

public class StructrueMap {
    //보호 범위
    public static HashMap<Location , String > protect = new HashMap<Location, String > () ;
    public static HashMap<Location , String > Link = new HashMap<Location, String > () ;
    public static HashMap<Chunk, String > Chunk = new HashMap<Chunk, String > () ;

}
