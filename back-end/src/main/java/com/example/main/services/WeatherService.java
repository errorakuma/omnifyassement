package com.example.main.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.main.models.Example;
import com.example.main.models.Today;
import com.example.main.models.TodaysWeather;

@Service
public class WeatherService extends MappingJackson2HttpMessageConverter {

	private static WeatherService ourInstance = new WeatherService();

	public static WeatherService getInstance() {
		return ourInstance;
	}

	private WeatherService() {
		setPrettyPrint(true);
	}

//	url = 'https://api.openweathermap.org/data/2.5/weather';
//	  apikey = 'b8e894e877f3ff37a958b371754f7c57';
//	

	public List<Example> getWeather(String city) {

		String websiteResponse = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&mode=json&appid=b8e894e877f3ff37a958b371754f7c57&units=metric";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(websiteResponse, String.class);

		String description = null;
		String WeatherCondition = null;
		double temp;
		double temp_min;
		double temp_max;
		double pressure;
		int humidity;

		List<Example> weatherList = new ArrayList<>();

		JSONObject root = new JSONObject(result);

		JSONArray weatherObject = root.getJSONArray("weather");

		for (int i = 0; i < weatherObject.length(); i++) {
			JSONObject elementInArray = weatherObject.getJSONObject(i);
			description = elementInArray.getString("description");
			WeatherCondition = elementInArray.getString("main");
		}

		JSONObject main = root.getJSONObject("main");

		temp = (int) main.getFloat("temp");
		pressure = main.getInt("pressure");
		humidity = main.getInt("humidity");
		temp_min = (int) main.getFloat("temp_min");
		temp_max = (int) main.getFloat("temp_max");

		TodaysWeather tw = new TodaysWeather();
		Example e = new Example();
		Today t = new Today();

		t.setDescription(description);

		t.setHumidity(humidity);
		t.setMain(WeatherCondition);
		t.setPressure(pressure);

		t.setTemp(temp);
		t.setTempMax(temp_max);
		t.setTempMin(temp_min);

		tw.setToday(t);
		e.setTodaysWeather(tw);

		weatherList.add(e);
		return weatherList;
	}

}
