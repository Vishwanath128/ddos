package org.springframework.samples.petclinic.ratelimiter;

public class RuleRequest {
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	int count;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	String ipAddress;


}
