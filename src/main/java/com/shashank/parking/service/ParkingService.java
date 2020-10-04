package com.shashank.parking.service;

import com.shashank.parking.model.Vehicle;

public interface ParkingService {
	public void createParkingLot(int capacity);
	
	public void park(Vehicle vehicle);
	
	public void unpark(Vehicle vehicle, int duration);
	
	public void status();
	
	public void resetParkingManager();
}
