package dev.boscolo.hroauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.boscolo.hroauth.dto.UserDTO;


@Component
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClient {

	@GetMapping(value = "/email")
	ResponseEntity<UserDTO> findByEmail(@RequestParam String email);
	
}
