package com.vermeg.ams.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/*@Controller
@RequestMapping("/lignecommand/")
public class LigneCommandController {
	@GetMapping("list")
	public String listArticles(Model model) {
		// model.addAttribute("articles", null);
		List<LigneCommand> la = (List<Article>) articleRepository.findAll();
		if (la.size() == 0)
			la = null;
		model.addAttribute("articles", la);
		return "article/listArticles";
	}
}*/
