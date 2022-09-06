package dev.boscolo.hrworker.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.boscolo.hrworker.dto.WorkerDTO;
import dev.boscolo.hrworker.entities.Worker;
import dev.boscolo.hrworker.repositories.WorkerRepository;
import dev.boscolo.hrworker.services.exceptions.ResourceNotFoundException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;
	
	@Transactional(readOnly = true)
	public List<WorkerDTO> findAll(){
		List<Worker> list = repository.findAll();
		return list.stream().map(x-> new WorkerDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public WorkerDTO findById(Long id) {
		Optional<Worker> optionalWorker = repository.findById(id);
		Worker worker = optionalWorker.orElseThrow( () -> new ResourceNotFoundException("Entity not found."));
		return new WorkerDTO(worker);
	}
}
