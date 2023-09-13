package org.springframework.samples.petclinic.ratelimiter;

public class RuleResponse {


	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	boolean response;
}
