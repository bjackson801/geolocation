package com.challenge.geolocation;

import com.challenge.geolocation.configuration.GeoLocationConfiguration;
import com.challenge.geolocation.dao.GeoLocationDao;
import com.challenge.geolocation.entity.GeoLocationEntity;
import com.challenge.geolocation.geoservice.IPGeolocationService;
import com.challenge.geolocation.interactor.GeoLocationFactory;
import com.challenge.geolocation.resources.GeoLocationResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class GeoLocation extends Application<GeoLocationConfiguration> {


    public static void main(String[] args) throws Exception {
        new GeoLocation().run(args);
    }

    @Override
    public void initialize(Bootstrap<GeoLocationConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    public void run(GeoLocationConfiguration configuration, Environment environment) throws Exception {
        GeoLocationDao geoLocationDao = new GeoLocationDao(hibernateBundle.getSessionFactory());
         var ipGeolocation = new IPGeolocationService();
        GeoLocationFactory factory = new GeoLocationFactory(geoLocationDao, ipGeolocation);
        environment.jersey().register(new GeoLocationResource(factory));
     //   initSwagger(configuration, environment);
    }

//    private void initSwagger(GeoLocationConfiguration configuration, Environment environment) {
//        environment.jersey().register(new ApiListingResourceJSON());
//
//        // Swagger providers
//        environment.jersey().register(new ApiDeclarationProvider());
//        environment.jersey().register(new ResourceListingProvider());
//
//        // Swagger Scanner, which finds all the resources for @Api Annotations
//        ScannerFactory.setScanner(new DefaultJaxrsScanner());
//
//        // Add the reader, which scans the resources and extracts the resource information
//        ClassReaders.setReader(new DefaultJaxrsApiReader());
//
//        // required CORS support
//        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
//        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//        filter.setInitParameter("allowedOrigins", "*"); // allowed origins comma separated
//        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
//        filter.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE,OPTIONS,HEAD");
//        filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months
//        filter.setInitParameter("allowCredentials", "true");
//
//        // Set the swagger config options
//        SwaggerConfig config = ConfigFactory.config();
//        config.setApiVersion("1.0.1");
//        config.setBasePath(configuration.getSwaggerBasePath());
//    }

    HibernateBundle<GeoLocationConfiguration> hibernateBundle =
            new HibernateBundle<GeoLocationConfiguration>(GeoLocationEntity.class) {
                @Override
                public PooledDataSourceFactory getDataSourceFactory(GeoLocationConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

}
