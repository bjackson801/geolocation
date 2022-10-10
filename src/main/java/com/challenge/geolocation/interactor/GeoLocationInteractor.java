package com.challenge.geolocation.interactor;

import com.challenge.geolocation.dao.Dao;
import com.challenge.geolocation.dto.GeoLocationDto;
import com.challenge.geolocation.entity.GeoLocationEntity;
import com.challenge.geolocation.exceptions.DataBaseException;
import com.challenge.geolocation.geoservice.IPGeolocation;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class GeoLocationInteractor {

    private final Dao geoLocationDao;
    private final IPGeolocation ipGeolocation;

    GeoLocationEntity entity;

    public GeoLocationInteractor(Dao geoLocationDao, IPGeolocation ipGeolocation) {
        this.geoLocationDao = geoLocationDao;
        this.ipGeolocation = ipGeolocation;
    }
    public GeoLocationEntity execute(String ipAddress) {
        log.info("Starting ip lookup for ipAddress: " + ipAddress);

        try {

            var ipRecord = doesRecordAlreadyExist(ipAddress);

            if(ipRecord != null){
                return ipRecord;
            }else {
                var response = ipGeolocation.callIpApi(ipAddress);
                var geoEntity = createEntityResult(response);
                saveNewResult(geoEntity);
                return geoEntity;
            }
        }catch (URISyntaxException ex) {
            log.error("I failed Please help me URISyntaxException error: " + ex.getMessage());
        }catch (IOException ex){
            log.error("I failed Please help me IOException error: " + ex.getMessage());
        }catch (InterruptedException ex) {
            log.error("I failed Please help me InterruptedException error: " + ex.getMessage());
        }catch (DataBaseException ex){
            log.error("Failed to lookup ipaddress. Failed with error: " + ex.getMessage());
        }
        return null;
    }
    private void saveNewResult(GeoLocationEntity body) {
        geoLocationDao.saveOrUpdate(body);
    }
    private GeoLocationEntity doesRecordAlreadyExist(String ipAddress) throws DataBaseException {

        return geoLocationDao.findByIpAddress(ipAddress);
    }
    private GeoLocationEntity createEntityResult(GeoLocationDto response) {
        var geoLocationEntity = new GeoLocationEntity();
        geoLocationEntity.setStatus(response.getStatus());
        geoLocationEntity.setMessage(response.getMessage());
        geoLocationEntity.setContinent(response.getContinent());
        geoLocationEntity.setContinentCode(response.getContinentCode());
        geoLocationEntity.setCountry(response.getCountry());
        geoLocationEntity.setCountryCode(response.getCountryCode());
        geoLocationEntity.setRegion(response.getRegion());
        geoLocationEntity.setRegionName(response.getRegionName());
        geoLocationEntity.setCity(response.getCity());
        geoLocationEntity.setDistrict(response.getDistrict());
        geoLocationEntity.setZip(response.getZip());
        geoLocationEntity.setLat(response.getLat());
        geoLocationEntity.setLon(response.getLon());
        geoLocationEntity.setTimezone(response.getTimezone());
        geoLocationEntity.setOffset(response.getOffset());
        geoLocationEntity.setCurrency(response.getCurrency());
        geoLocationEntity.setIsp(response.getIsp());
        geoLocationEntity.setOrg(response.getOrg());
        geoLocationEntity.setServiceName(response.getAs());
        geoLocationEntity.setAsname(response.getAsName());
        geoLocationEntity.setReverse(response.getReverse());
        geoLocationEntity.setMobile(response.isMobile());
        geoLocationEntity.setProxy(response.isProxy());
        geoLocationEntity.setHosting(response.isHosting());
        geoLocationEntity.setQuery(response.getQuery());
        return geoLocationEntity;
    }
}
