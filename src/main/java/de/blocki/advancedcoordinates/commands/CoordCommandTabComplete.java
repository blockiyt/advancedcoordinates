package de.blocki.advancedcoordinates.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CoordCommandTabComplete implements TabCompleter {
    List<String> mainCMD = new ArrayList<>();
    List<String> secondCMD = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(sender instanceof Player) {
            Player psender = (Player) sender;

            if (mainCMD.isEmpty()) {
                mainCMD.add("send");
                mainCMD.add("broadcast");
            }

            if (secondCMD.isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    secondCMD.add(p.getName());
                }
            }

            List<String> result = new ArrayList<>();
            if (args.length == 1) {
                if(psender.hasPermission("advancedcoordinates.coords.broadcast") || psender.hasPermission("advancedcoordinates.*") || psender.isOp()) {
                    for (String a : mainCMD) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
                return result;
            } else if (args.length == 3) {
                if(psender.hasPermission("advancedcoordinates.coords.send") || psender.hasPermission("advancedcoordinates.*") || psender.isOp()) {
                    for (String a : secondCMD) {
                        if (args[1].equalsIgnoreCase("send")) {
                            if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
                                result.add(a);
                            }
                        }
                    }
                }
                return result;
            }
        }
        return null;
    }
}
