package com.vermeg.ams.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.Command;
import com.vermeg.ams.entities.LigneCommand;
import com.vermeg.ams.entities.User;
import com.vermeg.ams.repositories.BookRepository;
import com.vermeg.ams.repositories.CommandRepository;
import com.vermeg.ams.repositories.LigneCommandRepository;
import com.vermeg.ams.repositories.UserRepository;

@Controller
@RequestMapping("/lignecommand/")
public class LigneCommandController {
	private final LigneCommandRepository lignecommandRepository;
	private final CommandRepository commandRepository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	
	public static double calculateTotalPrice() {
		Double totalPrice = 0.0;
		
		for (Map.Entry<Book,Integer> m : PanierController.listbook.entrySet()) {
			totalPrice+= (m.getKey().getPrice())*m.getValue();
        }

		return totalPrice;
	}

	@Autowired
	public LigneCommandController(LigneCommandRepository lignecommandRepository ,CommandRepository commandRepository,BookRepository bookRepository,UserRepository userRepository) {
		this.lignecommandRepository = lignecommandRepository;
		this.commandRepository= commandRepository;
		this.bookRepository=bookRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("list")
	public String listBooks(Model model) {
		List<LigneCommand> lc = (List<LigneCommand>) lignecommandRepository.findAll();
		if (lc.size() == 0)
			lc = null;
		model.addAttribute("lignecommand", lc);
		return "lignecommand/listLignecommand";
	}
	
	@GetMapping("add")
	public String addLigneCommand(Model model) {
	 System.out.println(LoginController.email);
		/*
		Command c= new Command(LocalDate.now(),calculateTotalPrice());
		User u = userRepository.findByEmail(email);
		c.setUser(u);
		Command c1 = commandRepository.save(c);
		
		for (Map.Entry<Book,Integer> m : PanierController.listbook.entrySet()) {
			LigneCommand lc = new LigneCommand(c1,m.getKey(),m.getKey().getPrice(),m.getValue());
			m.getKey().setQuantity(m.getKey().getQuantity()-m.getValue());
			bookRepository.save(m.getKey());
			lignecommandRepository.save(lc);
			
        }*/
		return "redirect:../command/list";
	}
	
	
	
}