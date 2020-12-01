package com.vermeg.ams.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vermeg.ams.entities.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {

	@Query(value = "SELECT * FROM command a WHERE a.user_id = ?1", nativeQuery = true)
	List<Command> findCommandByIdUser(long id);

	

}
