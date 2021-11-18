package com.toyblock.toyblockserver.structure.protect;

import hashmap.MapData;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.HashMap;

public class StructrueMap {
    //보호 범위
    public static HashMap<Location , String > protect = new HashMap<Location, String > () ;
    //길 위치
    public static HashMap<Location , String > Link = new HashMap<Location, String > () ;
    //청크
    public static HashMap<Chunk, String > Chunk = new HashMap<Chunk, String > () ;

    public static void mapSave() {
    }
    public static void mapLoad() {

    }
}
