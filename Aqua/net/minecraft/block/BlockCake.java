package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake
extends Block {
    public static final PropertyInteger BITES = PropertyInteger.create((String)"bites", (int)0, (int)6);

    protected BlockCake() {
        super(Material.cake);
        this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty)BITES, (Comparable)Integer.valueOf((int)0)));
        this.setTickRandomly(true);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        float f = 0.0625f;
        float f1 = (float)(1 + (Integer)worldIn.getBlockState(pos).getValue((IProperty)BITES) * 2) / 16.0f;
        float f2 = 0.5f;
        this.setBlockBounds(f1, 0.0f, f, 1.0f - f, f2, 1.0f - f);
    }

    public void setBlockBoundsForItemRender() {
        float f = 0.0625f;
        float f1 = 0.5f;
        this.setBlockBounds(f, 0.0f, f, 1.0f - f, f1, 1.0f - f);
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        float f = 0.0625f;
        float f1 = (float)(1 + (Integer)state.getValue((IProperty)BITES) * 2) / 16.0f;
        float f2 = 0.5f;
        return new AxisAlignedBB((double)((float)pos.getX() + f1), (double)pos.getY(), (double)((float)pos.getZ() + f), (double)((float)(pos.getX() + 1) - f), (double)((float)pos.getY() + f2), (double)((float)(pos.getZ() + 1) - f));
    }

    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
        return this.getCollisionBoundingBox(worldIn, pos, worldIn.getBlockState(pos));
    }

    public boolean isFullCube() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        this.eatCake(worldIn, pos, state, playerIn);
        return true;
    }

    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        this.eatCake(worldIn, pos, worldIn.getBlockState(pos), playerIn);
    }

    private void eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (player.canEat(false)) {
            player.triggerAchievement(StatList.field_181724_H);
            player.getFoodStats().addStats(2, 0.1f);
            int i = (Integer)state.getValue((IProperty)BITES);
            if (i < 6) {
                worldIn.setBlockState(pos, state.withProperty((IProperty)BITES, (Comparable)Integer.valueOf((int)(i + 1))), 3);
            } else {
                worldIn.setBlockToAir(pos);
            }
        }
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getBlock().getMaterial().isSolid();
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    public Item getItem(World worldIn, BlockPos pos) {
        return Items.cake;
    }

    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty((IProperty)BITES, (Comparable)Integer.valueOf((int)meta));
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue((IProperty)BITES);
    }

    protected BlockState createBlockState() {
        return new BlockState((Block)this, new IProperty[]{BITES});
    }

    public int getComparatorInputOverride(World worldIn, BlockPos pos) {
        return (7 - (Integer)worldIn.getBlockState(pos).getValue((IProperty)BITES)) * 2;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }
}
