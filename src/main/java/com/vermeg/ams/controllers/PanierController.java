package com.vermeg.ams.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vermeg.ams.entities.Book;

@Controller
@RequestMapping("/panier/")
public class PanierController {
    
	
     Map<Book, Integer> listbook = new HashMap<Book, Integer>();
     List< Map<Book, Integer>> list =  new ArrayList<>();
	
	
	
	@GetMapping("listpanier")
	// @ResponseBody
	public String listBooksPanier(Model model) {
		 Book b = new Book(1,"aaa","aaaa",154.0,2,"2000-02-02","i2.jpg");
	     listbook.put(b, 1);
	     list.add(listbook);
		if (listbook.size() == 0)
			listbook = null;
		model.addAttribute("books", list);
		return "Panier/showPanier";
	}
	
	
	
	
}
