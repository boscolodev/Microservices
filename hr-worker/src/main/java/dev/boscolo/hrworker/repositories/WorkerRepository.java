package dev.boscolo.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.boscolo.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
