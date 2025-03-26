/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evnit.ttpm.stdv.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.evnit.ttpm.stdv.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// private static final Logger log =
	// Logger.getLogger(JwtAuthenticationFilter.class);

	@Value("${app.jwt.header}")
	private String tokenRequestHeader;

	@Value("${app.jwt.client}")
	private String client_device;

	@Value("${app.jwt.header.prefix}")
	private String tokenRequestHeaderPrefix;

	@Autowired
	private JwtTokenValidator jwtTokenValidator;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/**
	 * Filter the incoming request for a valid token in the request header
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			// HttpServletRequest httpServletRequest = (HttpServletRequest)
			// request;
			// HttpServletResponse httpServletResponse = (HttpServletResponse)
			// response;
			// if errors exist then create a sanitized cookie header and
			// continue
			String org_code = request.getHeader("Org-code");
			// Cookie[] cookies = request.getCookies();
			// System.out.println("Extracted cookies: " + cookies);
			String jwt = getJwtFromRequest(request);
			String userId = jwtTokenValidator.validateToken(jwt, org_code != null ? org_code : "EVN");
			if (StringUtils.hasText(jwt) && userId != null) {
				// String userId = jwtTokenProvider.getUserIdFromJWT(jwt);

				UserDetails userDetails = customUserDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, jwt, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (ExpiredJwtException ex) {

			String isRefreshToken = request.getHeader("isRefreshToken");
			String requestURL = request.getRequestURL().toString();
			// allow for Refresh Token creation if following conditions are
			// true.
			if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("__Secure-3PSID_REF")) {
				allowForRefreshToken(ex, request);
			} else
				request.setAttribute("exception", ex);

		} catch (BadCredentialsException ex) {
			request.setAttribute("exception", ex);
		} catch (Exception ex) {
			request.setAttribute("exception", ex);
		}

		filterChain.doFilter(request, response);
	}

	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}

	/**
	 * Extract the token from the Authorization request header
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(tokenRequestHeader);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)) {
			return bearerToken.replace(tokenRequestHeaderPrefix, "");
		}
		return null;
	}

	/**
	 * Extract the token from the Authorization request header
	 */
	private String getJwtFromRequest1(HttpServletRequest request) {
		String client = request.getHeader("Authorization-type");
		if (client != null && client.equals("Header")) {
			String bearerToken = request.getHeader(tokenRequestHeader);
			// Cookie[] cookies = request.getCookies();
			// System.out.println("Extracted cookies: " + cookies);
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)) {
				// log.info("Extracted Token: " + bearerToken);
				return bearerToken.replace(tokenRequestHeaderPrefix, "");
			}
		} else {
			String bearerToken = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; ++i) {
					Cookie a = cookies[i];
					if (a.getName().equalsIgnoreCase("__Secure-PSID")) {
						bearerToken = a.getValue();
					}
				}
				return bearerToken;
			} else
				return null;
		}
		return null;
	}
}
