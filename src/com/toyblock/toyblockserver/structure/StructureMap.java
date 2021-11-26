package com.toyblock.toyblockserver.structure;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.HashMap;

public class StructureMap {
    //보호 이름
    public static HashMap<Location , String > protect = new HashMap<Location, String > () ;
    //연결 가능한지
    public static HashMap<Location , String > Link = new HashMap<Location, String > () ;
    //청크
    public static HashMap<Chunk, String > Chunk = new HashMap<Chunk, String > () ;

    public static void mapSave() {
    }
    public static void mapLoad() {

    }
}
