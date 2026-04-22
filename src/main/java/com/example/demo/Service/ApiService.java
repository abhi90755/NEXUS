package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.api.key}")
    private String weatherApiKey;

    // 🌦️ Weather
    public String getWeather(String city) {
        String url = "http://api.weatherapi.com/v1/current.json?key="
            + weatherApiKey + "&q=" + city + "&aqi=no";

        return restTemplate.getForObject(url, String.class);
    }

    // 😂 Joke
    public String getJoke() {
        String url = "https://v2.jokeapi.dev/joke/Any";
        return restTemplate.getForObject(url, String.class);
    }

    // 🧠 Quote
    public String getQuote() {
        String url = "https://zenquotes.io/api/random";
        return restTemplate.getForObject(url, String.class);
    }

    // 🎲 Activity
    public String getActivity() {
        String url = "https://bored-api.appbrewery.com/random";
        return restTemplate.getForObject(url, String.class);
    }
}