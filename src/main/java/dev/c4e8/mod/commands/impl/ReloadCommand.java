package dev.c4e8.mod.commands.impl;

import dev.c4e8.C4E8;
import dev.c4e8.core.impl.CommandManager;
import dev.c4e8.core.impl.ConfigManager;
import dev.c4e8.mod.commands.Command;

import java.util.List;

public class ReloadCommand extends Command {

	public ReloadCommand() {
		super("reload", "");
	}

	@Override
	public void runCommand(String[] parameters) {
		CommandManager.sendChatMessage("§fReloading..");
		C4E8.CONFIG = new ConfigManager();
		C4E8.PREFIX = C4E8.CONFIG.getString("prefix", C4E8.PREFIX);
		C4E8.CONFIG.loadSettings();
		C4E8.XRAY.read();
		C4E8.TRADE.read();
		C4E8.FRIEND.read();
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		return null;
	}
}
