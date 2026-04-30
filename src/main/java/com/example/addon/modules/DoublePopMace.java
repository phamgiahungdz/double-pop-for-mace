package com.example.addon.modules;

import meteordevelopment.meteorclient.events.entity.player.InteractEntityEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class DoubleAttack extends Module {
    public DoubleAttack() {
        super(Categories.Combat, "double-attack", "Attacks a second time in the same tick.");
    }

    @EventHandler
    private void onInteractEntity(InteractEntityEvent event) {
        // Ensure it is an attack interaction and the entity is valid
        if (event.action != InteractEntityEvent.Action.Attack || event.entity == null) return;

        // Send a manual attack packet immediately after the first one
        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(event.entity, mc.player.isSneaking()));
    }
}
