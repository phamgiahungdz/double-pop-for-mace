package com.example.addon;

import com.example.addon.modules.DoubleAttack;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        LOG.info("Initializing DoubleAttack Addon...");

        // Register the DoubleAttack module
        Modules.get().add(new DoubleAttack());
    }

    @Override
    public void onRegisterCategories() {
        // Using existing Categories.Combat
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
