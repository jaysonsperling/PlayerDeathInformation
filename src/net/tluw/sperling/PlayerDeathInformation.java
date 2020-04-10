package net.tluw.sperling;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDeathInformation extends JavaPlugin {
	
	// TODO
	// Move the prefixes into the config file
	
	public static FileConfiguration config;
	public static String messageConsolePrefix = "[PlayerDeathInformation] ";
	public static String messageInGamePrefix = "Death Info> ";
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		config = this.getConfig();
		
		getServer().getPluginManager().registerEvents(new ListenerDeathEvent(), this);
		
		this.getCommand("pdi").setExecutor(new CommandPdi());
		this.getCommand("deathback").setExecutor(new CommandDeathback());	
	}
	
	@Override
	public void onDisable() {
		// stubbed
	}
	
	public static void sendConsoleMessage(String consoleMessage) {
		Logger log = Bukkit.getLogger();
		log.info(messageConsolePrefix + consoleMessage);
	}
	
	public static void sendPlayerMessage(Player player, String playerMessage) {
		player.sendMessage(messageInGamePrefix + playerMessage);
	}
	
	public static void sendAdminMessage(String adminMessage) {
		// stubbed
	}
	
	public static void sendSenderMessage(CommandSender sender, String message) {
		sender.sendMessage(messageConsolePrefix + message);
	}
	
}
