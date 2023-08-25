package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Budgets;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Histories;
import com.example.demo.Repository.BudgetsRepository;
import com.example.demo.Repository.CategoriesRepository;
import com.example.demo.Repository.HistoriesRepository;
import com.example.demo.Repository.HistoryoriginRepository;
import com.example.demo.Repository.HistoryoriginRepositoryImpl;
import com.example.demo.model.Account;
import com.example.demo.model.yymm;

@Controller
public class NoteController {

	@Autowired
	Account account;
	@Autowired
	HistoryoriginRepository historyoriginRepository;

	@Autowired
	HistoryoriginRepositoryImpl historyoriginRepositoryImpl;

	@Autowired
	HistoriesRepository historiesRepository;

	@Autowired
	CategoriesRepository categoriesrepository;
	@Autowired
	BudgetsRepository budgetsRepository;
	@Autowired
	yymm yymm;

	private final static DateTimeFormatter FMTyear = DateTimeFormatter.ofPattern("yyyy");
	private final static DateTimeFormatter FMTmonth = DateTimeFormatter.ofPattern("MM");

	@GetMapping("/top")

	public String index(@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "date", defaultValue = "") Integer date,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			@RequestParam(value = "balance", defaultValue = "") Integer balance,
			Model model) {

		String year = LocalDate.now().format(FMTyear);
		String month = LocalDate.now().format(FMTmonth);
		yymm.setYear(year);
		yymm.setMonth(month);

		List<Histories> historiesList = historyoriginRepository.findAlltop(account.getId(), yymm.getYear(),
				yymm.getMonth());

		for (Histories histories : historiesList) {
			String categoryname = trans(histories.getCategoryid());
			histories.setCategoryname(categoryname);
		}

		List<Budgets> budgetList = budgetsRepository.findAllBymemberid(account.getId());
		int budgetTotal = 0;
		for (Budgets budget : budgetList) {
			budgetTotal += budget.getPrice();
		}
		Integer incometotal = historyoriginRepository.findAllincome(account.getId(), year, month);
		Integer outcometotal = historyoriginRepository.findAlloutcome(account.getId(), year, month);
		model.addAttribute("outcometotal", outcometotal);
		model.addAttribute("incometotal", incometotal);
		model.addAttribute("histories", historiesList);
		model.addAttribute("budgetTotal", budgetTotal);

		return "top";
	}

	@GetMapping("/outcomeRegist")
	public String index(Model model) {
		List<Categories> categoriesList = categoriesrepository.findAll();
		for (int i = 0; i <= 2; i++) {
			categoriesList.remove(0);
		}
		model.addAttribute("category", categoriesList);

		return "outcomeResist";
	}

	@PostMapping("/outcomeRegist")
	public String outcomeRegist(
			@RequestParam(name = "categoryid", defaultValue = "") Integer categoryid,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "categoryname", defaultValue = "") String categoryname,
			@RequestParam(name = "date", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestParam(name = "memo", defaultValue = "") String memo,

			Model model) {

		Histories histories = new Histories(account.getId(), categoryid, price * -1, date, memo);
		historiesRepository.save(histories);
		return "redirect:/top";
	}

	public String trans(Integer categoryId) {
		Categories category = categoriesrepository.findById(categoryId).get();
		return category.getName();
	}

	public Integer trans(String categoryName) {
		Categories category = categoriesrepository.findByName(categoryName).get();
		return category.getId();
	}

	@GetMapping("/incomeRegist")
	public String incomeRegist(Model model) {
		List<Budgets> budgertsList = budgetsRepository.findAllBymemberid(account.getId());
		for (Budgets budgets : budgertsList) {
			String categoryname = trans(budgets.getCategoryid());
			budgets.setCategoryname(categoryname);
		}
		model.addAttribute("category", budgertsList);
		return "income";
	}

	@PostMapping("/incomeRegist")
	public String incomeRegist(@RequestParam(name = "categoryid", defaultValue = "") Integer categoryid,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "date", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		Histories histories = new Histories(account.getId(), categoryid, price, date, memo);
		historiesRepository.save(histories);
		return "redirect:/top";
	}

	public String trans1(Integer categoryId) {
		Categories category = categoriesrepository.findById(categoryId).get();
		return category.getName();
	}

	public Integer trans1(String categoryName) {
		Categories category = categoriesrepository.findByName(categoryName).get();
		return category.getId();
	}

	@GetMapping("/detail")
	public String showdetail(Model model) {
		String currentmonth = yymm.getMonth();
		if (Integer.parseInt(yymm.getMonth()) < 10) {
			currentmonth = yymm.getMonth().substring(1);
		}
		List<Histories> historiesList = historyoriginRepository.findAlltop(account.getId(), yymm.getYear(),
				yymm.getMonth());
		for (Histories histories : historiesList) {
			String categoryname = trans(histories.getCategoryid());
			histories.setCategoryname(categoryname);
		}
		model.addAttribute("month", Integer.parseInt(currentmonth));
		model.addAttribute("histories", historiesList);
		return "detail";
	}

	@GetMapping("/detail/before")
	public String backdetail(Model model) {
		yymm.MinusCurrent();
		String currentmonth = yymm.getMonth();
		if (Integer.parseInt(yymm.getMonth()) < 10) {
			currentmonth = yymm.getMonth().substring(1);
		}
		List<Histories> historiesList = historyoriginRepository.findAlltop(account.getId(), yymm.getYear(),
				yymm.getMonth());
		for (Histories histories : historiesList) {
			String categoryname = trans(histories.getCategoryid());
			histories.setCategoryname(categoryname);
		}
		model.addAttribute("month", Integer.parseInt(currentmonth));
		model.addAttribute("histories", historiesList);
		return "detail";

	}

	@GetMapping("/detail/after")
	public String godetail(Model model) {
		yymm.PlusCurrent();
		String currentmonth = yymm.getMonth();
		if (Integer.parseInt(yymm.getMonth()) < 10) {
			currentmonth = yymm.getMonth().substring(1);
		}
		List<Histories> historiesList = historyoriginRepository.findAlltop(account.getId(), yymm.getYear(),
				yymm.getMonth());
		for (Histories histories : historiesList) {
			String categoryname = trans(histories.getCategoryid());
			histories.setCategoryname(categoryname);
		}
		model.addAttribute("month", Integer.parseInt(currentmonth));
		model.addAttribute("histories", historiesList);
		return "detail";
	}

	@GetMapping("/addcategory")
	public String showadd() {
		return "addcategory";
	}

	@PostMapping("/addcategory")
	public String add(@RequestParam(name = "categoryname") String categoryname, Model model) {
		Categories categories = new Categories(categoryname);

		categoriesrepository.save(categories);

		return "redirect:/top";

	}

	@GetMapping("/history/edit/{id}")
	public String showEdit(@PathVariable("id") Integer id, Model model) {
		List<Categories> categoriesList = categoriesrepository.findAll();
		Histories histories = historiesRepository.findById(id).get();
		model.addAttribute("histories", histories);
		model.addAttribute("category", categoriesList);
		model.addAttribute("id", id);
		return "edit";

	}

	@PostMapping("/history/edit/{id}")
	public String edit(@PathVariable("id") Integer id,
			@RequestParam(value = "categoryid", defaultValue = "") Integer categoryid,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "date", defaultValue = "") LocalDate date,
			@RequestParam(value = "memo", defaultValue = "") String memo,

			Model model) {

		Histories histories = new Histories(id, account.getId(), categoryid, price, date, memo);
		historiesRepository.save(histories);
		return "redirect:/top";
	}

	@GetMapping("/history/delete/{id}")
	public String showdelete(@PathVariable("id") Integer id, Model model) {
		Histories histories = historiesRepository.findById(id).get();
		String categoryName = trans(histories.getCategoryid());
		histories.setCategoryname(categoryName);
		model.addAttribute("histories", histories);
		return "delete";

	}

	@PostMapping("/history/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			@RequestParam(value = "categoryname", defaultValue = "") String categoryname,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "date", defaultValue = "") LocalDate date,
			@RequestParam(value = "memo", defaultValue = "") String memo,

			Model model) {
		/*Histories histories = new Histories(id, account.getId(), trans(categoryname), price, date, memo);
		model.addAttribute("histories", histories);*/
		historiesRepository.deleteById(id);
		return "redirect:/top";
	}

}
