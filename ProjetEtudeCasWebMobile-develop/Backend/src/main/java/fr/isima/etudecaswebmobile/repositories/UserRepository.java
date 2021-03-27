package fr.isima.etudecaswebmobile.repositories;


import fr.isima.etudecaswebmobile.entities.user.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDao, Integer> {

    @Query("select u from UserDao u where u.username= :username")
    UserDao findByUsername(@Param("username") String username);
    UserDao findById(Long id);
}
