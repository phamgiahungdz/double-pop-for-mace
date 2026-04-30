package com.example.addon;

import com.example.addon.modules.DoubleAttack;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("Double Pop Addon");
    public static final Category CATEGORY = Categories.Combat;
    public static final HudGroup HUD_GROUP = new HudGroup("DoublePop");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Double Attack...");

        // Register your module here
        Modules.get().add(new DoubleAttack());
    }

    @Override
    public void onRegisterCategories() {
        // Categories are registered automatically if using standard ones
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
