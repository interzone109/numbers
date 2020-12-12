package com.numbers.Numbers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.numbers.Numbers.entity.Statistics;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository{
	
	@Autowired
    private JdbcTemplate  jdbcTemplate;

	@Override
	public int save(Statistics statistics) {
		return jdbcTemplate.update("insert into statistics (number, count) values(?,?)", statistics.getNumb(), statistics.getCount());
		
	}

	@Override
	public int update(Statistics statistics) {
		jdbcTemplate.update("update statistics set count= ? where statistic_id= ?", statistics.getCount()+1, statistics.getId());
		return 0;
	}

	@Override
	public List<Statistics> findTopTen() {
		 return jdbcTemplate.query(
	                "select * from statistics order by count desc  limit 10", (rs, rowNum)->(
	                		new Statistics (rs.getLong("statistic_id"), rs.getInt("number"),rs.getInt("count")) 
	                		));
	}

	@SuppressWarnings("deprecation")
	@Override
	public Statistics findByNumber(int number) {
		return jdbcTemplate.queryForObject("select * from statistics where number =?",  new Object[]{number},
				(rs, rowNum)->( new Statistics (rs.getLong("statistic_id"), rs.getInt("number"),rs.getInt("count"))));
		
	}
	
	@SuppressWarnings("deprecation")
	public Boolean isExist(int number) {
		return jdbcTemplate.queryForObject("select Count(number) from statistics where number =?",  new Object[]{number},Integer.class)> 0;
	}
		

}
