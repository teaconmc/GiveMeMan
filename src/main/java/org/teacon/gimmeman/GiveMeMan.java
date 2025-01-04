package org.teacon.gimmeman;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(GiveMeMan.MOD_ID)
public final class GiveMeMan {
    public static final String MOD_ID = "gimmeman";

    public GiveMeMan(IEventBus eventBus) {

    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
