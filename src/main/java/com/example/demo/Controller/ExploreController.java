package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/explore")
public class ExploreController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/nearby")
    public List<Map<String, Object>> getNearbyPlaces(
            @RequestParam double lat,
            @RequestParam double lon) {

        String query = "[out:json];node(around:2000," + lat + "," + lon + ")[amenity];out;";
        String url = "https://overpass-api.de/api/interpreter?data=" + query;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map body = response.getBody();

        List<Map<String, Object>> results = new ArrayList<>();

        if (body != null && body.get("elements") != null) {
            List<Map<String, Object>> elements =
                    (List<Map<String, Object>>) body.get("elements");

            for (Map el : elements) {
                Map<String, Object> place = new HashMap<>();

                place.put("lat", el.get("lat"));
                place.put("lon", el.get("lon"));

                Map tags = (Map) el.get("tags");

                if (tags != null) {
                    place.put("name", tags.getOrDefault("name", "Unnamed"));
                    place.put("type", tags.getOrDefault("amenity", "unknown"));
                }

                results.add(place);
            }
        }

        return results;
    }
}