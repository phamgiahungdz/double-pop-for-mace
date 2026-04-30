package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;

public class DoublePopMace extends Module {
    public DoublePopMace() {
        // Use Categories.Combat for 1.20+ Meteor addons
        super(Categories.Combat, "double-pop-mace", "Sends a second attack packet instantly on hit.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // Listen for the player's manual attack packet
        if (event.packet instanceof ServerboundInteractPacket packet) {
            
            // We use the player's current crosshair target to identify the entity
            // This is the most compatible way across different mapping versions
            if (mc.crosshairTarget instanceof EntityHitResult hitResult) {
                Entity entity = hitResult.getEntity();

                if (entity != null) {
                    // Send an identical attack packet in the same tick
                    mc.getConnection().send(ServerboundInteractPacket.performInteraction(
                        entity, 
                        mc.player.isShiftKeyDown(), 
                        ServerboundInteractPacket.InteractionAction.ATTACK
                    ));
                }
            }
        }
    }
}
