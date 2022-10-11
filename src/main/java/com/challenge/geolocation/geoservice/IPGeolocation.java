package com.challenge.geolocation.geoservice;

import com.challenge.geolocation.dto.GeoLocationDto;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IPGeolocation {

    GeoLocationDto callIpApi(String ipAddress) throws IOException, InterruptedException, URISyntaxException;
}
