package net.minecraft.world.gen.layer;

public class GenLayerZoom extends GenLayer {
    public GenLayerZoom(final long p_i2134_1_, final GenLayer p_i2134_3_) {
        super(p_i2134_1_);
        super.parent = p_i2134_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(final int areaX, final int areaY, final int areaWidth, final int areaHeight) {
        final int i = areaX >> 1;
        final int j = areaY >> 1;
        final int k = (areaWidth >> 1) + 2;
        final int l = (areaHeight >> 1) + 2;
        final int[] aint = this.parent.getInts(i, j, k, l);
        final int i1 = k - 1 << 1;
        final int j1 = l - 1 << 1;
        final int[] aint1 = IntCache.getIntCache(i1 * j1);

        for (int k1 = 0; k1 < l - 1; ++k1) {
            int l1 = (k1 << 1) * i1;
            int i2 = 0;
            int j2 = aint[i2 + 0 + (k1 + 0) * k];

            for (int k2 = aint[i2 + 0 + (k1 + 1) * k]; i2 < k - 1; ++i2) {
                this.initChunkSeed(i2 + i << 1, k1 + j << 1);
                final int l2 = aint[i2 + 1 + (k1 + 0) * k];
                final int i3 = aint[i2 + 1 + (k1 + 1) * k];
                aint1[l1] = j2;
                aint1[l1++ + i1] = this.selectRandom2(j2, k2);
                aint1[l1] = this.selectRandom2(j2, l2);
                aint1[l1++ + i1] = this.selectModeOrRandom(j2, l2, k2, i3);
                j2 = l2;
                k2 = i3;
            }
        }

        final int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int j3 = 0; j3 < areaHeight; ++j3) {
            System.arraycopy(aint1, (j3 + (areaY & 1)) * i1 + (areaX & 1), aint2, j3 * areaWidth, areaWidth);
        }

        return aint2;
    }

    /**
     * Magnify a layer. Parms are seed adjustment, layer, number of times to magnify
     */
    public static GenLayer magnify(final long p_75915_0_, final GenLayer p_75915_2_, final int p_75915_3_) {
        GenLayer genlayer = p_75915_2_;

        for (int i = 0; i < p_75915_3_; ++i) {
            genlayer = new GenLayerZoom(p_75915_0_ + (long) i, genlayer);
        }

        return genlayer;
    }

    protected int selectRandom2(final int p_selectRandom2_1_, final int p_selectRandom2_2_) {
        final int i = this.nextInt(2);
        return i == 0 ? p_selectRandom2_1_ : p_selectRandom2_2_;
    }
}
