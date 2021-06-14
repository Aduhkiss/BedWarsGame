package com.thecloudyco.bw;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.thecloudyco.bw.cmd.SetGenSpawnCommand;
import com.thecloudyco.bw.cmd.SetTeamSpawnCommand;
import com.thecloudyco.bw.cmd.ShutdownGameLogicCommand;
import com.thecloudyco.bw.listener.PlayerJoinListener;

import net.md_5.bungee.api.ChatColor;

public class Starter extends JavaPlugin {
	
	private static Starter instance;
	
	//////////////////////////////////////////////////////
	private static BedWarsGame Game;
	private static int countdownTimer;
	//////////////////////////////////////////////////////
	
//	@Override
//	public void onLoad() {
//		
//		try {
//			FileUtils.deleteDirectory(new File("world"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		Path sourceDirectory = Paths.get("world_COPY");
//      Path targetDirectory = Paths.get("world");
//        
//        try {
//			Files.copy(sourceDirectory, targetDirectory);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("[ADUHKISS BEDWARS]: " + "Starting Plugin...");
		instance = this;
		
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		
		new WorldCreator("world_copy").createWorld();
		
		// Load any commands and/or event handlers that the game uses
		getCommand("setgenspawn").setExecutor(new SetGenSpawnCommand());
		getCommand("setteamspawn").setExecutor(new SetTeamSpawnCommand());
		getCommand("shutdowngamelogic").setExecutor(new ShutdownGameLogicCommand());
		
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		
		// Start keeping track of the players, check if we have enough to start the game
		
		Game = new BedWarsGame();
		
		// Also make sure, a game isnt actually running whenever we run the check
		new BukkitRunnable() {
			public void run() {
				if(Game.isGameRunning() == false && !Game.isGameStarting()) {
					// Check to see if we have enough players to start the game
					if(Bukkit.getOnlinePlayers().size() >= Game.getMinPlayersToStart()) {
						// Start the countdown to actually start a game
						Bukkit.broadcastMessage("We have enough players... starting game in 10 seconds...");
						Game.setGameStarting(true);
						countdownTimer = 10;
					}
				}
				
				if(Game.isGameStarting()) {
					if(countdownTimer <= 0) {
						// Start the game
						Game.setGameStarting(false);
						Game.startCountdown();
					} else {
						if(!getGameCore().isGameRunning() && (Bukkit.getOnlinePlayers().size() < Game.getMinPlayersToStart())) {
							// If for some reason a player logs out durring the start, we have to cancel it
							countdownTimer = 10;
							Game.setGameStarting(false);
							Bukkit.broadcastMessage(ChatColor.RED + "START CANCELLED! We need at least " + Game.getMinPlayersToStart() + " players to start!");
						} else {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting game in " + ChatColor.GOLD + countdownTimer + ChatColor.YELLOW + " seconds...");
							countdownTimer--;
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 0, 20);
		
		// Bukkit runnable for the message to let players know how many players are needed to start the game
		new BukkitRunnable() {
			public void run() {
				if(!getGameCore().isGameRunning() && (Bukkit.getOnlinePlayers().size() < Game.getMinPlayersToStart())) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						pl.sendMessage(ChatColor.RED + "" + Game.getMinPlayersToStart() + " players are required to start the game!");
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 0, (20 * 10));
		
		Bukkit.getLogger().info("[ADUHKISS BEDWARS]: " + "Disabling AutoSaving for every world...");
		// MAKE SURE THE WORLDS NEVER GET SAVED!!
		for(World w : Bukkit.getWorlds()) {
			w.setAutoSave(false);
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("[ADUHKISS BEDWARS]: " + "Shutting down BW Plugin...");
		instance = null;
		Bukkit.getLogger().info("[ADUHKISS BEDWARS]: " + "Shutting down Arcade Core...");
	}
	
	public static Starter getInstance() {
		return instance;
	}
	public static BedWarsGame getGameCore() {
		return Game;
	}

}
