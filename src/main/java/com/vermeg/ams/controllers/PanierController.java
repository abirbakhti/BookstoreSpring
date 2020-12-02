package com.vermeg.ams.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vermeg.ams.entities.Book;

@Controller
@RequestMapping("/panier/")
public class PanierController {
    
	
    public static Map<Book, Integer> listbook = new HashMap<Book, Integer>();
   
    public static double calculateTotalPrice() {
		Double totalPrice = 0.0;
		
		for (Map.Entry<Book,Integer> m : PanierController.listbook.entrySet()) {
			totalPrice+= (m.getKey().getPrice())*m.getValue();
        }

		return totalPrice;
	}
	
	
	
	@GetMapping("listpanier")
	// @ResponseBody
	public String listBooksPanier(Model model) {
		model.addAttribute("books", listbook);
		model.addAttribute("totalPrice", calculateTotalPrice());
		return "Panier/showPanier";
	}
	
	

	@PostMapping("addpanier")
	//@ResponseBody
	public String addToPanier(Model model, @RequestParam("vendu")List<Book> listBooks) {

		listbook.clear();
		for(Book b : listBooks) {
			listbook.put(b, 1);
			}
		model.addAttribute("books", listbook);

		return "redirect:listpanier";
	}
	
	
	@PostMapping("updateQuantity")
	//@ResponseBody
	public String updateQuantity(Model model,@RequestParam("id")long id,@RequestParam("quantity")int quantity) {
		
		for (Map.Entry<Book,Integer> m : listbook.entrySet()) {
            if(m.getKey().getId()==id) {
            	m.setValue(quantity);
            }
        }

		model.addAttribute("books", listbook);
		return "redirect:listpanier";

	}
	
	@GetMapping("delete/{id}")
	//@ResponseBody
	public String deleteBookFromPanier(@PathVariable("id") long id, Model model) {
		Book b =new Book();
		
		for (Map.Entry<Book,Integer> m : listbook.entrySet()) {
			if(m.getKey().getId()== id) {
            	 b = m.getKey();
            }
        }
		
		listbook.remove(b);
		

		return "redirect:../listpanier";
	}
	
	@GetMapping("cart")
	//@ResponseBody
	public String cart(Model model) {
		
		
		

		return "redirect:listpanier";
	}
	
	
	
	
	
	
	
}
