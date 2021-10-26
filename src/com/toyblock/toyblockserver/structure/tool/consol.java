package com.toyblock.toyblockserver.structure.tool;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class consol {
    ConsoleCommandSender consol = Bukkit.getConsoleSender();
    public void send(String str) {
        consol.sendMessage(str);
    }
}
