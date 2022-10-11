package com.challenge.geolocation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeoLocationDto {

    private String status;

    private String message;

    private String continent;

    private String continentCode;

    private String country;

    private String countryCode;

    private String region;

    private String regionName;

    private String city;

    private String district;

    private String zip;

    private float lat;

    private float lon;

    private String timezone;

    private int offset;

    private String currency;

    private String isp;

    private String org;

    private String as;

    private String asName;

    private String serviceName;

    private String reverse;

    private boolean mobile;

    private boolean proxy;

    private boolean hosting;

    private String query;
}
