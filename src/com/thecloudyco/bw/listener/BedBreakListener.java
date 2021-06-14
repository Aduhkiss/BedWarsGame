package com.thecloudyco.bw.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BedBreakListener implements Listener {
	@EventHandler
	public void onBedBreak(BlockBreakEvent ev) {
		if(!(ev.getBlock().getType() == Material.RED_BED)) { return; }
		
		// Figure out which team this bed belongs to
	}
}
