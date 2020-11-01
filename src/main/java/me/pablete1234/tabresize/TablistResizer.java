package me.pablete1234.tabresize;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TablistResizer extends JavaPlugin {

    private static final int TAB_SIZE = 80;

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager()
                .addPacketListener(new TablistResizePacketAdapter());
    }

    private class TablistResizePacketAdapter extends PacketAdapter {

        public TablistResizePacketAdapter() {
            super(TablistResizer.this, ListenerPriority.LOWEST, PacketType.Play.Server.LOGIN);
        }

        @Override
        public void onPacketSending(PacketEvent event) {
            if (event.getPacketType() == PacketType.Play.Server.LOGIN)
                event.getPacket().getIntegers().write(2, TAB_SIZE);
        }

    }

}
