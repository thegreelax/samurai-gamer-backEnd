package fr.isima.etudecaswebmobile.entities.location;

import fr.isima.etudecaswebmobile.entities.mapper.Mapper;
import fr.isima.etudecaswebmobile.entities.tag.TagMapper;
import fr.isima.etudecaswebmobile.models.Location;
import fr.isima.etudecaswebmobile.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper implements Mapper<Location, LocationEntity> {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Location toModel(LocationEntity entity) {
        List<Tag> tags;
        if (entity.getTagEntities().isEmpty())
            tags = new ArrayList<>();
        else
            tags = entity.getTagEntities().stream()
                    .map(tagMapper::toModel)
                    .collect(Collectors.toList());
        return new Location(
                entity.getId_location(),
                entity.getLabel(),
                entity.getLongitude(),
                entity.getLatitude(),
                tags
        );
    }

    @Override
    public LocationEntity fromModel(Location model) {

        return new LocationEntity(
                model.getId(),
                model.getLabel(),
                model.getLongitude(),
                model.getLatitude()
        );
    }
}
