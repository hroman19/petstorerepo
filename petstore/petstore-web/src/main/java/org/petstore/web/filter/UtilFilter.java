package org.petstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.petstore.common.model.User;

public class UtilFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			reqt.setCharacterEncoding("UTF-8");
			String reqURI = reqt.getRequestURI().substring(reqt.getContextPath().length());

			if (reqURI.endsWith(".xhtml")) {
				((HttpServletResponse) response).sendRedirect("/index");
			}
			if (reqURI.indexOf("/index") >= 0 || reqURI.contains("javax.faces.resource") 
					|| reqURI.endsWith(".css") || reqURI.endsWith(".js") 
					|| reqURI.endsWith(".jpg") || reqURI.endsWith(".jpeg")
					|| reqURI.endsWith(".bmp")) {
				chain.doFilter(request, response);
				return;
			}
			if (ses != null && ses.getAttribute("user") != null) {
				User user = (User) ses.getAttribute("user");
				if ((reqURI.contains("/admin") && user.getIsAdmin())
						|| (reqURI.contains("/bucket") && !user.getIsAdmin())) {
					chain.doFilter(request, response);
					return;
				}
			}
			resp.sendRedirect(reqt.getContextPath() + "/index");
		} catch (Exception e) {
			// unnecessary
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
