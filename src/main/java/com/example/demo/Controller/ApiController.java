package com.example.demo.Controller;

import com.example.demo.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    // 🌦️ Weather
    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
        return apiService.getWeather(city);
    }

    // 😂 Joke
    @GetMapping("/joke")
    public String getJoke() {
        return apiService.getJoke();
    }

    // 🧠 Quote
    @GetMapping("/quote")
    public String getQuote() {
        return apiService.getQuote();
    }

    // 🎲 Activity
    @GetMapping("/activity")
    public String getActivity() {
        return apiService.getActivity();
    }
}