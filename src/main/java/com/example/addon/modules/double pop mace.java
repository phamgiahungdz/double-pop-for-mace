package your.package.name.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.entity.Entity;

public class MultiAttack extends Module {
    public MultiAttack() {
        // You can change Category.COMBAT to any existing category
        super(Category.COMBAT, "multi-attack", "Sends an extra attack packet on every hit.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        // Check if the outgoing packet is an attack packet
        if (event.packet instanceof PlayerInteractEntityC2SPacket packet) {
            
            // Extract the entity being attacked using an accessor or reflections if needed
            // For standard fabric/meteor development, accessors are usually available
            Entity entity = packet.getEntity(mc.world);
            
            if (entity != null) {
                // Send an identical attack packet immediately
                mc.player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(entity, mc.player.isSneaking()));
            }
        }
    }
}
