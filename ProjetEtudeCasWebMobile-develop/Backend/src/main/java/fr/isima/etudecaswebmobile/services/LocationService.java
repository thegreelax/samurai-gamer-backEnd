package fr.isima.etudecaswebmobile.services;

import fr.isima.etudecaswebmobile.models.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LocationService {
    public Location addLocation(Location location) throws Exception;
    public List<Location> getAllLocations();
    public Location getLocationById(Long id) ;
    public Location updateLocationById(Location location, Long id) ;
    public ResponseEntity<Boolean> deleteLocationById(Long id) ;
    public List<Location> getLocationsByTag(@PathVariable long tag_id);
    public List<Location> findAllLocationsByUserId(Long id);
    public List<Location> findAllLocationsOfAnotherUserByTagTitles(String ownerUsername, List<String> tagTitles);
    public String shareLocationsWithAnotherUserByTagTitles(String ownerUsername, List<String> tagTitles);

}
