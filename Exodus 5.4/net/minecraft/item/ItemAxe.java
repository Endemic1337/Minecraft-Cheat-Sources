/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemAxe
extends ItemTool {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet((Object[])new Block[]{Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder});

    @Override
    public float getStrVsBlock(ItemStack itemStack, Block block) {
        return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.getStrVsBlock(itemStack, block) : this.efficiencyOnProperMaterial;
    }

    protected ItemAxe(Item.ToolMaterial toolMaterial) {
        super(3.0f, toolMaterial, EFFECTIVE_ON);
    }
}

