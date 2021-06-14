package com.thecloudyco.arcade;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class Game {
	
	private World world;
	
	private int minPlayersToStart;
	private int maxPlayers;
	
	protected boolean isRunning;
	protected boolean isStarting;
	
	private ArrayList<Player> Players = new ArrayList<Player>();
	private ArrayList<Player> Spectators = new ArrayList<Player>();
	
	public Game(int minPlayersToStart, int maxPlayers) {
		this.minPlayersToStart = minPlayersToStart;
		this.maxPlayers = maxPlayers;
		isRunning = false;
	}
	
	public int getMinPlayersToStart() {
		return minPlayersToStart;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public boolean isGameRunning() {
		return isRunning;
	}
	
	public boolean isGameStarting() {
		return isStarting;
	}
	
	public void setGameStarting(boolean a) {
		isStarting = a;
	}
	
	public ArrayList<Player> getPlayers() {
		return Players;
	}
	
	public ArrayList<Player> getSpectators() {
		return Spectators;
	}
	
	public void addPlayer(Player player) {
		Players.add(player);
	}
	public void removePlayer(Player player) {
		Players.remove(player);
	}
	
	public void addSpectator(Player spec) {
		Spectators.add(spec);
	}
	public void removeSpectator(Player spec) {
		Spectators.remove(spec);
	}
	
	public abstract void startGame();
	public abstract void stopGame();
	public abstract void resetGame();
	
	public void startCountdown() {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			addPlayer(pl);
		}
		startGame();
		isRunning = true;
	}
	
	public void setWorld(World w) {
		this.world = w;
	}
	public World getWorld() {
		return world;
	}
	
}
