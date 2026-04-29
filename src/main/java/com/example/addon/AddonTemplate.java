package your.package.name;

import your.package.name.modules.DoublePopMace;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class Addon extends MeteorAddon {
    // Custom category for your addon modules
    public static final Category CATEGORY = new Category("Custom Combat");

    @Override
    public void onInitialize() {
        // Registers your module so it appears in the ClickGUI
        Modules.get().add(new DoublePopMace());
    }

    @Override
    public void onRegisterCategories() {
        // Makes your custom category visible in the Meteor GUI
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        // Must match your source folder structure exactly
        return "your.package.name";
    }
}
