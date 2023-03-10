package net.minecraft.item;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSign
extends Item {
    public ItemSign() {
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (side == EnumFacing.DOWN) {
            return false;
        }
        if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid()) {
            return false;
        }
        if (!playerIn.canPlayerEdit(pos = pos.offset(side), side, stack)) {
            return false;
        }
        if (!Blocks.standing_sign.canPlaceBlockAt(worldIn, pos)) {
            return false;
        }
        if (worldIn.isRemote) {
            return true;
        }
        if (side == EnumFacing.UP) {
            int i = MathHelper.floor_double((double)((double)((playerIn.rotationYaw + 180.0f) * 16.0f / 360.0f) + 0.5)) & 0xF;
            worldIn.setBlockState(pos, Blocks.standing_sign.getDefaultState().withProperty((IProperty)BlockStandingSign.ROTATION, (Comparable)Integer.valueOf((int)i)), 3);
        } else {
            worldIn.setBlockState(pos, Blocks.wall_sign.getDefaultState().withProperty((IProperty)BlockWallSign.FACING, (Comparable)side), 3);
        }
        --stack.stackSize;
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntitySign && !ItemBlock.setTileEntityNBT((World)worldIn, (EntityPlayer)playerIn, (BlockPos)pos, (ItemStack)stack)) {
            playerIn.openEditSign((TileEntitySign)tileentity);
        }
        return true;
    }
}
