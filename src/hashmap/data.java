package hashmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class data extends JavaPlugin {

	
	public static void makeFile(File f) {
		if (!f.exists() || !f.isFile()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void mapToFile(File f, HashMap<String, String>  villageindex) {
		try {
			FileWriter writer = new FileWriter(f, false);
			for(String uuid : villageindex.keySet()){
				writer.write(uuid+"|"+villageindex.get(uuid)+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void fileToMap(File f, HashMap<String,String> villageindex) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String fileLine = null;
			while ((fileLine = reader.readLine()) != null) {

				String key = (fileLine.split("\\|")[0]);
				String index = (fileLine.split("\\|")[1]);


				villageindex.put(key,index);

			}
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		} catch (IOException e4) {
			e4.printStackTrace();
		}
	}
	
}
