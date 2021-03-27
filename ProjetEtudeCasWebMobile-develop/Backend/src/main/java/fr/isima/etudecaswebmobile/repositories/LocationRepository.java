package fr.isima.etudecaswebmobile.repositories;

import fr.isima.etudecaswebmobile.entities.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long>
{
    @Query("select l from LocationEntity l  join l.tagEntities t where t.id_tag= :tag_id")
    Optional<List<LocationEntity>> getLocationsByTag(@Param("tag_id") long tag_id);

    @Query(value = "SELECT l.id_location, l.label, l.longitude, l.latitude " +
            "from location l " +
            "INNER JOIN locations_tags tl on l.id_location = tl.location_id " +
            "INNER JOIN tag t on tl.tag_id = t.id_tag where t.user_id = ?1", nativeQuery = true)
    Optional<List<LocationEntity>> findAllLocationsByUserId(Long id);

    @Query(value = "SELECT l.id_location, l.label, l.longitude, l.latitude " +
            "from location l " +
            "INNER JOIN locations_tags tl on l.id_location = tl.location_id " +
            "INNER JOIN tag t on tl.tag_id = t.id_tag " +
            "where t.title IN :tagTitles"
            , nativeQuery = true
    )
    List<LocationEntity> findAllLocationsByTagTitles(
            @Param("tagTitles") List<String> tagTitles
    );
}
