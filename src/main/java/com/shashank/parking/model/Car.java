package com.shashank.parking.model;

public class Car extends Vehicle {
	public Car(String registrationNo) {
		super(registrationNo);
	}

	@Override
	public String toString() {
		return "Car [getRegistrationNo()=" + getRegistrationNo() + "]";
	}

}
