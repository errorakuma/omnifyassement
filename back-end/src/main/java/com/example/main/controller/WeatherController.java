package com.example.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Example;
import com.example.main.services.WeatherService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
//
//	@Autowired
//	public WeatherController(WeatherService weatherService) {
//		this.weatherService = weatherService;
//	}

	@GetMapping("/")
	String home() {
		return "Hello World!";
	}

	@GetMapping("/weather/{city}")
	public List<Example> getWeather(@PathVariable String city) {
		return this.weatherService.getWeather(city);
	}

}
