package com.example.addon;

import com.example.addon.modules.DoublePopMace;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class AddonTemplate extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Mace Addon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Double Pop Mace Addon...");

        // Modules
        Modules.get().add(new DoublePopMace());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
