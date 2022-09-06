package dev.boscolo.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import dev.boscolo.hrpayroll.entities.Payment;
import dev.boscolo.hrpayroll.entities.dto.PaymentDTO;
import dev.boscolo.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;
	
	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	@GetMapping(value = "{workerId}/days/{days}")
	public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
		Payment payment = service.getPayment(workerId, days);
		return ResponseEntity.ok().body(new PaymentDTO(payment));
		
	}
	
	public ResponseEntity<PaymentDTO> getPaymentAlternative(Long workerId, Integer days){
		Payment payment = new Payment("Jr", 400.0, days);
		return ResponseEntity.ok().body(new PaymentDTO(payment));
	}

	
}
