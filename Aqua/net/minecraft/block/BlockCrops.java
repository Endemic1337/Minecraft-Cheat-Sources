package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops
extends BlockBush
implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create((String)"age", (int)0, (int)7);

    protected BlockCrops() {
        this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, (Comparable)Integer.valueOf((int)0)));
        this.setTickRandomly(true);
        float f = 0.5f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
        this.setCreativeTab(null);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
    }

    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.farmland;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        float f;
        int i;
        super.updateTick(worldIn, pos, state, rand);
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && (i = ((Integer)state.getValue((IProperty)AGE)).intValue()) < 7 && rand.nextInt((int)(25.0f / (f = BlockCrops.getGrowthChance((Block)this, worldIn, pos))) + 1) == 0) {
            worldIn.setBlockState(pos, state.withProperty((IProperty)AGE, (Comparable)Integer.valueOf((int)(i + 1))), 2);
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        int i = (Integer)state.getValue((IProperty)AGE) + MathHelper.getRandomIntegerInRange((Random)worldIn.rand, (int)2, (int)5);
        if (i > 7) {
            i = 7;
        }
        worldIn.setBlockState(pos, state.withProperty((IProperty)AGE, (Comparable)Integer.valueOf((int)i)), 2);
    }

    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {
        boolean flag1;
        float f = 1.0f;
        BlockPos blockpos = pos.down();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0f;
                IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (iblockstate.getBlock() == Blocks.farmland) {
                    f1 = 1.0f;
                    if ((Integer)iblockstate.getValue((IProperty)BlockFarmland.MOISTURE) > 0) {
                        f1 = 3.0f;
                    }
                }
                if (i != 0 || j != 0) {
                    f1 /= 4.0f;
                }
                f += f1;
            }
        }
        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean bl = flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0f;
        } else {
            boolean flag2;
            boolean bl2 = flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0f;
            }
        }
        return f;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && this.canPlaceBlockOn(worldIn.getBlockState(pos.down()).getBlock());
    }

    protected Item getSeed() {
        return Items.wheat_seeds;
    }

    protected Item getCrop() {
        return Items.wheat;
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        int i;
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
        if (!worldIn.isRemote && (i = ((Integer)state.getValue((IProperty)AGE)).intValue()) >= 7) {
            int j = 3 + fortune;
            for (int k = 0; k < j; ++k) {
                if (worldIn.rand.nextInt(15) > i) continue;
                BlockCrops.spawnAsEntity((World)worldIn, (BlockPos)pos, (ItemStack)new ItemStack(this.getSeed(), 1, 0));
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return (Integer)state.getValue((IProperty)AGE) == 7 ? this.getCrop() : this.getSeed();
    }

    public Item getItem(World worldIn, BlockPos pos) {
        return this.getSeed();
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return (Integer)state.getValue((IProperty)AGE) < 7;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty((IProperty)AGE, (Comparable)Integer.valueOf((int)meta));
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue((IProperty)AGE);
    }

    protected BlockState createBlockState() {
        return new BlockState((Block)this, new IProperty[]{AGE});
    }
}
