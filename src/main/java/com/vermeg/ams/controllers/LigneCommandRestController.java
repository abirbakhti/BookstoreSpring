package com.vermeg.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.LigneCommand;
import com.vermeg.ams.repositories.LigneCommandRepository;

@RestController
@RequestMapping({ "/lignecommands" })
public class LigneCommandRestController {
	@Autowired
	private LigneCommandRepository lignecommandRepository;
	
	@GetMapping("/list")
	public List<LigneCommand> getAllLigneCommands() {
		return (List<LigneCommand>) lignecommandRepository.findAll();
	}
	
	@PostMapping("/add")
	public LigneCommand createLigneCommand(@Valid @RequestBody LigneCommand ligneCommand) {
		return lignecommandRepository.save(ligneCommand);
	}
}
