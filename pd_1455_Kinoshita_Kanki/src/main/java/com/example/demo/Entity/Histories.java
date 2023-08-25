package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Histories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "member_id")
	private Integer memberid;

	@Column(name = "category_id")
	private Integer categoryid;

	private Integer price;

	@Column(name = "date_at")
	private LocalDate date;

	private String memo;

	@Transient
	private String categoryname;

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Histories() {
		super();
	}

	public Histories(Integer memberid, Integer categoryid, Integer price, LocalDate date) {
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.price = price;
		this.date = date;

	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Histories(Integer id, Integer memberid, Integer categoryid, Integer price, LocalDate date, String memo) {
		this.id = id;
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.price = price;
		this.date = date;
		this.memo = memo;

	}

	public Histories(Integer memberid, Integer categoryid, Integer price, LocalDate date, String memo) {
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.price = price;
		this.date = date;
		this.memo = memo;

	}

	public Histories(String categoryname) {
		this.categoryname = categoryname;
	}

	public Histories(Integer id, String categoryname, Integer price) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.id = id;
		this.categoryname = categoryname;
		this.price = price;
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

	public LocalDate getDate() {
		return date;
	}

	public String getMemo() {
		return memo;
	}

}
