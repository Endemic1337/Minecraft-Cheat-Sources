/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public interface IHopper
extends IInventory {
    public double getYPos();

    public World getWorld();

    public double getXPos();

    public double getZPos();
}

