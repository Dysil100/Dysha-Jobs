package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;/*package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import org.springframework.stereotype.Service;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeolocationService {
    private static final GeometryFactory GEOMETRY_FACTORY = JTSFactoryFinder.getGeometryFactory();
    private static final String GEONAMES_USERNAME = "YOUR_GEONAMES_USERNAME";

    private static Point getCurrentLocation() throws IOException, ParseException {
        // Simuler la récupération de la localisation actuelle (à partir d'un fichier GeoJSON)
        File geoJsonFile = new File("current_location.geojson");
        SimpleFeatureSource featureSource = DataUtilities.source(DataUtilities.fileToURL(geoJsonFile));
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();
        SimpleFeatureIterator iterator = featureCollection.features();
        SimpleFeature feature = iterator.next();
        Geometry geometry = (Geometry) feature.getDefaultGeometry();
        return (Point) geometry;
    }
    private static String getCountryName(Point point) throws Exception {
        WebService.setUserName(GEONAMES_USERNAME);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setLatitude(point.getY());
        searchCriteria.setLongitude(point.getX());
        ToponymSearchResult searchResult = WebService.search(searchCriteria);
        if (searchResult.getTotalResultsCount() > 0) {
            Toponym toponym = searchResult.getToponyms().get(0);
            return toponym.getCountryName();
        }
        return null;
    }

    private static String getRegionName(Point point) throws Exception {
        WebService.setUserName(GEONAMES_USERNAME);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setLatitude(point.getY());
        searchCriteria.setLongitude(point.getX());
        ToponymSearchResult searchResult = WebService.search(searchCriteria);
        if (searchResult.getTotalResultsCount() > 0) {
            Toponym toponym = searchResult.getToponyms().get(0);
            return toponym.getAdminName1();
        }
        return null;
    }

    private static String getCityName(Point point) throws Exception {
        WebService.setUserName(GEONAMES_USERNAME);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setLatitude(point.getY());
        searchCriteria.setLongitude(point.getX());
        ToponymSearchResult searchResult = WebService.search(searchCriteria);
        if (searchResult.getTotalResultsCount() > 0) {
            Toponym toponym = searchResult.getToponyms().get(0);
            return toponym.getName();
        }
        return null;
    }

    private static String getStreetName(Point point) throws Exception {
        WebService.setUserName(GEONAMES_USERNAME);
        StreetSearchCriteria searchCriteria = new StreetSearchCriteria();
        searchCriteria.setLatitude(point.getY());
        searchCriteria.setLongitude(point.getX());
        StreetSearchResult searchResult = WebService.findNearbyStreets(searchCriteria);
        if (searchResult.getStreetSegments().size() > 0) {
            StreetSegment streetSegment = searchResult.getStreetSegments().get(0);
            return streetSegment.getStreet().getName();
        }
        return null;
    }


    private static double calculateDistance(Point location1, Point location2) {
        return location1.distance(location2);
    }

    private static List<Point> filterLocationsInRadius(List<Point> locations, Point center, double radius) {
        List<Point> locationsInRadius = new ArrayList<>();

        for (Point location : locations) {
            double distance = location.distance(center);
            if (distance <= radius) {
                locationsInRadius.add(location);
            }
        }

        return locationsInRadius;
    }

    private static Point createPoint(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(GEOMETRY_FACTORY);
        return (Point) reader.read(wkt);
    }
}*/