package fr.isima.etudecaswebmobile.services;


import fr.isima.etudecaswebmobile.entities.user.UserDao;
import fr.isima.etudecaswebmobile.models.UserDto;
import fr.isima.etudecaswebmobile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDao save(UserDto user)
	{
		UserDao newUser = new UserDao();
		if (user.getId()!=null)
			newUser.setId(user.getId());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		return userDao.save(newUser);
	}

	public UserDao getUserById(Long id)
	{
		return userDao.findById(id);
	}

	public UserDao getUserByUsername(String username)
	{
		return userDao.findByUsername(username);
	}

	public UserDao getCurrentUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return userDao.findByUsername(username);
	}

	public UserDao update(UserDto newUser, Long id)
	{
		UserDao oldUser = this.getUserById(id);
		oldUser.setUsername(newUser.getUsername());
		oldUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		oldUser.setEmail(newUser.getEmail());
		oldUser.setFirstName(newUser.getFirstName());
		oldUser.setLastName(newUser.getLastName());
		return userDao.save(oldUser);
	}

}
