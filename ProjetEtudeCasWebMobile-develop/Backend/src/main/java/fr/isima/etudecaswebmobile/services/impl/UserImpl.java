package fr.isima.etudecaswebmobile.services.impl;

import fr.isima.etudecaswebmobile.entities.user.UserDao;
import fr.isima.etudecaswebmobile.models.UserDto;
import fr.isima.etudecaswebmobile.repositories.UserRepository;
import fr.isima.etudecaswebmobile.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    public UserDao getCurrentUser(String username)
    {
        UserDao user = userRepository.findByUsername(username);
        return user;
    }
}
