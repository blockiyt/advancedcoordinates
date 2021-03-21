package de.blocki.advancedcoordinates.commands;

import de.blocki.advancedcoordinates.main.ConfigManager;
import de.blocki.advancedcoordinates.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            double x = p.getLocation().getX();
            double y = p.getLocation().getY();
            double z = p.getLocation().getZ();
            if(Boolean.parseBoolean(ConfigManager.get("round-location").toString())){
                x = Math.round(x);
                y = Math.round(y);
                z = Math.round(z);
            }
            if(args.length >= 1){
                if(p.hasPermission("advancedcoordinates.coords.broadcast") || p.hasPermission("advancedcoordinates.*") || p.isOp()) {
                    if (args[0].equalsIgnoreCase("broadcast")) {
                        Bukkit.broadcastMessage(Main.prefix + "Die Koordinaten des Spielers " + p.getName() + " sind:\n" +
                                Main.prefix + "X: " + x + "\n" +
                                Main.prefix + "Y: " + y + "\n" +
                                Main.prefix + "Z: " + z);
                    }
                }
                if(args.length >= 2) {
                    if(p.hasPermission("advancedcoordinates.coords.send") || p.hasPermission("advancedcoordinates.*") || p.isOp()) {
                        if (args[0].equalsIgnoreCase("send")) {
                            if (args[1] != null) {
                                Player playtosend = Bukkit.getPlayer(args[1]);
                                if (playtosend != null) {
                                    if (!(playtosend == p)) {
                                        //player is nich null
                                        playtosend.sendMessage(Main.prefix + "Die Koordinaten des Spielers " + p.getName() + " sind:\n" +
                                                Main.prefix + "X: " + x + "\n" +
                                                Main.prefix + "Y: " + y + "\n" +
                                                Main.prefix + "Z: " + z);
                                        p.sendMessage(Main.prefix + "Deine Koordinaten wurden an den Spieler " + playtosend.getName() + " gesendet!");
                                    } else {
                                        p.sendMessage(Main.prefix + "Du kannst dir selbst deine Koordinaten nicht schicken!");
                                    }
                                } else {
                                    //player is null
                                    p.sendMessage(Main.prefix + "Der Spieler wurde nicht gefunden!");
                                }
                            }else {
                                p.sendMessage(Main.prefix + "Du hast keinen Spieler angegeben!");
                            }
                        }
                    }
                }
            }else{
                if(p.hasPermission("advancedcoordinates.coords") || p.hasPermission("advancedcoordinates.*") || p.isOp()) {
                    p.sendMessage(Main.prefix + "Die Koordinaten von dir sind:\n" +
                            Main.prefix + "X: " + x + "\n" +
                            Main.prefix + "Y: " + y + "\n" +
                            Main.prefix + "Z: " + z);
                }
            }


        }
        return false;
    }
}
