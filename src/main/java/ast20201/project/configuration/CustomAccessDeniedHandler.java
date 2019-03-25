/*
 * Reference
 * https://www.kancloud.cn/digest/springsecurity/169115
 */
package ast20201.project.configuration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (request.getContentType() != null && request.getContentType().contains("application/json")) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getOutputStream().println("{ \"error\": \"" + accessDeniedException.getMessage() + "\" }");
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/403.jsp");
			dispatcher.forward(request, response);
		}
	}
}
