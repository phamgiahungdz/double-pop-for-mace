package com.example.addon.modules;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;

public class DoubleAttack extends Module {
    public DoubleAttack() {
        super(Categories.Combat, "DoubleAttack", "Attacks twice in one tick.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        // Ensure player and world are loaded
        if (mc.player == null || mc.level == null) return;

        // Get the entity you are currently looking at
        Entity target = null;
        if (mc.hitResult instanceof EntityHitResult entityHit) {
            target = entityHit.getEntity();
        }

        // If a target exists and is within reach (4.5 blocks)
        if (target != null && mc.player.distanceTo(target) <= 4.5) {
            attackEntity(target);
            attackEntity(target);
        }
    }

    private void attackEntity(Entity target) {
        if (mc.getConnection() == null) return;

        // Use the manual constructor to avoid "cannot find symbol" errors with the .attack() helper
        // This version works across most 1.20+ and 1.21 versions
        mc.getConnection().send(ServerboundInteractPacket.attack(target, mc.player.isShiftKeyDown()));
        
        // Swing hand visually
        mc.player.swing(InteractionHand.MAIN_HAND);
    }
}
