package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Members;
import com.example.demo.Repository.MembersRepository;
import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	MembersRepository membersRepository;

	@GetMapping({ "/", "login", "logout" })
	public String index() {

		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
			@RequestParam("password") String password, Model model) {
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力してください");
			return "login";
		}
		List<Members> memberList = membersRepository.findByEmailAndPassword(email, password);
		if (memberList == null || memberList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		Members members = memberList.get(0);

		account.setId(members.getId());
		account.setName(members.getName());

		return "redirect:/top";
	}

}
