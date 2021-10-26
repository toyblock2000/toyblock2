package com.toyblock.toyblockserver.structure.tool;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class consol {
    static ConsoleCommandSender consol = Bukkit.getConsoleSender();
    public static void send(String str) {
        consol.sendMessage(str);
    }
}
