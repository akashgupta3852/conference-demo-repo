package com.pluralsight.conferencedemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my")
public class MyConfig {
	
	private String featureEnabled;

	public String getFeatureEnabled() {
		return featureEnabled;
	}

	public void setFeatureEnabled(String featureEnabled) {
		this.featureEnabled = featureEnabled;
	}
}
