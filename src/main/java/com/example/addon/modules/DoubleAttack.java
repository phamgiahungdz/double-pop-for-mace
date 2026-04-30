package com.example.addon.modules;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;

// Example module
public class DoubleAttack extends Module {
    public DoubleAttack() {
        super(Categories.Combat, "DoubleAttack", "Attacks twice in one tick.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.world == null) return;

        // Target the entity in your crosshair
        Entity target = mc.crosshairTarget instanceof net.minecraft.util.hit.EntityHitResult ? 
            ((net.minecraft.util.hit.EntityHitResult) mc.crosshairTarget).getEntity() : null;

        if (target != null && mc.player.distanceTo(target) <= 4.5) { // Reach check
            // Send attack packet twice
            attackEntity(target);
            attackEntity(target);
        }
    }

    private void attackEntity(Entity target) {
        // Send packet directly to server to bypass client-side attack cooldown limitations
        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(target, mc.player.isSneaking()));
        
        // Optionally swing hand for visual effect
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
