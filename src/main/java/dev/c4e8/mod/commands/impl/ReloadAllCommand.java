package dev.c4e8.mod.commands.impl;

import dev.c4e8.C4E8;
import dev.c4e8.core.impl.CommandManager;
import dev.c4e8.mod.commands.Command;

import java.util.List;

public class ReloadAllCommand extends Command {

	public ReloadAllCommand() {
		super("reloadall", "");
	}

	@Override
	public void runCommand(String[] parameters) {
		CommandManager.sendChatMessage("§fReloading..");
		C4E8.unload();
        try {
            C4E8.load();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		return null;
	}
}
