package net.tluw.sperling;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPdi implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		PlayerDeathInformation.sendConsoleMessage("/pdi was executed");
		switch (arg3[0].toString().toLowerCase()) {
		case "info":
			// TODO: Permission check if sender is a player
			PlayerDeathInformation.sendSenderMessage(sender, "Current configuration:");
			PlayerDeathInformation.sendSenderMessage(sender, "  deathback-available-seconds: " + PlayerDeathInformation.config.getInt("deathback-available-seconds"));
			PlayerDeathInformation.sendSenderMessage(sender, "  deathback-god-seconds: " + PlayerDeathInformation.config.getInt("deathback-god-seconds"));
			PlayerDeathInformation.sendSenderMessage(sender, "  require-deathback-confirmation: " + PlayerDeathInformation.config.getBoolean("require-deathback-confirmation"));
			return true;
		case "reload":
			// TODO: Put this back in and make sure we do a permission check as well
			// Will also need to re-register the command in plugin.yml
		default:
			return false;
		}
	}
}
