package fr.isima.etudecaswebmobile.repositories;

import fr.isima.etudecaswebmobile.entities.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long>
{
    Optional<TagEntity> findByTitleAndUserDaoUsername(String title, String userName);

    @Query("select t from TagEntity t where t.userDao.id= :user_id")
    List<TagEntity> getUserTags(@Param("user_id") long user_id);
}
