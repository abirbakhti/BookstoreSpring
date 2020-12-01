package com.vermeg.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.Command;
import com.vermeg.ams.exceptions.ResourceNotFoundException;
import com.vermeg.ams.repositories.CommandRepository;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping({ "/commands" })
public class CommandRestController {
	private final CommandRepository commandRepository;

	@Autowired
	public CommandRestController(CommandRepository commandRepository) {
		this.commandRepository = commandRepository;
	}

	@GetMapping("/list")
	public List<Command> getAllCommand() {
		return (List<Command>) commandRepository.findAll();
	}

	@PostMapping("/add")
	public Command createCommand(@Valid @RequestBody Command command) {
		return commandRepository.save(command);
	}

	@PutMapping("/{commandId}")
	public Command updateCommand(@PathVariable Long commandId, @Valid @RequestBody Command commandRequest) {
		return commandRepository.findById(commandId).map(command -> {
			command.setDate(commandRequest.getDate());
			command.setTotalPrice(commandRequest.getTotalPrice());
			return commandRepository.save(command);
		}).orElseThrow(() -> new ResourceNotFoundException("Command Id " + commandId + " not found"));
	}

	@DeleteMapping("/{commandId}")
	public ResponseEntity<?> deleteCommand(@PathVariable Long commandId) {
		return commandRepository.findById(commandId).map(command -> {
			commandRepository.delete(command);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Command Id " + commandId + " not found"));
	}
}
