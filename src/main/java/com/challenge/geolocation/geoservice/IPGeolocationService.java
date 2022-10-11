package com.challenge.geolocation.geoservice;

import com.challenge.geolocation.dto.GeoLocationDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IPGeolocationService implements IPGeolocation{
    @Override
    public GeoLocationDto callIpApi(String ipAddress) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(createURL(ipAddress)))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), GeoLocationDto.class);
    }
    private String createURL(String ipAddress) {

        return "http://ip-api.com/json/" + ipAddress + "?" + createFields();
    }
    private String createFields() {

        return "fields=" + "status,message,continent,continentCode,country,countryCode,region,regionName," +
                "city,district,zip,lat,lon,timezone,offset,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query";
    }
}
