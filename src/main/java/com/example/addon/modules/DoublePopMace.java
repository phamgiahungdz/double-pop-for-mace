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
        // Categories.Combat is the correct 1.21 Mojang name
        super(Categories.Combat, "double-pop-mace", "Sends an extra attack packet in the same tick.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // ServerboundInteractPacket is the Mojang name for attack/interact
        if (event.packet instanceof ServerboundInteractPacket packet) {
            
            // In modern versions, 'crosshairTarget' is renamed to 'hitResult'
            if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) mc.hitResult).getEntity();

                if (entity != null) {
                    // Logic to send the second attack packet
                    // We use performInteraction to create the attack packet
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
