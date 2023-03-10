package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagIntArray
extends NBTBase {
    private int[] intArray;

    NBTTagIntArray() {
    }

    public NBTTagIntArray(int[] p_i45132_1_) {
        this.intArray = p_i45132_1_;
    }

    void write(DataOutput output) throws IOException {
        output.writeInt(this.intArray.length);
        for (int i = 0; i < this.intArray.length; ++i) {
            output.writeInt(this.intArray[i]);
        }
    }

    void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
        sizeTracker.read(192L);
        int i = input.readInt();
        sizeTracker.read((long)(32 * i));
        this.intArray = new int[i];
        for (int j = 0; j < i; ++j) {
            this.intArray[j] = input.readInt();
        }
    }

    public byte getId() {
        return 11;
    }

    public String toString() {
        String s = "[";
        for (int i : this.intArray) {
            s = s + i + ",";
        }
        return s + "]";
    }

    public NBTBase copy() {
        int[] aint = new int[this.intArray.length];
        System.arraycopy((Object)this.intArray, (int)0, (Object)aint, (int)0, (int)this.intArray.length);
        return new NBTTagIntArray(aint);
    }

    public boolean equals(Object p_equals_1_) {
        return super.equals(p_equals_1_) ? Arrays.equals((int[])this.intArray, (int[])((NBTTagIntArray)((Object)p_equals_1_)).intArray) : false;
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode((int[])this.intArray);
    }

    public int[] getIntArray() {
        return this.intArray;
    }
}
