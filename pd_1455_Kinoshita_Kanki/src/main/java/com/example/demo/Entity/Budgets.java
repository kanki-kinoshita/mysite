package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "budgets")
public class Budgets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "member_id")
	private Integer memberid;
	@Column(name = "category_id")
	private Integer categoryid;
	private Integer price;

	@Transient
	private String categoryname;

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Budgets() {

	}

	public Budgets(Integer id, Integer memberid, Integer categoryid, Integer price) {
		this.id = id;
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.price = price;

	}

	public Budgets(Integer memberid, Integer categoryid, Integer price) {
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.price = price;

	}

	public Budgets(String categoryname) {
		this.categoryname = categoryname;
	}

	public Integer getId() {
		return id;
	}

	public Integer getMemberid() {
		return memberid;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public Integer getPrice() {
		return price;
	}

}
