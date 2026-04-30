package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.entity.Entity;

public class DoublePopMace extends Module {
    public DoublePopMace() {
        // Changed Category.COMBAT to Categories.Combat to match Meteor's Mojang mapping style
        super(Categories.Combat, "double-pop-mace", "Sends an extra attack packet in the same tick.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        if (event.packet instanceof ServerboundInteractPacket packet) {
            // In Mojang mappings, we use a specific way to get the entity
            // Using Meteor's 'mc' instance which is available in the Module class
            Entity entity = packet.getTarget(mc.level);
            
            if (entity != null) {
                // Send the extra attack packet
                mc.getConnection().send(ServerboundInteractPacket.createAttackPacket(entity, mc.player.isShiftKeyDown()));
            }
        }
    }
}
