package com.challenge.geolocation.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity(name = "geoLocation")
@Getter
@Setter
public class GeoLocationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String status;
    @Column
    private String message;
    @Column
    private String continent;
    @Column
    private String continentCode;
    @Column
    private String country;
    @Column
    private String countryCode;
    @Column
    private String region;
    @Column
    private String regionName;
    @Column
    private String city;
    @Column
    private String district;
    @Column
    private String zip;
    @Column
    private float lat;
    @Column
    private float lon;
    @Column(name = "timezone")
    private String timezone;
    @Column
    private int offset;
    @Column
    private String currency;
    @Column
    private String isp;
    @Column
    private String org;
    @Column
    private String serviceName;
    @Column
    private String asname;
    @Column
    private String reverse;
    @Column
    private boolean mobile;
    @Column
    private boolean proxy;
    @Column
    private boolean hosting;
    @Column
    private String query;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date lastModifiedDate;


}
