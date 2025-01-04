package org.teacon.gimmeman;

import net.neoforged.fml.common.Mod;

import java.util.List;

@Mod(GimmeMan.MOD_ID)
public final class GimmeMan {
    public static final String MOD_ID = "gimmeman";

    public GimmeMan() {

    }

    public static final List<String> modsToRemoveAdvancements = List.of("l2backpack");
}
