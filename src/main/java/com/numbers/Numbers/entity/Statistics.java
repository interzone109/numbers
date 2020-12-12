package com.numbers.Numbers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
 * the entity is responsible for storing the ratings of the numbers
 * */

@Data
@Entity
@Table(name = "statistics")
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "statistic_id", nullable = false)
	private long id;

	@Column(unique = true, name = "number", nullable = false)
	private int numb;

	@Column(name = "count", nullable = false)
	private int count;

	public Statistics(long id, int numb, int count) {

		this.id = id;
		this.numb = numb;
		this.count = count;
	}

	public Statistics() {

	}

}
