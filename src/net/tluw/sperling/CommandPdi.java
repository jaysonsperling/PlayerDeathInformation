package net.tluw.sperling;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPdi implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		switch (command.toString()) {
		case "reload":
			// reload the plugin
			return true;
		default:
			return false;
		}
	}
}