package com.hcl.intern.projects.ddos.ratelimiter;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RequestCounter {
	HashMap<String,Integer> counter = new HashMap<>();

	public Integer getCount(String key) {
		return counter.get(key);
	}

	public void increment(String key) {
		int count = counter.getOrDefault(key,0);
		count=count+1;
		counter.put(key, count);
	}
}
