package fr.isima.etudecaswebmobile.controllers;

import fr.isima.etudecaswebmobile.models.Tag;
import fr.isima.etudecaswebmobile.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public ResponseEntity<Tag> addTag(@Validated @RequestBody Tag tag) {
        return new ResponseEntity<>(this.tagService.addTag(tag), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> getAll() {
        return new ResponseEntity<>(this.tagService.getAllTags(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tag> getTagById(@PathVariable long id) {
        return new ResponseEntity<>(this.tagService.getTagById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Tag> updateTagById(@Validated @RequestBody Tag newTag, @PathVariable long id)
    {
        return new ResponseEntity<>(this.tagService.updateTagById(newTag, id),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteTagById(@PathVariable long id)
    {
        return tagService.deleteTagById(id);
    }

    @RequestMapping(value = "/tag/location/{location_id}", method = RequestMethod.POST)
    public ResponseEntity<Tag> addTagToLocation(@PathVariable long location_id, @Validated @RequestBody Tag tag)
    {
        return new ResponseEntity<>(this.tagService.addTagToLocation(location_id, tag), HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{tag_id}/location/{location_id}", method = RequestMethod.PUT)
    public ResponseEntity<Tag> addExistedTagToLocation(@PathVariable long location_id, @PathVariable long tag_id)
    {
        return new ResponseEntity<>(this.tagService.addExistedTagToLocation(location_id, tag_id), HttpStatus.OK);
    }

}
