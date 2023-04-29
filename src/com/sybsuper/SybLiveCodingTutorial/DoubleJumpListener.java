package com.sybsuper.SybLiveCodingTutorial;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


public class DoubleJumpListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setFlySpeed(0);
        e.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        e.setCancelled(true);
        Vector velocity = new Vector(0,1,0);
        Vector lookVec = e.getPlayer().getLocation().getDirection();
        lookVec.setY(0);
        velocity.add(lookVec.multiply(0.5));
        e.getPlayer().setVelocity(velocity);
    }
}
