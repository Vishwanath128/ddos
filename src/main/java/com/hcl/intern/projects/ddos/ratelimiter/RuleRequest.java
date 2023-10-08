package com.hcl.intern.projects.ddos.ratelimiter;

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
	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	String ipAddress;
    String geoLocation;

}
