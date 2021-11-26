package com.toyblock.toyblockserver.tool.hashmap;

import com.toyblock.toyblockserver.structure.protect.LocationSave;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;

public class MapData extends JavaPlugin {


    public static void makeFile(File f) {
        if (!f.exists() || !f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void Protect_mapToFile(File f, HashMap<Location, String> map) {

        try {
            FileWriter writer = new FileWriter(f, false);
            LocationSave save = new LocationSave();
            for(Location location : map.keySet()){
                String str_loc = save.locSave(location);
                writer.write(str_loc+"|"+map.get(location)+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Protect_fileToMap(File f, HashMap<Location,String> map) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String fileLine = null;
            while ((fileLine = reader.readLine()) != null) {

                String key = (fileLine.split("\\|")[0]);
                String index = (fileLine.split("\\|")[1]);
                LocationSave save = new LocationSave();
                Location locs = save.locput(key);

                map.put(locs,index);

            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

}
