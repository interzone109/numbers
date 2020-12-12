package com.numbers.Numbers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.numbers.Numbers.entity.Statistics;
import com.numbers.Numbers.repository.StatisticsRepository;

/**
 *   main Number cintroller
 * 
 * */

@Controller
public class NumberController {

	@Autowired
	private StatisticsRepository jdbcRepository;

	@RequestMapping(path = { "/number/{number}" }, method = RequestMethod.GET)
	public @ResponseBody String index(@PathVariable("number") Integer number) {

		final String uri = "http://numbersapi.com/" + number;

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		Thread thread = new Thread() {

			@Override
			public void run() {
				Statistics optionStat = null ;
				if (jdbcRepository.isExist(number)) {
					optionStat = jdbcRepository.findByNumber(number);
					int count = optionStat.getCount() + 1;
					optionStat.setCount(count);
					jdbcRepository.update(optionStat);
				
				} else {
					optionStat = new Statistics();
					optionStat.setCount(1);
					optionStat.setNumb(number);
					jdbcRepository.save(optionStat);
				}

			}

		};
		thread.start();

		return result;
	}

}
