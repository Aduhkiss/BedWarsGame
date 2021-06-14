package com.thecloudyco.bw.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thecloudyco.bw.Starter;

import net.md_5.bungee.api.ChatColor;

public class SetGenSpawnCommand implements CommandExecutor {
	//    /setgenspawn 
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must run this command in-game!");
			return false;
		}
		Player pl = (Player) sender;
		
		String generator = args[0];
		
		if(!Starter.getInstance().getConfig().getBoolean(pl.getWorld().getName() + ".active")) {
			sender.sendMessage(ChatColor.RED + "The map you are in is disabled from running the game!");
			return false;
		}
		
//		if(Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".generators." + generator + ".x") == 0) {
//			sender.sendMessage(ChatColor.RED + "The generator you requested, does not exist!");
//			return false;
//		}
		
		Starter.getInstance().getConfig().set(pl.getWorld().getName() + ".generators." + generator + ".x", pl.getLocation().getX());
		Starter.getInstance().getConfig().set(pl.getWorld().getName() + ".generators." + generator + ".y", pl.getLocation().getY());
		Starter.getInstance().getConfig().set(pl.getWorld().getName() + ".generators." + generator + ".z", pl.getLocation().getZ());
		
		sender.sendMessage(ChatColor.GREEN + "Done.");
		Starter.getInstance().saveConfig();
		return true;
	}
}
