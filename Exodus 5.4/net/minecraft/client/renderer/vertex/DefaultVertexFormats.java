/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.vertex;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class DefaultVertexFormats {
    public static final VertexFormat ITEM;
    public static final VertexFormat POSITION_COLOR;
    public static final VertexFormat POSITION_TEX_LMAP_COLOR;
    public static final VertexFormat POSITION;
    public static final VertexFormatElement TEX_2S;
    public static final VertexFormat OLDMODEL_POSITION_TEX_NORMAL;
    public static final VertexFormatElement NORMAL_3B;
    public static final VertexFormat POSITION_TEX_COLOR;
    public static final VertexFormat POSITION_TEX_COLOR_NORMAL;
    public static final VertexFormatElement TEX_2F;
    public static final VertexFormat POSITION_TEX;
    public static final VertexFormat POSITION_TEX_NORMAL;
    public static final VertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP;
    public static final VertexFormatElement POSITION_3F;
    public static final VertexFormatElement COLOR_4UB;
    public static final VertexFormat BLOCK;
    public static final VertexFormat POSITION_NORMAL;
    public static final VertexFormatElement PADDING_1B;

    static {
        BLOCK = new VertexFormat();
        ITEM = new VertexFormat();
        OLDMODEL_POSITION_TEX_NORMAL = new VertexFormat();
        PARTICLE_POSITION_TEX_COLOR_LMAP = new VertexFormat();
        POSITION = new VertexFormat();
        POSITION_COLOR = new VertexFormat();
        POSITION_TEX = new VertexFormat();
        POSITION_NORMAL = new VertexFormat();
        POSITION_TEX_COLOR = new VertexFormat();
        POSITION_TEX_NORMAL = new VertexFormat();
        POSITION_TEX_LMAP_COLOR = new VertexFormat();
        POSITION_TEX_COLOR_NORMAL = new VertexFormat();
        POSITION_3F = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3);
        COLOR_4UB = new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR, 4);
        TEX_2F = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2);
        TEX_2S = new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV, 2);
        NORMAL_3B = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.NORMAL, 3);
        PADDING_1B = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.PADDING, 1);
        BLOCK.func_181721_a(POSITION_3F);
        BLOCK.func_181721_a(COLOR_4UB);
        BLOCK.func_181721_a(TEX_2F);
        BLOCK.func_181721_a(TEX_2S);
        ITEM.func_181721_a(POSITION_3F);
        ITEM.func_181721_a(COLOR_4UB);
        ITEM.func_181721_a(TEX_2F);
        ITEM.func_181721_a(NORMAL_3B);
        ITEM.func_181721_a(PADDING_1B);
        OLDMODEL_POSITION_TEX_NORMAL.func_181721_a(POSITION_3F);
        OLDMODEL_POSITION_TEX_NORMAL.func_181721_a(TEX_2F);
        OLDMODEL_POSITION_TEX_NORMAL.func_181721_a(NORMAL_3B);
        OLDMODEL_POSITION_TEX_NORMAL.func_181721_a(PADDING_1B);
        PARTICLE_POSITION_TEX_COLOR_LMAP.func_181721_a(POSITION_3F);
        PARTICLE_POSITION_TEX_COLOR_LMAP.func_181721_a(TEX_2F);
        PARTICLE_POSITION_TEX_COLOR_LMAP.func_181721_a(COLOR_4UB);
        PARTICLE_POSITION_TEX_COLOR_LMAP.func_181721_a(TEX_2S);
        POSITION.func_181721_a(POSITION_3F);
        POSITION_COLOR.func_181721_a(POSITION_3F);
        POSITION_COLOR.func_181721_a(COLOR_4UB);
        POSITION_TEX.func_181721_a(POSITION_3F);
        POSITION_TEX.func_181721_a(TEX_2F);
        POSITION_NORMAL.func_181721_a(POSITION_3F);
        POSITION_NORMAL.func_181721_a(NORMAL_3B);
        POSITION_NORMAL.func_181721_a(PADDING_1B);
        POSITION_TEX_COLOR.func_181721_a(POSITION_3F);
        POSITION_TEX_COLOR.func_181721_a(TEX_2F);
        POSITION_TEX_COLOR.func_181721_a(COLOR_4UB);
        POSITION_TEX_NORMAL.func_181721_a(POSITION_3F);
        POSITION_TEX_NORMAL.func_181721_a(TEX_2F);
        POSITION_TEX_NORMAL.func_181721_a(NORMAL_3B);
        POSITION_TEX_NORMAL.func_181721_a(PADDING_1B);
        POSITION_TEX_LMAP_COLOR.func_181721_a(POSITION_3F);
        POSITION_TEX_LMAP_COLOR.func_181721_a(TEX_2F);
        POSITION_TEX_LMAP_COLOR.func_181721_a(TEX_2S);
        POSITION_TEX_LMAP_COLOR.func_181721_a(COLOR_4UB);
        POSITION_TEX_COLOR_NORMAL.func_181721_a(POSITION_3F);
        POSITION_TEX_COLOR_NORMAL.func_181721_a(TEX_2F);
        POSITION_TEX_COLOR_NORMAL.func_181721_a(COLOR_4UB);
        POSITION_TEX_COLOR_NORMAL.func_181721_a(NORMAL_3B);
        POSITION_TEX_COLOR_NORMAL.func_181721_a(PADDING_1B);
    }
}

