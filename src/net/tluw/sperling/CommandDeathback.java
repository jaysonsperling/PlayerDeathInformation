package net.tluw.sperling;

import org.bukkit.Statistic;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandDeathback implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			Integer ticksSinceLastDeath = player.getStatistic(Statistic.TIME_SINCE_DEATH);
			Integer ticksToExecuteCommand = PlayerDeathInformation.config.getInt("deathback-available-seconds") * 20;
			Integer ticksDamageImmunity = PlayerDeathInformation.config.getInt("deathback-god-seconds") * 20;
			
			if (player.hasPermission("playerdeathinformation.deathback.use")) {
				if (ticksSinceLastDeath < ticksToExecuteCommand) {
					
					List<MetadataValue> playerDeathLocationX = (List<MetadataValue>) player.getMetadata("playerDeathLocationX");
					List<MetadataValue> playerDeathLocationY = (List<MetadataValue>) player.getMetadata("playerDeathLocationY");
					List<MetadataValue> playerDeathLocationZ = (List<MetadataValue>) player.getMetadata("playerDeathLocationZ");
					List<MetadataValue> playerDeathLocationWorld = (List<MetadataValue>) player.getMetadata("playerDeathLocationWorld");
					
					if (playerDeathLocationX.isEmpty() || playerDeathLocationY.isEmpty() || playerDeathLocationY.isEmpty() || playerDeathLocationWorld.isEmpty()) {
						PlayerDeathInformation.sendPlayerMessage(player, "No death point to teleport you back to!");
						return false;
					}
					
					PlayerDeathInformation.sendPlayerMessage(player, "Teleporting you back to your death point!");

					player.teleport(new Location(Bukkit.getServer().getWorld(playerDeathLocationWorld.get(0).asString()), playerDeathLocationX.get(0).asDouble(), playerDeathLocationY.get(0).asDouble(), playerDeathLocationZ.get(0).asDouble()));
									
					if (player.hasPermission("playerdeathinformation.deathback.god")) {
						PlayerDeathInformation.sendPlayerMessage(player, "You're immune from damage for " + (ticksDamageImmunity / 20) + " seconds");
						player.setNoDamageTicks(ticksDamageImmunity);
						player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, ticksDamageImmunity, 0));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, ticksDamageImmunity, 0));
					}
					player.removeMetadata("playerDeathLocation", player.getServer().getPluginManager().getPlugin("PlayerDeathInformation"));
				} else {
					PlayerDeathInformation.sendPlayerMessage(player, "You haven't died in the past " + (ticksToExecuteCommand / 20) + " seconds!");
				}
			} else {
				PlayerDeathInformation.sendPlayerMessage(player, "You do not have permission to use this command!");
			}
		} else {
			sender.sendMessage("This command can only be ran by a player");
			return true;
		}
	return true;
	}	
}
