package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.dataTransferObjects.Point;
import com.twilight.services.GeoCodingService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GeoCodingServiceImpl implements GeoCodingService {
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public Point getLocation(AddressR address) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "TwilightFoodDelivery/1.0");
        String query =
                address.landMark() + ", " +
                        address.street() + ", " +
                        address.city() + ", " +
                        address.pinCode() + ", India";
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = UriComponentsBuilder
                .fromUriString("https://nominatim.openstreetmap.org/search")
                .queryParam("q", query)
                .queryParam("format", "jsonv2")
                .queryParam("limit", 1)
                .build(false)
                .toUriString();

        String response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();

        JsonNode root =
                new ObjectMapper().readTree(response);

        if(root.isEmpty())
            throw new RuntimeException(
                    "Address not found"
            );

        double lat =
                root.get(0)
                        .get("lat")
                        .asDouble();

        double lon =
                root.get(0)
                        .get("lon")
                        .asDouble();
        System.out.println("Response : ---------------------------------------------------"+ response);
        return new Point(lat,lon);
    }
}
