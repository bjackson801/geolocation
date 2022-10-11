package com.challenge.geolocation.interactor;

import com.challenge.geolocation.dao.Dao;
import com.challenge.geolocation.dao.GeoLocationDao;
import com.challenge.geolocation.geoservice.IPGeolocation;

public class GeoLocationFactory {

    public final Dao geoLocationDao;
    private final IPGeolocation ipGeolocation;


    public GeoLocationFactory(GeoLocationDao geoLocationDao, IPGeolocation ipGeolocation) {
        this.geoLocationDao = geoLocationDao;
        this.ipGeolocation = ipGeolocation;
    }

    public GeoLocationInteractor make (){

        return new GeoLocationInteractor(geoLocationDao, ipGeolocation);
    }
}
