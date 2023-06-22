package com.softtek.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller & @response Body
@RequestMapping("/message") // global path
public class MessageRenderController {
	@GetMapping("/generate")
	public ResponseEntity<String> generateMessage() {
		// get system date & time
		LocalDateTime ldt = LocalDateTime.now();
		// get the current hour
		int hour = ldt.getHour();
		String body = null;
		// return hour<12?"Godd Morning":hour<16?"good afternoon":"goodnight";
		if (hour < 12)
			body = "good morning";
		else if (hour < 16)
			body = "good afternoon";
		else if (hour < 20)
			body = "good evening";
		else
			body = "good night";

		HttpStatus status = HttpStatus.OK;
		ResponseEntity<String> rs = new ResponseEntity<>(body, status);
		return rs;
	}
}
