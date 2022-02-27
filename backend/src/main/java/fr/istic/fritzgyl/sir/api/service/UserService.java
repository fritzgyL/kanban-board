package fr.istic.fritzgyl.sir.api.service;

import org.jasypt.util.password.StrongPasswordEncryptor;

import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.exception.DataNotFoundException;
import fr.istic.fritzgyl.sir.api.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(long userId) {
		User user = userRepository.read(userId);
		if (user == null) {
			throw new DataNotFoundException("User with id " + userId + " not found");
		}
		return user;
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public void removeUser(long userId) {
		User user = getUser(userId);
		if (user == null) {
			throw new DataNotFoundException("User with id " + userId + " not found");
		} else {
			userRepository.delete(user);
		}
	}

	public User updateUser(User currentUser) {
		return userRepository.update(currentUser);
	}

	public User signin(User user) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		User mUser = userRepository.login(user.getEmail());
		if (mUser != null) {
			return passwordEncryptor.checkPassword(user.getPassword(),mUser.getPassword()) ? mUser : null;
		}
		return mUser;
	}

	public User signup(User user) {
		User mUser = null;
		if (user.getEmail() != "" && user.getPassword() != "") {
			if (!userRepository.checkIfIsEmailTaken(user.getEmail())) {
				StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
				String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
				mUser = user;
				mUser.setPassword(encryptedPassword);
				System.out.println(mUser.getPassword());
				return userRepository.save(mUser);
			}
		}
		return mUser;
	}
}
