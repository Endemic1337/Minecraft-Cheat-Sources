/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBook
extends Item {
    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return itemStack.stackSize == 1;
    }
}

