package com.example.demo.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Histories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Component
public class HistoryoriginRepositoryImpl implements HistoryoriginRepository {

	@Autowired
	EntityManager em;

	@Override
	public List<Histories> findAlltop(Integer memberId, String year, String month) {
		// SQL
		String sql = "SELECT * FROM histories where member_id = :memberId ";

		if (year != null && month != null) {
			if (month.equals("02")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-28' order by date_at";
			} else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-30' order by date_at";
			} else {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-31' order by date_at";
			}
		}

		Query query = em.createNativeQuery(sql, Histories.class);

		query.setParameter("memberId", memberId);

		// パラメータ

		// SQL 実行(参照系)
		@SuppressWarnings("unchecked")
		List<Histories> historyList = query.getResultList();

		return historyList;
	}

	@Override
	public int findAllincome(Integer memberId, String year, String month) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = "SELECT * FROM histories where member_id = :memberId and category_id<4";
		if (year != null && month != null) {
			if (month.equals("02")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-28' order by date_at";
			} else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-30' order by date_at";
			} else {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-31' order by date_at";
			}
		}
		Query query = em.createNativeQuery(sql, Histories.class);

		query.setParameter("memberId", memberId);

		@SuppressWarnings("unchecked")
		List<Histories> historyList = query.getResultList();
		int incometotal = 0;
		for (Histories history : historyList) {
			incometotal = incometotal + history.getPrice();

		}
		return incometotal;
	}

	@Override
	public int findAlloutcome(Integer memberId, String year, String month) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = "SELECT * FROM histories where member_id = :memberId and category_id>3";
		if (year != null && month != null) {
			if (month.equals("02")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-28' order by date_at";
			} else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-30' order by date_at";
			} else {
				sql += "and date_at between " + "'" + year + "-" + month + "-01' and '" + year + "-" + month
						+ "-31' order by date_at";
			}
		}
		Query query = em.createNativeQuery(sql, Histories.class);

		query.setParameter("memberId", memberId);

		@SuppressWarnings("unchecked")
		List<Histories> historyList = query.getResultList();
		int outcometotal = 0;
		for (Histories history : historyList) {
			outcometotal = outcometotal + history.getPrice();

		}
		return outcometotal;
	}

}
