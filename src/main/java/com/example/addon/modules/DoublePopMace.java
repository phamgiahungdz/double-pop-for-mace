package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.entity.Entity;

public class DoublePopMace extends Module {
    public DoublePopMace() {
        // Using Category.COMBAT (Yarn name)
        super(Category.COMBAT, "double-pop-mace", "Sends an extra attack packet in the same tick.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // PlayerInteractEntityC2SPacket is the Yarn name for the attack packet
        if (event.packet instanceof PlayerInteractEntityC2SPacket packet) {
            
            // In 1.21 Yarn, mc.crosshairTarget is usually mc.crosshairTarget 
            // but we must check if it's an Entity hit
            if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) mc.crosshairTarget).getEntity();

                if (entity != null) {
                    // This creates a second attack packet for the same entity
                    mc.player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(
                        entity, 
                        mc.player.isSneaking()
                    ));
                }
            }
        }
    }
}
