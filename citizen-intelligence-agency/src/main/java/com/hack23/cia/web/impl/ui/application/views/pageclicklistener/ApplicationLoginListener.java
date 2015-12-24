package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.springframework.web.context.request.RequestContextHolder;

import com.ejt.vaadin.loginform.LoginForm.LoginEvent;
import com.ejt.vaadin.loginform.LoginForm.LoginListener;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;

public final class ApplicationLoginListener implements LoginListener {

	private final ApplicationManager applicationManager;

	/**
	 * @param testChartView
	 */
	public ApplicationLoginListener(ApplicationManager applicationManager) {
		this.applicationManager = applicationManager;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onLogin(LoginEvent event) {

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(event.getUserName());
		loginRequest.setUserpassword(event.getPassword());
		loginRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		LoginResponse response = (LoginResponse) applicationManager.service(loginRequest);

	}
}