package com.shashank.parking;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shashank.parking.model.Car;
import com.shashank.parking.service.ParkingService;
import com.shashank.parking.service.ParkingServiceImpl;

public class ParkingLotApplicationTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	public void init() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void createParkingLot() throws Exception {
		ParkingService service = new ParkingServiceImpl();
		service.createParkingLot(10);
		assertTrue(outContent.toString().contains("Created parking lot with 10 slots"));
		service.resetParkingManager();
	}
	
	@Test
	public void parking() throws Exception {
		ParkingService service = new ParkingServiceImpl();
		service.createParkingLot(10);
		service.park(new Car("KA-01-HH-7777"));
		assertTrue(outContent.toString().contains("Allocated slot number : 1"));
		service.resetParkingManager();
	}

	@Test
	public void parkingLotFull() throws Exception {
		ParkingService service = new ParkingServiceImpl();
		service.createParkingLot(2);
		service.park(new Car("KA-01-HH-7777"));
		service.park(new Car("KA-01-HH-2701"));
		service.park(new Car("KA-01-P-333"));
		assertTrue(outContent.toString().contains("Sorry, parking lot is full"));
		service.resetParkingManager();
	}
	
	@Test
	public void unpark() throws Exception {
		ParkingService service = new ParkingServiceImpl();
		service.createParkingLot(10);
		service.park(new Car("KA-01-HH-7777"));
		service.park(new Car("KA-01-HH-2701"));
		service.unpark(new Car("KA-01-HH-7777") , 4);
		assertTrue(outContent.toString().contains("Registration number KA-01-HH-7777 with slot number 1 is free with charge 30"));
		service.resetParkingManager();
	}
	
	@Test
	public void notFound() throws Exception {
		ParkingService service = new ParkingServiceImpl();
		service.createParkingLot(10);
		service.park(new Car("KA-01-HH-7777"));
		service.park(new Car("KA-01-HH-2701"));
		service.unpark(new Car("KA-01-HH-8888") , 4);
		assertTrue(outContent.toString().contains("Registration number KA-01-HH-8888 not found"));
		service.resetParkingManager();
	}
}
