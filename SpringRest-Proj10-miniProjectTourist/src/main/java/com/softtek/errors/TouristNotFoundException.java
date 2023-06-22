package com.softtek.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TouristNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TouristNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
