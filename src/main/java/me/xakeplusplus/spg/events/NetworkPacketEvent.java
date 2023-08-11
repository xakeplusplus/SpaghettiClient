package me.xakeplusplus.spg.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class NetworkPacketEvent extends Event {

    public Packet packet;

    public NetworkPacketEvent(Packet packet) {
        super();
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}
