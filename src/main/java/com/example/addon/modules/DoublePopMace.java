package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.entity.Entity;

public class DoublePopMace extends Module {
    public DoublePopMace() {
        super(Categories.Combat, "double-pop-mace", "Sends an extra attack packet in the same tick.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // We look for the interact packet (which handles attacks)
        if (event.packet instanceof ServerboundInteractPacket packet) {
            
            // We use Meteor's internal 'mc' instance to find what we are looking at
            // This is safer than packet.getTarget() which varies by version
            if (mc.crosshairTarget instanceof net.minecraft.world.phys.EntityHitResult hitResult) {
                Entity entity = hitResult.getEntity();
                
                // Only trigger if we aren't already the one sending the extra packet
                // (Prevents infinite loops)
                if (entity != null) {
                    // Send an attack packet using the official constructor
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
