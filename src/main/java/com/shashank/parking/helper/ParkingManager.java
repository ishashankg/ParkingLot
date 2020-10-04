package com.shashank.parking.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shashank.parking.model.Vehicle;

public class ParkingManager {
	private int capacity;
	private int availableSlots;
	private Map<Integer, Vehicle> slotsToVehicle;
	private static volatile ParkingManager instance = null;
	private ParkingSlotManager parkingSlotManager;

	public static ParkingManager getInstance(int capacity, ParkingSlotManager parkingSlotManager) {
		if (instance == null) {
			synchronized (ParkingManager.class) {
				if (instance == null) {
					instance = new ParkingManager(capacity, parkingSlotManager);
				}
			}
		}
		return instance;
	}

	public ParkingManager(int capacity, ParkingSlotManager parkingSlotManager) {
		this.capacity = capacity;
		this.availableSlots = capacity;
		this.parkingSlotManager = parkingSlotManager;
		slotsToVehicle = new HashMap<>();
		for (int i = 1; i <= capacity; i++) {
			slotsToVehicle.put(i, null);
			parkingSlotManager.add(i);
		}
	}

	public int park(Vehicle vehicle) {
		int availableSlot;
		if (availableSlots == 0) {
			return -1;
		} else {
			availableSlot = parkingSlotManager.getSlot();
			if (slotsToVehicle.containsValue(vehicle)) {
				return -1;
			}
			slotsToVehicle.put(availableSlot, vehicle);
			availableSlots--;
			parkingSlotManager.remove(availableSlot);
		}
		return availableSlot;
	}

	public int unpark(Vehicle vehicle) {
		if (!slotsToVehicle.containsValue(vehicle)) {
			return -1;
		} else {
			int slot = slotsToVehicle.entrySet().stream().filter(entry -> vehicle.equals(entry.getValue()))
					.map(Map.Entry::getKey).findFirst().get();
			availableSlots++;
			parkingSlotManager.add(slot);
			slotsToVehicle.put(slot, null);
			return slot;
		}
	}

	public List<String> status() {
		List<String> status = new ArrayList<>();
		for (int i = 1; i <= capacity; i++) {
			Vehicle vehicle = slotsToVehicle.get(i);
			if (vehicle != null) {
				status.add(i + "\t\t" + vehicle.getRegistrationNo());
			}
		}
		return status;
	}

	public void resetParkingManager() {
		instance = null;
	}
}
