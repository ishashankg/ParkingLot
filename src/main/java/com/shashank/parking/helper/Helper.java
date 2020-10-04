package com.shashank.parking.helper;

public class Helper {

	public static boolean validateRequest(String str) {
		boolean isValid = false;
		String[] inputs = str.split(" ");
		if (inputs.length == 1 && inputs[0].equals(Constants.STATUS)) {
			isValid = true;
		} else if (inputs.length == 2
				&& (inputs[0].equals(Constants.CREATE_PARKING_LOT) || inputs[0].equals(Constants.PARK))) {
			isValid = true;
		} else if (inputs.length == 3 && inputs[0].equals(Constants.LEAVE)) {
			isValid = true;
		}
		return isValid;
	}
}
