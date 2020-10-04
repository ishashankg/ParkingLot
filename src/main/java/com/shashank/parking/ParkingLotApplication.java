package com.shashank.parking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.shashank.parking.helper.Helper;
import com.shashank.parking.processor.RequestProcessor;
import com.shashank.parking.service.ParkingService;
import com.shashank.parking.service.ParkingServiceImpl;

public class ParkingLotApplication {

	public static void main(String[] args) {
		if (args.length == 1) {
			RequestProcessor processor = new RequestProcessor();
			ParkingService parkingService = new ParkingServiceImpl();
			processor.setParkingService(parkingService);
			File inputFile = new File(args[0]);
			String input = null;
			try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile))) {
				while ((input = bufferReader.readLine()) != null) {
					input = input.trim();
					if (Helper.validateRequest(input)) {
						try {
							processor.process(input);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} else
						System.out.println("Incorrect Command provided");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("File path not provided");
		}
	}

}
