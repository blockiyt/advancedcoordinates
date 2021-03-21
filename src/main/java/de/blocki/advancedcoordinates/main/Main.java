package de.blocki.advancedcoordinates.main;

import de.blocki.advancedcoordinates.commands.CoordCommand;
import de.blocki.advancedcoordinates.commands.CoordCommandTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String prefix;

    public static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("coords").setExecutor(new CoordCommand());
        getCommand("coords").setTabCompleter(new CoordCommandTabComplete());

        setDefaultConfig();
    }

    private void setDefaultConfig(){
        if(ConfigManager.get("prefix") == null){ ConfigManager.set("prefix", "&7[&6AC&7]"); }
        if(ConfigManager.get("round-location") == null){ ConfigManager.set("round-location", true); }
        ConfigManager.save();
        prefix = ConfigManager.get("prefix").toString().replace("&", "§") + " ";
    }

    private void setupMessagesConfig(){
        if(ConfigManager.get("coords") == null){ ConfigManager.set("coords", "Die Koordinaten des Spielers %PLAYERNAME% sind:"); }
        if(ConfigManager.get("coords_send") == null){ ConfigManager.set("coords_send", "Deine Koordinaten wurden an den Spieler %PLAYERNAME% gesendet!"); }
        if(ConfigManager.get("coords_not_self_send") == null){ ConfigManager.set("coords_not_self_send", "Du kannst dir selbst deine Koordinaten nicht schicken!"); }
        if(ConfigManager.get("player_not_found") == null){ ConfigManager.set("player_not_found", "Der Spieler wurde nicht gefunden!"); }
        if(ConfigManager.get("no_player_given") == null){ ConfigManager.set("no_player_given", "Du hast keinen Spieler angegeben!"); }
        ConfigManager.save();
    }
}
