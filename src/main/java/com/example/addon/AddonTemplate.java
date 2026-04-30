package com.example.addon;

import com.example.addon.modules.DoubleAttack; // Import your module class
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("Example Addon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Double Attack Addon...");

        // This line registers your custom module into the Meteor Client system
        Modules.get().add(new DoubleAttack());
    }

    @Override
    public void onRegisterCategories() {
        // Use this if you want to create a custom category instead of using Combat
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
