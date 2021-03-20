package ru.cs.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionParam {
	Long firmId;

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}
	
}
