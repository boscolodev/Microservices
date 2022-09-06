package dev.boscolo.hruser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.boscolo.hruser.dto.UserDTO;
import dev.boscolo.hruser.entities.User;
import dev.boscolo.hruser.repositories.UserRepository;
import dev.boscolo.hruser.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> optionalUser = repository.findById(id);
		User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found."));
		return new UserDTO(user);
	}

	@Transactional(readOnly = true)
	public UserDTO findByEmail(String email) {
		Optional<User> optionalUser = repository.findByEmail(email);
		User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Email not found."));
		return new UserDTO(user);
	}
	
}
