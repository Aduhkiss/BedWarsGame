package com.thecloudyco.bw.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.thecloudyco.bw.Starter;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onPlayerConnect(PlayerJoinEvent ev) {
		
		if(Starter.getGameCore().isGameRunning()) {
			ev.getPlayer().kickPlayer(ChatColor.RED + "This game is already running!? Please try again later!");
			return;
		}
		
		// When players join the game (when it hasnt started yet, lets toss them into the lobby spawn area, and give them starter items to select stuff)
		ev.getPlayer().teleport(new Location(ev.getPlayer().getWorld(),
				Starter.getInstance().getConfig().getDouble(ev.getPlayer().getWorld().getName() + ".spawnpoints.lobby.x"),
				Starter.getInstance().getConfig().getDouble(ev.getPlayer().getWorld().getName() + ".spawnpoints.lobby.y"),
				Starter.getInstance().getConfig().getDouble(ev.getPlayer().getWorld().getName() + ".spawnpoints.lobby.z")));
		
		ev.getPlayer().setGameMode(GameMode.ADVENTURE);
		ev.getPlayer().setHealth(20.00);
		
		Starter.getGameCore().setWorld(ev.getPlayer().getWorld());
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			ev.setJoinMessage("");
			pl.sendMessage(ChatColor.YELLOW + "" + pl.getName() + " has joined! (" + ChatColor.AQUA + Bukkit.getOnlinePlayers().size() + ChatColor.YELLOW + "/" + ChatColor.AQUA + Starter.getGameCore().getMaxPlayers() + ChatColor.YELLOW + ")");
		}
		
		//TODO: Give them starter items to select kits and perks (or a basic shop)
	}
	
	@EventHandler
	public void onPlayerConnect(PlayerQuitEvent ev) {
		ev.setQuitMessage("");
	}

}
