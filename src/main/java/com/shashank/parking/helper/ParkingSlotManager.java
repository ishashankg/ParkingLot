package com.shashank.parking.helper;

import java.util.TreeSet;

public class ParkingSlotManager {
	private TreeSet<Integer> slots;

	public ParkingSlotManager() {
		this.slots = new TreeSet<Integer>();
	}

	public int getSlot() {
		return slots.first();
	}

	public void add(int slot) {
		slots.add(slot);
	}

	public void remove(int slot) {
		slots.remove(slot);
	}

}
