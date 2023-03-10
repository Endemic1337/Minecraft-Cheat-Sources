package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna
extends BiomeGenBase {
    private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);

    protected BiomeGenSavanna(int id) {
        super(id);
        this.spawnableCreatureList.add((Object)new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 20;
    }

    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        return rand.nextInt(5) > 0 ? field_150627_aC : this.worldGeneratorTrees;
    }

    protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
        Mutated biomegenbase = new Mutated(p_180277_1_, (BiomeGenBase)this);
        biomegenbase.temperature = (this.temperature + 1.0f) * 0.5f;
        biomegenbase.minHeight = this.minHeight * 0.5f + 0.3f;
        biomegenbase.maxHeight = this.maxHeight * 0.5f + 1.2f;
        return biomegenbase;
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
        for (int i = 0; i < 7; ++i) {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
        }
        super.decorate(worldIn, rand, pos);
    }
}
