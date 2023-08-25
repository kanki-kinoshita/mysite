package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Budgets;
import com.example.demo.Entity.Members;
import com.example.demo.Repository.BudgetsRepository;
import com.example.demo.Repository.MembersRepository;
import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class MenberController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	MembersRepository membersRepository;

	@Autowired
	BudgetsRepository budgetsRepository;

	@GetMapping("/regist")
	public String regist() {
		return "login";
	}

	@PostMapping("/regist")
	public String regist(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "balance", defaultValue = "") Integer balance,
			@RequestParam(name = "tFoodCost", defaultValue = "") Integer tFoodCost,
			@RequestParam(name = "tUtCost", defaultValue = "") Integer tUtCost,
			@RequestParam(name = "tTransCost", defaultValue = "") Integer tTransCost,
			@RequestParam(name = "tComCost", defaultValue = "") Integer tComCost,
			@RequestParam(name = "tRentCost", defaultValue = "") Integer tRentCost,
			@RequestParam(name = "tEntCost", defaultValue = "") Integer tEntCost,

			Model model) {
		if (email.length() == 0 || password.length() == 0 || name.length() == 0) {
			model.addAttribute("message1", "*は必須です");
			return "login";
		}
		if (balance == null || tFoodCost == null || tUtCost == null || tTransCost == null || tComCost == null
				|| tRentCost == null || tEntCost == null) {
			model.addAttribute("message1", "*は必須です");
			return "login";

		}

		Members members = new Members(name, email, password, balance);
		membersRepository.save(members);
		int[] budget = { tFoodCost, tUtCost, tTransCost, tComCost, tRentCost, tEntCost };
		for (int i = 0; i <= 5; i++) {
			Budgets budgets = new Budgets(members.getId(), (Integer) (i + 4), (Integer) budget[i]);
			budgetsRepository.save(budgets);
		}
		model.addAttribute("balance", budget);

		account.setId(members.getId());
		account.setName(members.getName());

		return "redirect:/login";
	}

}
