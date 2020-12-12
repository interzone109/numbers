package com.numbers.Numbers.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.numbers.Numbers.entity.Statistics;
import com.numbers.Numbers.metric.MetricComponent;
import com.numbers.Numbers.repository.StatisticsRepository;

/**
 * controller displaying metrics
 */

@Controller
public class MetricController {

	@Autowired
	private StatisticsRepository jdbcRepository;
	@Autowired
	private MetricComponent metricComponent;

	@RequestMapping(path = { "/metric" }, method = RequestMethod.GET)
	public @ResponseBody String metric() {

		List<Statistics> topTen = jdbcRepository.findTopTen();

		StringBuilder str = new StringBuilder();
		str.append(Arrays.toString(topTen.toArray()));

		str.append("; average latency of the Numbers service is " + metricComponent.getAvarageControllerTime() +" ms");

		str.append("; average success rate of Number sercvise  -" + metricComponent.getSuccessServise() + " %");

		return str.toString();

	}
}
