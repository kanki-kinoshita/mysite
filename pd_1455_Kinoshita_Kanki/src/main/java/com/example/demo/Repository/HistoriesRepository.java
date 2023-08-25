package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Histories;

public interface HistoriesRepository extends JpaRepository<Histories, Integer> {

	List<Histories> findAllBymemberid(Integer id);

}
