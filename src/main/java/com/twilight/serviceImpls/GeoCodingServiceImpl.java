package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.Address;
import com.twilight.dataTransferObjects.Point;
import com.twilight.exceptions.GeocodingError;
import com.twilight.services.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Point getLocation(Address address) throws GeocodingError
    {
        String headerName = "User-Agent";
        String headerValue = "TwilightFoodDelivery/1.0";

        HttpEntity<Void> request = createRequest(headerName,headerValue);
        String url = generateUrl(address.state(),address.city(),address.pinCode(),address.street(),address.landMark());


        String response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        ).getBody();


        return formatForLatAndLon(response);
    }

    private String generateUrl(String state,
                               String city,
                               String pinCode,
                               String street,
                               String landmark
    ){
        String query =
                street + ", " +
                        city + ", " +
                        state + ", " +
                        "India";
        String baseUrl = "https://nominatim.openstreetmap.org/search";
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("q", query)
                .queryParam("format", "jsonv2")
                .queryParam("limit", 1)
                .build(false)
                .toUriString();
    }
    <T> HttpEntity<T> createRequest(String headerName,
                                    String headerValue
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerName, headerValue);
        return new HttpEntity<T>(headers);
    }

    private Point formatForLatAndLon(String response)
    {
        JsonNode root = new ObjectMapper().readTree(response);

        if(root.isEmpty())
            throw new GeocodingError("Address not found");
        double lat =
                root.get(0)
                        .get("lat")
                        .asDouble();

        double lon =
                root.get(0)
                        .get("longitude")
                        .asDouble();
        return new Point(lat,lon);
    }

}
