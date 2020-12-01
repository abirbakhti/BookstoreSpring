package com.vermeg.ams.controllers;

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
import com.vermeg.ams.repositories.BookRepository;
import com.vermeg.ams.repositories.CommandRepository;

@Controller
@RequestMapping("/command/")
public class CommandController {

	private final CommandRepository commandRepository;
	private static BookRepository bookRepository;

	@Autowired
	public CommandController(CommandRepository commandRepository, BookRepository bookRepository) {
		this.commandRepository = commandRepository;
		this.bookRepository = bookRepository;
	}

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

	@GetMapping("addToCart/{id}")
	public String addToCart(@PathVariable("id") long id, Model model) {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid command Id:" + id));
		book.setCart(!book.getCart());
		bookRepository.save(book);
		model.addAttribute("Totalprice", calculateTotalPrice());

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

	@GetMapping("add")

	public String showAddCommandForm(Model model) {

		List<Book> lb = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", lb);
		model.addAttribute("Totalprice", calculateTotalPrice());
		return "Command/addCommand";
	}

	@PostMapping("add")
	public String addCommand(@Valid Command command, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Command/addCommand";
		}
		commandRepository.save(command);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteCommand(@PathVariable("id") long id, Model model) {
		Command command = commandRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid command Id:" + id));
		commandRepository.delete(command);
		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showCommandFormToUpdate(@PathVariable("id") long id, Model model) {
		Command command = commandRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid command Id:" + id));
		model.addAttribute("command", command);
		return "command/updateCommand";
	}

	@PostMapping("update")
	public String updateCommand(@Valid Command command, BindingResult result, Model model) {
		if (result.hasErrors()) {

			return "command/updateCommand";
		}
		commandRepository.save(command);
		return "redirect:list";
	}
}
