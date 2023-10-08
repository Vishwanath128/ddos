package com.hcl.intern.projects.ddos.ratelimiter;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
class RateLimiterController {

	@Autowired
	RequestCounter counter;

	@Autowired
	private KieContainer kieContainer;

	@PostMapping(value = "/rateLimit", produces = "application/json")
	public Response rateLimit(@RequestBody Request request) {
		System.out.println("inside rate limiter"+request.getIpAddress());
		String key = getKey(request.getIpAddress());
		int numberOfRequestsFromThisIPForCurrentMinute =
			getNumberOfRequestsFromThisIPForCurrentMinute(key);
		boolean exceedsLimit =  doesThisExceedsAllowedLimit(
			numberOfRequestsFromThisIPForCurrentMinute,
			request.getIpAddress(),
			request.getGeoLocation());
		Response response=new Response();
		if(exceedsLimit) {
			response.setAction("DENY");
		} else {
			response.setAction("ALLOW");
			incrementCounter(key);
		}
		return response;
	}
	public int getNumberOfRequestsFromThisIPForCurrentMinute(String key){
		if(counter.getCount(key) != null) {
			return counter.getCount(key);
		} else {
			return 0;
		}
	}

	public boolean doesThisExceedsAllowedLimit(int count,String ipAddress,String geoLocation) {
		//call rule engine to decide
		/*if(count >= 2) {
			return true;
		}
		return false;
		*/
		RuleResponse response = new RuleResponse();
		RuleRequest request=new RuleRequest();
		request.setCount(count);
		request.setIpAddress(ipAddress);
		request.setGeoLocation(geoLocation);
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.setGlobal("ruleResponse", response);
		kieSession.insert(request);
		kieSession.fireAllRules();
		kieSession.dispose();
		return response.isResponse();
	}

	public void incrementCounter(String key) {
		counter.increment(key);
	}

	public String getKey(String ipAddress) {
		return ipAddress + ":" +
			Calendar.getInstance().get(Calendar.HOUR) + ":" +
			Calendar.getInstance().get(Calendar.MINUTE);
	}
}
