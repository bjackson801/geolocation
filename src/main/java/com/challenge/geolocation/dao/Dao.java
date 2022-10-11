package com.challenge.geolocation.dao;

import com.challenge.geolocation.dto.GeoLocationDto;
import com.challenge.geolocation.entity.GeoLocationEntity;
import com.challenge.geolocation.exceptions.DataBaseException;

import java.util.List;

public interface Dao {
    GeoLocationEntity findByIpAddress(String ipAddress) throws DataBaseException;
    void saveOrUpdate(GeoLocationEntity entity);
}
