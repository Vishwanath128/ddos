package org.springframework.samples.petclinic.ratelimiter;

public class Request {
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private String ipAddress;

}
