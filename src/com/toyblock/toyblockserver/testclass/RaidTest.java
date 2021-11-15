package com.toyblock.toyblockserver.testclass;

import org.bukkit.Raid;
import org.bukkit.event.raid.RaidEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidTest {
    public void raidEvent(RaidEvent event) {
        event.getRaid().getLocation();
    }


}
