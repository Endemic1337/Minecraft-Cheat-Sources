package net.minecraft.world.gen.layer;

public class GenLayerFuzzyZoom extends GenLayerZoom {
    public GenLayerFuzzyZoom(final long p_i2123_1_, final GenLayer p_i2123_3_) {
        super(p_i2123_1_, p_i2123_3_);
    }

    /**
     * returns the most frequently occurring number of the set, or a random number from those provided
     */
    protected int selectModeOrRandom(final int p_151617_1_, final int p_151617_2_, final int p_151617_3_, final int p_151617_4_) {
        return this.selectRandom(p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_);
    }
}
