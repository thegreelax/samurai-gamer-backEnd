package fr.isima.etudecaswebmobile.services.impl;


import fr.isima.etudecaswebmobile.entities.location.LocationEntity;
import fr.isima.etudecaswebmobile.entities.tag.TagEntity;
import fr.isima.etudecaswebmobile.entities.tag.TagMapper;
import fr.isima.etudecaswebmobile.entities.user.UserDao;
import fr.isima.etudecaswebmobile.exception.NoContentException;
import fr.isima.etudecaswebmobile.exception.NotFoundException;
import fr.isima.etudecaswebmobile.exception.UnauthorizedException;
import fr.isima.etudecaswebmobile.models.Tag;
import fr.isima.etudecaswebmobile.repositories.LocationRepository;
import fr.isima.etudecaswebmobile.repositories.TagRepository;
import fr.isima.etudecaswebmobile.services.JwtUserDetailsService;
import fr.isima.etudecaswebmobile.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class TagImpl implements TagService {


    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public Tag addTag(Tag tag) {

        if(tag.getId() == null) {
            TagEntity tagEntity = tagMapper.fromModel(tag);
            tagEntity.setUserDao(userDetailsService.getCurrentUser());
            return tagMapper.toModel(tagRepository.save(tagEntity));
        }else
            throw new UnauthorizedException("Tag exist");
    }

    @Override
    public List<Tag> getAllTags()
    {
        List<TagEntity> tagEntities = tagRepository.findAll();
        UserDao user = userDetailsService.getCurrentUser();
        List<TagEntity> tagEntitiesUser = tagRepository.getUserTags(user.getId());

        if (!tagEntitiesUser.isEmpty())
            return tagEntitiesUser.stream()
                    .map(tagMapper::toModel)
                    .collect(Collectors.toList());
        else
            throw new NoContentException("Tags Not Found");
    }

    @Override
    public Tag getTagById(Long id) {
        Optional<TagEntity> tagEntity = tagRepository.findById(id);
        if (tagEntity.isPresent())
            return tagMapper.toModel(tagEntity.get());
        else
            throw new NoContentException("The tag selected not Found");
    }

    @Override
    public Tag updateTagById(Tag newTag, Long id) {
        Optional<TagEntity> optionalTagEntity = tagRepository.findById(id);
        if (optionalTagEntity.isPresent()) {
            TagEntity oldTagEntity = optionalTagEntity.get();
            oldTagEntity.setTitle(newTag.getLabel());
            return tagMapper.toModel(tagRepository.save(oldTagEntity));
        }else
            throw new NotFoundException("Tag not found");
    }

    @Override
    public ResponseEntity<Boolean> deleteTagById(Long id) {
        Optional<TagEntity> tagEntityOptional = tagRepository.findById(id);
        if (tagEntityOptional.isPresent()) {
            TagEntity tagEntity = tagEntityOptional.get();
            List<LocationEntity> locationEntities = tagEntity.getLocationEntities();
            if (locationEntities != null) {
                locationEntities.forEach(locationEntity -> {
                    List<TagEntity> tagEntities = locationEntity.getTagEntities();
                    tagEntities.remove(locationEntity);
                    locationEntity.setTagEntities(tagEntities);
                });
                tagEntity.setLocationEntities(null);
            }
            tagRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Tag addTagToLocation(Long location_id, Tag tag)
    {
        TagEntity newTag = tagMapper.fromModel(tag);

        Optional<LocationEntity> locationOptional = locationRepository.findById(location_id);
        if (locationOptional.isPresent()) {
            LocationEntity location = locationOptional.get();

            List<LocationEntity> locationEntities = newTag.getLocationEntities();
            locationEntities.add(location);
            newTag.setLocationEntities(locationEntities);

            List<TagEntity> tagEntities = location.getTagEntities();
            tagEntities.add(newTag);
            location.setTagEntities(tagEntities);

            locationRepository.save(location);

            return tagMapper.toModel(tagRepository.save(newTag));
        } else
            throw new NotFoundException("Location Not Found");
    }

    @Override
    public Tag addExistedTagToLocation(long location_id, long tag_id)
    {
        TagEntity existedTag;
        Optional<TagEntity> tagEntityOptional = tagRepository.findById(tag_id);
        if (tagEntityOptional.isPresent())
            existedTag = tagEntityOptional.get();
        else
            throw new NotFoundException("Tag Not Found");

        Optional<LocationEntity> locationOptional = locationRepository.findById(location_id);
        if (locationOptional.isPresent()) {
            LocationEntity location = locationOptional.get();

            List<LocationEntity> locationEntities = existedTag.getLocationEntities();
            locationEntities.add(location);
            existedTag.setLocationEntities(locationEntities);

            List<TagEntity> tagEntities = location.getTagEntities();
            tagEntities.add(existedTag);
            location.setTagEntities(tagEntities);

            locationRepository.save(location);

            return tagMapper.toModel(tagRepository.save(existedTag));
        } else
            throw new NotFoundException("Location Not Found");
    }
}
