package pl.coderslab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findBySupervisor(User user) {
		return userRepository.findBySupervisor(user);
	}

	@Override
	public List<User> findAllAdmins() {
		return userRepository.findByRole_Name("ROLE_ADMIN");
		// or: userRepository.findAllByRoleName("ROLE_ADMIN");
	}


}
