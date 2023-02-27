package es.fernando.spring.app.auth.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import es.fernando.spring.app.auth.service.JWTService;
import es.fernando.spring.app.constants.Constants;

public class JWTAuthoritationFilter extends BasicAuthenticationFilter {
	private JWTService jwtService;

	public JWTAuthoritationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(Constants.AUTHORITATION);
		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = null;

		if (jwtService.validate(header)) {
			String username = jwtService.getUsername(header);
			Object roles = jwtService.getRoles(header);

			authentication = new UsernamePasswordAuthenticationToken(username, null,
					(Collection<? extends GrantedAuthority>) roles);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	protected boolean requiresAuthentication(String header) {
		if (header == null || !header.startsWith(Constants.BEARER)) {
			return false;
		}
		return true;
	}

}
