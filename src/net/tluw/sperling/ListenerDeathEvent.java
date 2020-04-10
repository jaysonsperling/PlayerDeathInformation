package net.tluw.sperling;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class ListenerDeathEvent implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = (Player)event.getEntity().getPlayer();
		String playerName = player.getName();
		Location playerLocation = player.getLocation();
		Double playerLocationX = playerLocation.getX();
		Double playerLocationY = playerLocation.getY();
		Double playerLocationZ = playerLocation.getZ();
		World playerWorld = player.getWorld();
				
		String deathCoordinates = "XYZ " + String.format("%.2f", playerLocationX) + "/" + String.format("%.2f", playerLocationY) + "/" + String.format("%.2f", playerLocationZ) + " in " + playerWorld.getName();
		
		Integer secondsToExecuteCommand = PlayerDeathInformation.config.getInt("deathback-available-seconds");
		
		Plugin plugin = player.getServer().getPluginManager().getPlugin("PlayerDeathInformation");
		
		player.setMetadata("playerDeathLocationX", new PluginMetadata(plugin, playerLocationX.toString()));
		player.setMetadata("playerDeathLocationY", new PluginMetadata(plugin, playerLocationY.toString()));
		player.setMetadata("playerDeathLocationZ", new PluginMetadata(plugin, playerLocationZ.toString()));
		player.setMetadata("playerDeathLocationWorld", new PluginMetadata(plugin, playerWorld.getName()));
		
		PlayerDeathInformation.sendConsoleMessage(playerName + " died at coordinates " + deathCoordinates);

		PlayerDeathInformation.sendAdminMessage(playerName + " died at coordinates " + deathCoordinates);
		
		if (player.hasPermission("playerdeathinformation.message.get.player")) {
			PlayerDeathInformation.sendPlayerMessage(player, "You died at " + deathCoordinates);
			if (player.hasPermission("playerdeathinformation.deathback.use")) {
				PlayerDeathInformation.sendPlayerMessage(player,"You have " + secondsToExecuteCommand + " seconds to use /deathback to get back to where you died!");
			}
		}
		
		// Make sure our metadata dealio actually worked correctly...
		List<MetadataValue> playerDeathLocationX = (List<MetadataValue>) player.getMetadata("playerDeathLocationX");
		List<MetadataValue> playerDeathLocationY = (List<MetadataValue>) player.getMetadata("playerDeathLocationY");
		List<MetadataValue> playerDeathLocationZ = (List<MetadataValue>) player.getMetadata("playerDeathLocationZ");
		List<MetadataValue> playerDeathLocationWorld = (List<MetadataValue>) player.getMetadata("playerDeathLocationWorld");
		PlayerDeathInformation.sendConsoleMessage(player.getName() + "'s death coordinates saved: " + playerDeathLocationWorld.get(0).value() + "@" + playerDeathLocationX.get(0).value() + "x" + playerDeathLocationY.get(0).value() + "x" + playerDeathLocationZ.get(0).value());
	}
}