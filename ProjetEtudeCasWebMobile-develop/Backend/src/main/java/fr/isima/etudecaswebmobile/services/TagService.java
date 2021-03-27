package fr.isima.etudecaswebmobile.services;

import fr.isima.etudecaswebmobile.models.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {
    public Tag addTag(Tag tag );
    public List<Tag> getAllTags();
    public Tag getTagById(Long id) ;
    public Tag updateTagById(Tag tag, Long id) ;
    public ResponseEntity<Boolean> deleteTagById(Long id);
    public Tag addTagToLocation(Long location_id, Tag tag);
    public Tag addExistedTagToLocation(long location_id, long tag_id);

}
