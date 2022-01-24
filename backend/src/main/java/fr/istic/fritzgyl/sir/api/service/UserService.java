package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(long userId) {
		return userRepository.read(userId);
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public void removeUser(long userId) {
		userRepository.delete(getUser(userId));
	}

	public User updateUser(User currentUser) {
		return userRepository.update(currentUser);
	}

}
