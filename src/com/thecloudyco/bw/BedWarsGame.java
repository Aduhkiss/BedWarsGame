package com.thecloudyco.bw;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.thecloudyco.arcade.Game;
import com.thecloudyco.arcade.util.AduhWorldUtil;
import com.thecloudyco.bw.common.BWTeam;

import net.md_5.bungee.api.ChatColor;

public class BedWarsGame extends Game {
	
	public BedWarsGame() {
		super(2, 16);
	}
	
	private HashMap<Player, BWTeam> Teams = new HashMap<>();

	@Override
	public void startGame() {
		
		// Do some wild randomization to pick the teams for players
		//TODO: Make it more likely that a player will get assigned to a team that has less players
		Random r = new Random();
		
		for(Player pl : this.getPlayers()) {
			
			new BukkitRunnable() {
				public void run() {
					Location spawn = null;
					int t = r.nextInt(3);
					if(t == 0) {
						setTeam(pl, BWTeam.RED);
						pl.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.RED + "RED TEAM" + ChatColor.YELLOW + "!");
						spawn = new Location(pl.getWorld(),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.red.x"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.red.y"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.red.z"));
					}
					if(t == 1) {
						setTeam(pl, BWTeam.BLUE);
						pl.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.BLUE + "BLUE TEAM" + ChatColor.YELLOW + "!");
						spawn = new Location(pl.getWorld(),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.blue.x"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.blue.y"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.blue.z"));
					}
					if(t == 2) {
						setTeam(pl, BWTeam.GREEN);
						pl.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.GREEN + "GREEN TEAM" + ChatColor.YELLOW + "!");
						spawn = new Location(pl.getWorld(),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.green.x"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.green.y"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.green.z"));
					}
					if(t == 3) {
						setTeam(pl, BWTeam.YELLOW);
						pl.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.YELLOW + "YELLOW TEAM" + ChatColor.YELLOW + "!");
						spawn = new Location(pl.getWorld(),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.yellow.x"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.yellow.y"),
								Starter.getInstance().getConfig().getDouble(pl.getWorld().getName() + ".spawnpoints.yellow.z"));
					}
					
					pl.teleport(spawn);
					pl.setGameMode(GameMode.SURVIVAL);
					pl.setHealth(20.00);
				}
			}.runTask(Starter.getInstance());
		}
		
		// Lookup the config values for the map we are playing on, grap all the coords for the spawnpoints
		// and send players to their spawns
		
		// Make runnables for all the generators in the game, to drop the items that they are in-charge of
		
		
		// IRON ORE
		new BukkitRunnable() {
			public void run() {
				if(!Starter.getGameCore().isGameRunning()) return;
				Location BLUE_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.z"));
				Location RED_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.z"));
				Location GREEN_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.z"));
				Location YELLOW_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.z"));
				
				ItemStack iron_ore = new ItemStack(Material.IRON_INGOT);
				
				new BukkitRunnable() {
					public void run() {
						getWorld().dropItemNaturally(BLUE_GEN, iron_ore);
						getWorld().dropItemNaturally(RED_GEN, iron_ore);
						getWorld().dropItemNaturally(GREEN_GEN, iron_ore);
						getWorld().dropItemNaturally(YELLOW_GEN, iron_ore);
					}
				}.runTask(Starter.getInstance());
			}
		}.runTaskTimerAsynchronously(Starter.getInstance(), 0, 20 * 1);
		
		// GOLD ORE
		new BukkitRunnable() {
			public void run() {
				if(!Starter.getGameCore().isGameRunning()) return;
				Location BLUE_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.blue_team_gen.z"));
				Location RED_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.red_team_gen.z"));
				Location GREEN_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.green_team_gen.z"));
				Location YELLOW_GEN = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.yellow_team_gen.z"));
				
				ItemStack gold_ore = new ItemStack(Material.GOLD_INGOT);

				
				new BukkitRunnable() {
					public void run() {
						getWorld().dropItemNaturally(BLUE_GEN, gold_ore);
						getWorld().dropItemNaturally(RED_GEN, gold_ore);
						getWorld().dropItemNaturally(GREEN_GEN, gold_ore);
						getWorld().dropItemNaturally(YELLOW_GEN, gold_ore);
					}
				}.runTask(Starter.getInstance());
			}
		}.runTaskTimerAsynchronously(Starter.getInstance(), 0, 20 * 10);

		// DIAMOND ORE
		new BukkitRunnable() {
			public void run() {
				if(!Starter.getGameCore().isGameRunning()) return;
				Location D_GEN_ONE = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_one.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_one.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_one.z"));
				Location D_GEN_TWO = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_two.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_two.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_two.z"));
				Location D_GEN_THREE = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_three.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_three.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_three.z"));
				Location D_GEN_FOUR = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_four.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_four.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.diamond_gen_four.z"));
				
				ItemStack diamond_ore = new ItemStack(Material.DIAMOND);

				
				new BukkitRunnable() {
					public void run() {
						getWorld().dropItemNaturally(D_GEN_ONE, diamond_ore);
						getWorld().dropItemNaturally(D_GEN_TWO, diamond_ore);
						getWorld().dropItemNaturally(D_GEN_THREE, diamond_ore);
						getWorld().dropItemNaturally(D_GEN_FOUR, diamond_ore);
					}
				}.runTask(Starter.getInstance());
			}
		}.runTaskTimerAsynchronously(Starter.getInstance(), 0, 20 * 45);
		
		// EMERALD ORE
		new BukkitRunnable() {
			public void run() {
				if(!Starter.getGameCore().isGameRunning()) return;
				Location E_GEN_ONE = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_one.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_one.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_one.z"));
				Location E_GEN_TWO = new Location(getWorld(),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_two.x"),
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_two.y") + 1,
						Starter.getInstance().getConfig().getDouble(getWorld().getName() + ".generators.emerald_gen_two.z"));
				
				ItemStack emerald_ore = new ItemStack(Material.EMERALD);
				
				new BukkitRunnable() {
					public void run() {
						getWorld().dropItemNaturally(E_GEN_ONE, emerald_ore);
						getWorld().dropItemNaturally(E_GEN_TWO, emerald_ore);
					}
				}.runTask(Starter.getInstance());
			}
		}.runTaskTimerAsynchronously(Starter.getInstance(), 0, 20 * 60);
	}
	
	private void setTeam(Player pl, BWTeam team) {
		Teams.put(pl, team);
	}

	@Override
	public void stopGame() {
		resetGame();
		isRunning = false;
	}
	
	@Override
	public void resetGame() {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			pl.kickPlayer(ChatColor.GOLD + "Game Restarting... [FORCE SEND TO HUB COMMAND]");
		}
		
		/////////////////////////////
		for(World w : Bukkit.getWorlds()) {
			Bukkit.unloadWorld(w, false);
		}
		
//		World delete = Bukkit.getWorld("world");
//		File deleteFolder = delete.getWorldFolder();
//		AduhWorldUtil.deleteWorld(deleteFolder);
//		
		// The world to copy
		World source = Bukkit.getWorld("world_copy");
		File sourceFolder = source.getWorldFolder();
		 
		// The world to overwrite when copying
		World target = Bukkit.getWorld("world");
		File targetFolder = target.getWorldFolder();
		
		AduhWorldUtil.copyWorld(sourceFolder, targetFolder);
        /////////////////////////////
	}

}
