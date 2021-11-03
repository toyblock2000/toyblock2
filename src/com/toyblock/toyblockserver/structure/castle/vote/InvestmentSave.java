package com.toyblock.toyblockserver.structure.castle.vote;

import java.io.*;

public class InvestmentSave {
    private final File f = new File(getDataFolder(), "/data.txt");
    public void fileToMap(File f, HashMap<String, Integer> map) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String fileLine = null;
            while ((fileLine = reader.readLine()) != null) {

                String uuid = String.fromString(fileLine.split("\\|")[0]);
                String str = fileLine.split("\\|")[1];

                map.put(uuid, Integer.parseInt(str));
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
