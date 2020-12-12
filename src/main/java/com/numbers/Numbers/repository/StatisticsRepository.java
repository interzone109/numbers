package com.numbers.Numbers.repository;

import java.util.List;

import com.numbers.Numbers.entity.Statistics;

public interface StatisticsRepository {

	int save(Statistics statistics);

	int update(Statistics statistics);

	List<Statistics> findTopTen();

	Statistics findByNumber(int number);

	public Boolean isExist(int number);
}
