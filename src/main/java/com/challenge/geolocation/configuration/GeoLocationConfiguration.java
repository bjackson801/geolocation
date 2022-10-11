package com.challenge.geolocation.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Setter
@Getter
public class GeoLocationConfiguration extends Configuration {
    private DataSourceFactory database;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        if (Objects.isNull(database))
            database = new DataSourceFactory();
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory database) {
        this.database = database;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String swaggerBasePath;
    public String getSwaggerBasePath(){ return swaggerBasePath; }
}
