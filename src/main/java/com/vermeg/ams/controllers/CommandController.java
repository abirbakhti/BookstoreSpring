package com.vermeg.ams.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.Command;
import com.vermeg.ams.entities.LigneCommand;
import com.vermeg.ams.repositories.BookRepository;
import com.vermeg.ams.repositories.CommandRepository;
import com.vermeg.ams.repositories.LigneCommandRepository;
import com.vermeg.ams.controllers.CommandController;

@Controller
@RequestMapping("/command/")
public class CommandController {

	private final CommandRepository commandRepository;
	private static BookRepository bookRepository;
	private final LigneCommandRepository lignecommandRepository;
	
	public static long id_user = 2 ;

	
	
	@Autowired
	public CommandController(CommandRepository commandRepository, BookRepository bookRepository,
			LigneCommandRepository lignecommandRepository) {
		this.commandRepository = commandRepository;
		this.bookRepository = bookRepository;
		this.lignecommandRepository = lignecommandRepository;
	}
/*
	public static double calculateTotalPrice() {
		Double totalPrice = 0.0;
		List<Book> lb = (List<Book>) bookRepository.findAll();
		for (Book b : lb) {
			if (b.getCart() == true) {
				totalPrice += b.getPrice();
			}
		}

		return totalPrice;
	}
*/
	@GetMapping("addToCart/{id}")
	public String addToCart(@PathVariable("id") long id, Model model) {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid command Id:" + id));
	
		bookRepository.save(book);
		//model.addAttribute("Totalprice", calculateTotalPrice());

		return "redirect:../add";
	}

	@GetMapping("list")
	// @ResponseBody
	public String listCommand(Model model) {
		List<Command> lp = (List<Command>) commandRepository.findAll();

		if (lp.size() == 0)
			lp = null;

		model.addAttribute("commands", lp);
		return "Command/listCommands";
	}

	@GetMapping("listBooks")
	// @ResponseBody
	public String listBookForCommand(Model model) {
		List<Book> lp = (List<Book>) bookRepository.findAll();

		if (lp.size() == 0)
			lp = null;

		model.addAttribute("books", lp);
		return "Command/showBooks";
	}

	
	@GetMapping("listByUser")
	// @ResponseBody
	public String listCommandById(Model model) {
		List<Command> lp = (List<Command>) commandRepository.findCommandByIdUser(id_user);

		if (lp.size() == 0)
			lp = null;

		model.addAttribute("commands", lp);
		return "Command/listCommands";
	}
	@GetMapping("add")

	public String showAddCommandForm(Model model) {
/*
		List<Book> lb = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", lb);
		model.addAttribute("Totalprice", calculateTotalPrice());*/
		return "Command/addCommand";
	}

	@PostMapping("add")
	public String addCommand(BindingResult result, Model model) {
		/*if (result.hasErrors()) {
			return "Command/addCommand";
		}

		Command command = new Command(LocalDate.now(), CommandController.calculateTotalPrice());
		List<Book> lb = (List<Book>) bookRepository.findAll();
		List<Book> lbCart = new ArrayList<>();
		commandRepository.save(command);
		for (Book b : lb) {
			if (b.getCart()) {
				lbCart.add(b);
				b.setCart(false);
				b.setQuantity(b.getQuantity() - 1);
			}
			lignecommand.setBook(b);
			lignecommand.setCommand(command);
			lignecommandRepository.save(lignecommand);
		}*/
		
	//	Command command =new Command(LocalDate.now(), CommandController.calculateTotalPrice());
		return "redirect:../command/list";
	}

	@GetMapping("delete/{id}")
	public String deleteCommand(@PathVariable("id") long id, Model model) {
		Command command = commandRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid command Id:" + id));
		commandRepository.delete(command);
		return "redirect:../list";
	}

	
}
