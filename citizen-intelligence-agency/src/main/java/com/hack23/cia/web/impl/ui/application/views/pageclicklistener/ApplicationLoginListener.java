package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;

import com.ejt.vaadin.loginform.LoginForm.LoginEvent;
import com.ejt.vaadin.loginform.LoginForm.LoginListener;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public final class ApplicationLoginListener implements LoginListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLoginListener.class);

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
		if (response.getResult().equals(ServiceResult.SUCCESS)) {
			LOGGER.info("LoginRequest {}",event.getUserName() );

			UI.getCurrent().getNavigator().navigateTo(UserViews.USERHOME_VIEW_NAME);
		} else {
			Notification.show("Register failed",
	                  "Error message",
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info("RegisterUser {} failure",event.getUserName() );
		}

	}
}