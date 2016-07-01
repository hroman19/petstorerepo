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
import javax.servlet.http.HttpServletResponseWrapper;

public class JsessionIdAvoiderFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (!(req instanceof HttpServletRequest)) {
			chain.doFilter(req, res);
			return;
		}

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// Prevent rendering of JSESSIONID in URLs for all outgoing links
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(response) {
			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeRedirectURL(String url) {
				return url;
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}
		};
		chain.doFilter(req, wrappedResponse);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
