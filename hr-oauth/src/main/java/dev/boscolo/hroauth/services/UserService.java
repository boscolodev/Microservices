package dev.boscolo.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.boscolo.hroauth.dto.UserDTO;
import dev.boscolo.hroauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public UserDTO findByEmail(String email) {
		UserDTO userDTO = userFeignClient.findByEmail(email).getBody();
		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = userFeignClient.findByEmail(username).getBody();
		if(userDTO == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found.");
		}
		logger.info("User found: " + username);
		
		return userDTO;
	}
}
