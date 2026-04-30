package com.example.addon.modules;

import meteordevelopment.meteorclient.events.entity.player.AttackEntityEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class DoubleAttack extends Module {
    public DoubleAttack() {
        super(Categories.Combat, "double-attack", "Attacks a second time in the same tick.");
    }

    @EventHandler
    private void onAttackEntity(AttackEntityEvent event) {
        if (event.entity == null || mc.player == null || mc.player.networkHandler == null) return;

        // Using networkHandler directly usually bypasses mapping issues
        mc.player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(event.entity, mc.player.isSprinting()));
    }
}
