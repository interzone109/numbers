package com.numbers.Numbers.metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

/**
 * сomponent that collects metrics from the servlet
 * 
 */

@Component("myServlet") 
public class MetricComponent {
	private Map<Integer, Integer> successAvarageSercvise;
	private AtomicInteger latencyCount;
	private AtomicLong latencyTime;

	public MetricComponent() {
		
		latencyCount = new AtomicInteger();
		latencyTime = new AtomicLong();
		successAvarageSercvise = new ConcurrentHashMap<>();
		System.out.println("successAvarageSercvise !!!!!!!!!!!!!!!"  + successAvarageSercvise);
	}

	public long getAvarageControllerTime() {
		
		return (latencyTime.get() !=0 )?latencyTime.get() / latencyCount.get() : 0;
	}

	public int getSuccessServise() {
		int successCodeCount[] = new int[] {0};
		int otherCode[] = new int[] {0};
		successAvarageSercvise.keySet().forEach(code -> {
			if (code == 200)
				successCodeCount[0] += successAvarageSercvise.get(code);
			else
				otherCode[0] += successAvarageSercvise.get(code);
		});

		return ( successCodeCount[0]!=0)? successCodeCount[0] / (successCodeCount[0] + otherCode[0]) * 100:0;

	}

	public Map<Integer, Integer> getSuccessAvarageSercvise() {
		return successAvarageSercvise;
	}

	public AtomicInteger getLatencyCount() {
		return latencyCount;
	}

	public AtomicLong getLatencyTime() {
		return latencyTime;
	}

}
