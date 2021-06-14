package com.thecloudyco.bw.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thecloudyco.bw.Starter;

import net.md_5.bungee.api.ChatColor;

public class ShutdownGameLogicCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + sender.getName() + " has stopped the game!");
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2f, 4f);
		}
		Starter.getGameCore().stopGame();
		return true;
	}
	
}
