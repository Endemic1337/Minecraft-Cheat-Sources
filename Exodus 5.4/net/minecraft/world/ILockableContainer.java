/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;

public interface ILockableContainer
extends IInventory,
IInteractionObject {
    public void setLockCode(LockCode var1);

    public boolean isLocked();

    public LockCode getLockCode();
}

