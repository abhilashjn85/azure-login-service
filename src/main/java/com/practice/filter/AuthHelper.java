package com.practice.filter;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import com.practice.constant.Constant;

import javax.servlet.http.HttpServletRequest;

public final class AuthHelper {

	public static final String PRINCIPAL_SESSION_NAME = "principal";

	private AuthHelper() {
	}

	public static boolean isAuthenticated(HttpServletRequest request) {
		return request.getSession().getAttribute(PRINCIPAL_SESSION_NAME) != null;
	}

	public static AuthenticationResult getAuthSessionObject(HttpServletRequest request) {
		return (AuthenticationResult) request.getSession().getAttribute(PRINCIPAL_SESSION_NAME);
	}

	public static boolean containsAuthenticationData(HttpServletRequest httpRequest) {
		return httpRequest.getMethod().equalsIgnoreCase("POST")
				&& (httpRequest.getParameterMap().containsKey(Constant.ERROR)
						|| httpRequest.getParameterMap().containsKey(Constant.ID_TOKEN)
						|| httpRequest.getParameterMap().containsKey(Constant.CODE));
	}

	public static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
		return authResponse instanceof AuthenticationSuccessResponse;
	}
}
