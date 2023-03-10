package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerAddMushroomIsland extends GenLayer {
    public GenLayerAddMushroomIsland(final long p_i2120_1_, final GenLayer p_i2120_3_) {
        super(p_i2120_1_);
        this.parent = p_i2120_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(final int areaX, final int areaY, final int areaWidth, final int areaHeight) {
        final int i = areaX - 1;
        final int j = areaY - 1;
        final int k = areaWidth + 2;
        final int l = areaHeight + 2;
        final int[] aint = this.parent.getInts(i, j, k, l);
        final int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i1 = 0; i1 < areaHeight; ++i1) {
            for (int j1 = 0; j1 < areaWidth; ++j1) {
                final int k1 = aint[j1 + 0 + (i1 + 0) * k];
                final int l1 = aint[j1 + 2 + (i1 + 0) * k];
                final int i2 = aint[j1 + 0 + (i1 + 2) * k];
                final int j2 = aint[j1 + 2 + (i1 + 2) * k];
                final int k2 = aint[j1 + 1 + (i1 + 1) * k];
                this.initChunkSeed(j1 + areaX, i1 + areaY);

                if (k2 == 0 && k1 == 0 && l1 == 0 && i2 == 0 && j2 == 0 && this.nextInt(100) == 0) {
                    aint1[j1 + i1 * areaWidth] = BiomeGenBase.mushroomIsland.biomeID;
                } else {
                    aint1[j1 + i1 * areaWidth] = k2;
                }
            }
        }

        return aint1;
    }
}
