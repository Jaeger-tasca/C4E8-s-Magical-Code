package dev.c4e8.mod.commands.impl;

import dev.c4e8.C4E8;
import dev.c4e8.core.Manager;
import dev.c4e8.core.impl.CommandManager;
import dev.c4e8.core.impl.ConfigManager;
import dev.c4e8.mod.commands.Command;

import java.util.List;

public class LoadCommand extends Command {

	public LoadCommand() {
		super("load", "[config]");
	}

	@Override
	public void runCommand(String[] parameters) {
		if (parameters.length == 0) {
			sendUsage();
			return;
		}
		CommandManager.sendChatMessage("§fLoading..");
		ConfigManager.options = Manager.getFile(parameters[0] + ".cfg");
		C4E8.CONFIG = new ConfigManager();
		C4E8.PREFIX = C4E8.CONFIG.getString("prefix", C4E8.PREFIX);
		C4E8.CONFIG.loadSettings();
        ConfigManager.options = Manager.getFile("options.txt");
		C4E8.save();
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		return null;
	}
}
