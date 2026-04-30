package com.example.addon;

import com.example.addon.modules.DoubleAttack; // Import your module
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        LOG.info("Initializing DoubleAttack Addon...");

        // Modules
        // This line registers your DoubleAttack module so it appears in the ClickGUI
        Modules.get().add(new DoubleAttack());
    }

    @Override
    public void onRegisterCategories() {
        // You can register custom categories here if needed
        // For now, we are using the default 'Combat' category
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
