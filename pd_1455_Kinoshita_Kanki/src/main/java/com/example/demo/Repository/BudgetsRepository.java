package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Budgets;

public interface BudgetsRepository extends JpaRepository<Budgets, Integer> {

	List<Budgets> findAllBymemberid(Integer id);

}
