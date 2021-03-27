package fr.isima.etudecaswebmobile.services;


import fr.isima.etudecaswebmobile.entities.user.UserDao;
import fr.isima.etudecaswebmobile.models.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService
{
    public UserDao getCurrentUser(String username);
}
