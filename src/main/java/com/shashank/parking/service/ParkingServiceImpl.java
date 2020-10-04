package com.shashank.parking.service;

import java.util.List;

import com.shashank.parking.helper.ParkingManager;
import com.shashank.parking.helper.ParkingSlotManager;
import com.shashank.parking.model.Vehicle;

public class ParkingServiceImpl implements ParkingService {

	private ParkingManager parkingManager = null;

	@Override
	public void createParkingLot(int capacity) {
		if (parkingManager != null) {
			throw new RuntimeException("Parking Lot already created");
		}
		parkingManager = ParkingManager.getInstance(capacity, new ParkingSlotManager());
		System.out.println("Created parking lot with " + capacity + " slots");
	}

	@Override
	public void park(Vehicle vehicle) {
		if (parkingManager == null) {
			throw new RuntimeException("Parking lot doesn't exists");
		}
		int slot = parkingManager.park(vehicle);
		if (slot == -1) {
			System.out.println("Sorry, parking lot is full");
		} else {
			System.out.println("Allocated slot number : " + slot);
		}
	}

	@Override
	public void unpark(Vehicle vehicle, int duration) {
		if (parkingManager == null) {
			throw new RuntimeException("Parking lot doesn't exists");
		}
		int slot = parkingManager.unpark(vehicle);
		if (slot == -1) {
			System.out.println(String.format("Registration number %s not found", vehicle.getRegistrationNo()));
		} else {
			int charge = (duration <= 2) ? 10 : (10 + (duration - 2) * 10);
			System.out.println(String.format("Registration number %s with slot number %d is free with charge %d",
					vehicle.getRegistrationNo(), slot, charge));
		}
	}

	@Override
	public void status() {
		if (parkingManager == null) {
			throw new RuntimeException("Parking lot doesn't exists");
		}
		System.out.println("Slot No.\tRegistration No.");
		List<String> statusList = parkingManager.status();
		if (statusList.size() == 0)
			System.out.println("Sorry, parking lot is empty.");
		else {
			for (String statusSting : statusList) {
				System.out.println(statusSting);
			}
		}
	}

	@Override
	public void resetParkingManager() {
		if (parkingManager != null) {
			parkingManager.resetParkingManager();
		}
	}
}
