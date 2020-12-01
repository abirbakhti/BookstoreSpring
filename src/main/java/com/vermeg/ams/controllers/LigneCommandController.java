package com.vermeg.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.LigneCommand;
import com.vermeg.ams.repositories.BookRepository;
import com.vermeg.ams.repositories.LigneCommandRepository;

@Controller
@RequestMapping("/lignecommand/")
public class LigneCommandController {
	private final LigneCommandRepository lignecommandRepository;

	@Autowired
	public LigneCommandController(LigneCommandRepository lignecommandRepository) {
		this.lignecommandRepository = lignecommandRepository;
	}

	@GetMapping("list")
	public String listBooks(Model model) {
		List<LigneCommand> lc = (List<LigneCommand>) lignecommandRepository.findAll();
		if (lc.size() == 0)
			lc = null;
		model.addAttribute("lignecommand", lc);
		return "lignecommand/listLignecommand";
	}
}