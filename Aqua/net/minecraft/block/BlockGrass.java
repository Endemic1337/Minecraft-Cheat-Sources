package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public class BlockGrass
extends Block
implements IGrowable {
    public static final PropertyBool SNOWY = PropertyBool.create((String)"snowy");

    protected BlockGrass() {
        super(Material.grass);
        this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty)SNOWY, (Comparable)Boolean.valueOf((boolean)false)));
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty((IProperty)SNOWY, (Comparable)Boolean.valueOf((block == Blocks.snow || block == Blocks.snow_layer ? 1 : 0) != 0));
    }

    public int getBlockColor() {
        return ColorizerGrass.getGrassColor((double)0.5, (double)1.0);
    }

    public int getRenderColor(IBlockState state) {
        return this.getBlockColor();
    }

    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
        return BiomeColorHelper.getGrassColorAtPos((IBlockAccess)worldIn, (BlockPos)pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getBlock().getLightOpacity() > 2) {
                worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
            } else if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    Block block = worldIn.getBlockState(blockpos.up()).getBlock();
                    IBlockState iblockstate = worldIn.getBlockState(blockpos);
                    if (iblockstate.getBlock() != Blocks.dirt || iblockstate.getValue((IProperty)BlockDirt.VARIANT) != BlockDirt.DirtType.DIRT || worldIn.getLightFromNeighbors(blockpos.up()) < 4 || block.getLightOpacity() > 2) continue;
                    worldIn.setBlockState(blockpos, Blocks.grass.getDefaultState());
                }
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.DIRT), rand, fortune);
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        BlockPos blockpos = pos.up();
        block0: for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;
            while (true) {
                if (j >= i / 16) {
                    if (worldIn.getBlockState((BlockPos)blockpos1).getBlock().blockMaterial != Material.air) continue block0;
                    if (rand.nextInt(8) == 0) {
                        IBlockState iblockstate;
                        BlockFlower.EnumFlowerType blockflower$enumflowertype = worldIn.getBiomeGenForCoords(blockpos1).pickRandomFlower(rand, blockpos1);
                        BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();
                        if (!blockflower.canBlockStay(worldIn, blockpos1, iblockstate = blockflower.getDefaultState().withProperty(blockflower.getTypeProperty(), (Comparable)blockflower$enumflowertype))) continue block0;
                        worldIn.setBlockState(blockpos1, iblockstate, 3);
                        continue block0;
                    }
                    IBlockState iblockstate1 = Blocks.tallgrass.getDefaultState().withProperty((IProperty)BlockTallGrass.TYPE, (Comparable)BlockTallGrass.EnumType.GRASS);
                    if (!Blocks.tallgrass.canBlockStay(worldIn, blockpos1, iblockstate1)) continue block0;
                    worldIn.setBlockState(blockpos1, iblockstate1, 3);
                    continue block0;
                }
                if (worldIn.getBlockState((blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1)).down()).getBlock() != Blocks.grass || worldIn.getBlockState(blockpos1).getBlock().isNormalCube()) continue block0;
                ++j;
            }
        }
    }

    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    protected BlockState createBlockState() {
        return new BlockState((Block)this, new IProperty[]{SNOWY});
    }
}
