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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.repositories.BookRepository;

@Controller
@RequestMapping("/book/")
public class BookController {
	private final BookRepository bookRepository;
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@GetMapping("list")
	// @ResponseBody
	public String listBooks(Model model) {
		List<Book> lp = (List<Book>) bookRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		model.addAttribute("books", lp);
		return "book/listBooks";
	}
	
	

	
	@GetMapping("add")
	public String showAddBookForm(Model model) {
		Book book = new Book();// object dont la valeur des attributs par defaut
		model.addAttribute("book", book);
		return "book/addBook";
	}

	@PostMapping("add")
	//@ResponseBody
	public String addBook(@Valid Book book, BindingResult result,@RequestParam("files") MultipartFile[] files ) {
	
		 StringBuilder fileName = new StringBuilder();
	     MultipartFile file = files[0];
	     
	     
	
		 Path fileNameAndPath = Paths.get(uploadDirectory,
		file.getOriginalFilename());

		 fileName.append(file.getOriginalFilename());
		 try {
		Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
		e.printStackTrace();
		}

		
		
		if (result.hasErrors()) {
			return "book/addBook";
		}
		book.setPicture(fileName.toString());
		bookRepository.save(book);
		return "redirect:list";
		//return book.toString();
	}
	
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
		bookRepository.delete(book);
		return "redirect:../list";
	}
	
	@GetMapping("edit/{id}")
	public String showBookFormToUpdate(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
		model.addAttribute("book", book);
		return "book/updateBook";
	}

	@PostMapping("update")
	public String updateBook(@Valid Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "book/updateBook";
		}
		bookRepository.save(book);
		return "redirect:list";
	}
}
