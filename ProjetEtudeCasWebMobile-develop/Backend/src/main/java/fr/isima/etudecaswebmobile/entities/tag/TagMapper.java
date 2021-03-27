package fr.isima.etudecaswebmobile.entities.tag;

import fr.isima.etudecaswebmobile.entities.mapper.Mapper;
import fr.isima.etudecaswebmobile.models.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagEntity> {

    @Override
    public Tag toModel(TagEntity entity) {
        return new Tag(
                entity.getId_tag(),
                entity.getTitle()
        );
    }

    @Override
    public TagEntity fromModel(Tag model) {
        return new TagEntity(
                model.getId(),
                model.getLabel()
        );
    }
}
