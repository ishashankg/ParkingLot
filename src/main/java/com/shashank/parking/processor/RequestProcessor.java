package com.shashank.parking.processor;

import com.shashank.parking.helper.Constants;
import com.shashank.parking.model.Car;
import com.shashank.parking.service.ParkingService;

public class RequestProcessor {
	private ParkingService parkingService;

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public void process(String input) throws Exception {
		String[] inputs = input.split(" ");
		String key = inputs[0];
		switch (key) {
		case Constants.CREATE_PARKING_LOT:
			try {
				int capacity = Integer.parseInt(inputs[1]);
				parkingService.createParkingLot(capacity);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Error while parsing for create parking slot");
			}
			break;
		case Constants.PARK:
			parkingService.park(new Car(inputs[1]));
			break;
		case Constants.LEAVE:
			try {
				int duration = Integer.parseInt(inputs[2]);
				parkingService.unpark(new Car(inputs[1]), duration);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Error while parsing for unpark");
			}
			break;
		case Constants.STATUS:
			parkingService.status();
			break;
		default:
			break;
		}
	}
}
