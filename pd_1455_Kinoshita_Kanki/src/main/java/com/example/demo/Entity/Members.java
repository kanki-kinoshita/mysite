package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String email;

	private String password;
	private Integer balance;

	public Members() {

	}

	public Members(String name, String email, String password, Integer balance) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.balance = balance;

	}

	public Members(String email, String password) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.email = email;
		this.password = password;
	}

	public Members(String name, String email, String password) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Integer getBalance() {
		return balance;
	}
}
