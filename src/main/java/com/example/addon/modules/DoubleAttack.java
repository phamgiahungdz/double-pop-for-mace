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
        if (mc.player == null || mc.level == null) return;

        Entity target = null;
        if (mc.hitResult instanceof EntityHitResult entityHit) {
            target = entityHit.getEntity();
        }

        if (target != null && mc.player.distanceTo(target) <= 4.5) {
            attackEntity(target);
            attackEntity(target);
        }
    }

    private void attackEntity(Entity target) {
        // Updated for Mojang Mappings
        mc.getConnection().send(ServerboundInteractPacket.attack(target, mc.player.isShiftKeyDown()));
        mc.player.swing(InteractionHand.MAIN_HAND);
    }
}
