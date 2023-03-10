package net.minecraft.server.management;

import com.google.gson.JsonObject;

import java.io.File;
import java.net.SocketAddress;

public class BanList extends UserList<String, IPBanEntry> {
    public BanList(final File bansFile) {
        super(bansFile);
    }

    protected UserListEntry<String> createEntry(final JsonObject entryData) {
        return new IPBanEntry(entryData);
    }

    public boolean isBanned(final SocketAddress address) {
        final String s = this.addressToString(address);
        return this.hasEntry(s);
    }

    public IPBanEntry getBanEntry(final SocketAddress address) {
        final String s = this.addressToString(address);
        return this.getEntry(s);
    }

    private String addressToString(final SocketAddress address) {
        String s = address.toString();

        if (s.contains("/")) {
            s = s.substring(s.indexOf(47) + 1);
        }

        if (s.contains(":")) {
            s = s.substring(0, s.indexOf(58));
        }

        return s;
    }
}
