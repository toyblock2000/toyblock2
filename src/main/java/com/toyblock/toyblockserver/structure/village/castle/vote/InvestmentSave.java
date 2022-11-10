package com.toyblock.toyblockserver.structure.village.castle.vote;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;

public class InvestmentSave extends JavaPlugin {
    private final File f = new File(getDataFolder(), "/data.txt");

    public void makeFile(File f) {
        if (!f.exists() || !f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fileToMap(File f, HashMap<String, Integer> map) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String fileLine = null;
            while ((fileLine = reader.readLine()) != null) {

                String uuid = fileLine.split("\\|")[0];
                String str = fileLine.split("\\|")[1];

                map.put(uuid, Integer.parseInt(str));
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
    public void mapToFile(File f, HashMap<String, Integer> map) {
        try {
            FileWriter writer = new FileWriter(f, false);
            for(String uuid : map.keySet()){
                writer.write(uuid+"|"+map.get(uuid)+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
