package com.playground;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
	
	@Value("${server.contextPath}")
	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	@Override
	public String toString() {
		return "AppConfiguration [contextPath=" + contextPath + "]";
	}
}
