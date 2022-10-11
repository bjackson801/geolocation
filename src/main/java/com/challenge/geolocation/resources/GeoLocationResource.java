package com.challenge.geolocation.resources;

import com.challenge.geolocation.cache.CacheConfigManager;
import com.challenge.geolocation.entity.GeoLocationEntity;
import com.challenge.geolocation.interactor.GeoLocationFactory;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ip-api/{ipAddress}")
//@Api(value="/ip-api/{ipAddress}", description="Operations on the hello object")
public class GeoLocationResource { //implements GeoLocationResourceSwaggerDoc {
    private final GeoLocationFactory factory;

    public GeoLocationResource(GeoLocationFactory factory) {
        this.factory = factory;
    }

    private GeoLocationEntity checkCacheForRecord(String ipAddress) {
      return CacheConfigManager.getInstance().initStudentCache(factory, ipAddress);
    }

    //@Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @UnitOfWork
    public GeoLocationEntity getGeoLocation(@PathParam("ipAddress") String ipAddress) {
        return checkCacheForRecord(ipAddress);
    }
}
