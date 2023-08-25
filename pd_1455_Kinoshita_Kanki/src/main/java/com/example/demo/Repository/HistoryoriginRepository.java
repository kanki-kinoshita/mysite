package com.example.demo.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Histories;

@Repository
public interface HistoryoriginRepository {
	List<Histories> findAlltop(Integer memberId, String year, String month);

	int findAllincome(Integer memberId, String year, String month);

	int findAlloutcome(Integer memberId, String year, String month);

}