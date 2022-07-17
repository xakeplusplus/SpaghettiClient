package me.xakeplusplus.spg.event.events;

import me.xakeplusplus.spg.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {
	private final Packet p;
	
	public PacketEvent(Packet p) {
		super();
		this.p = p;
	}

	public Packet getPacket() {
		return this.p;
	}

	public static class Receive extends PacketEvent {

		public Receive(Packet p) {
			super(p);
		}
	}

	public static class Send extends PacketEvent {
		public Send(Packet p) {
			super(p);
		}
	}

	public static class PostReceive extends PacketEvent {
		public PostReceive(Packet p) {
			super(p);
		}
	}

	public static class PostSend extends PacketEvent {
		public PostSend(Packet p) {
			super(p);
		}
	}
	
	
}
