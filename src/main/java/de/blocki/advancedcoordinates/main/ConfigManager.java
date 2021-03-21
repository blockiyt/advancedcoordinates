package de.blocki.advancedcoordinates.main;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File file = new File(Main.instance.getDataFolder(), "config.yml");
    private static YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);


    public static void set(String path, Object value){
        yamlConfiguration.set(path, value);
    }

    public static Object get(String path){ return yamlConfiguration.get(path); }

    public static void save(){
        try{
            yamlConfiguration.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
