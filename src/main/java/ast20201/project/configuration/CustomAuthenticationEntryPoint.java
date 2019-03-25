/*
 * References
 * https://stackoverflow.com/questions/19767267/handle-spring-security-authentication-exceptions-with-exceptionhandler
 */
package ast20201.project.configuration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {
		if (request.getContentType() != null && request.getContentType().contains("application/json")) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getOutputStream().println("{ \"error\" : \"" + authenticationException.getMessage() + "\" }");
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/401.jsp");
			dispatcher.forward(request, response);
		}
	}
}