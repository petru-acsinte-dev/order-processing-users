package com.orderprocessing.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderprocessing.users.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

	Optional<Status> findByStatus(String status);

}
