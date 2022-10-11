package com.challenge.geolocation.dao;

import com.challenge.geolocation.entity.GeoLocationEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class GeoLocationDao extends AbstractDAO<GeoLocationEntity> implements Dao {
    public GeoLocationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    @Override
    public GeoLocationEntity findByIpAddress(String ipAddress) {
        Query query = currentSession().createQuery("select g " +
                "                        from  geoLocation g"   +
                "                        where query = :ipAddress").setParameter("ipAddress", ipAddress);
        List<GeoLocationEntity> results = query.getResultList();
        if(!results.isEmpty()){
           return results.get(0);
        } else {
            return null;
        }
    }
    @Override
    public void saveOrUpdate(GeoLocationEntity entity) {
        persist(entity);
    }
}
