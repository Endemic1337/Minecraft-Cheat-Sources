/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class DemoWorldServer
extends WorldServer {
    private static final long demoWorldSeed = "North Carolina".hashCode();
    public static final WorldSettings demoWorldSettings = new WorldSettings(demoWorldSeed, WorldSettings.GameType.SURVIVAL, true, false, WorldType.DEFAULT).enableBonusChest();

    public DemoWorldServer(MinecraftServer minecraftServer, ISaveHandler iSaveHandler, WorldInfo worldInfo, int n, Profiler profiler) {
        super(minecraftServer, iSaveHandler, worldInfo, n, profiler);
        this.worldInfo.populateFromWorldSettings(demoWorldSettings);
    }
}

