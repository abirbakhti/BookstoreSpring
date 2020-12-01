package com.vermeg.ams.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vermeg.ams.entities.Command;

public interface CommandRepository extends CrudRepository<Command, Long> {

}
