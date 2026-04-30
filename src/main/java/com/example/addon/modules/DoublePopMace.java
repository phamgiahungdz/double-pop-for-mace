package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class DoublePopMace extends Module {
    public DoublePopMace() {
        super(Categories.Combat, "double-pop-mace", "Sends an extra attack packet in the same tick.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // Listen for the player's manual interaction packet
        if (event.packet instanceof ServerboundInteractPacket packet) {
            
            // In 1.21, we check the player's current hit result
            if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) mc.hitResult).getEntity();

                if (entity != null) {
                    // Modern 1.21 Mojang way to send an attack packet
                    // 'performInteraction' is often restricted, so we use 'attack'
                    mc.getConnection().send(ServerboundInteractPacket.createAttackPacket(
                        entity, 
                        mc.player.isShiftKeyDown()
                    ));
                }
            }
        }
    }
}
