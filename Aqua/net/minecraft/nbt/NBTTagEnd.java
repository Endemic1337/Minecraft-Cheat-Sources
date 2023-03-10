package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagEnd
extends NBTBase {
    void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
        sizeTracker.read(64L);
    }

    void write(DataOutput output) throws IOException {
    }

    public byte getId() {
        return 0;
    }

    public String toString() {
        return "END";
    }

    public NBTBase copy() {
        return new NBTTagEnd();
    }
}
