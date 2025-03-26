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

import com.evnit.ttpm.stdv.exception.ExpiredTokenRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.evnit.ttpm.stdv.exception.InvalidTokenRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenValidator {

	private static final Logger logger = Logger.getLogger(JwtTokenValidator.class);
	private final String publicKey;
	private final String tokenRequestHeader;


	@Autowired
	public JwtTokenValidator(@Value("${app.jwt.publickey}") String publicKey,
			@Value("${app.jwt.header.prefix}") String tokenRequestHeader) {
		this.publicKey = publicKey;
		this.tokenRequestHeader = tokenRequestHeader;
	}

	/**
	 * Validates if a token satisfies the following properties - Signature is not
	 * malformed - Token hasn't expired - Token is supported - Token has not
	 * recently been logged out.
	 * 
	 * @throws Exception
	 */
	public String validateToken(String authToken, String org_code) {
		String userId = null;
		String strKey = publicKey;
		try {

			// Jwts.parser().setSigningKey(RSAKey.getPublicKey(publicKey)).parseClaimsJws(authToken);
			Claims claims = Jwts.parser().setSigningKey(RSAKey.getPublicKey(strKey)).parseClaimsJws(authToken)
					.getBody();
			userId = claims.getSubject();
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
			throw new InvalidTokenRequestException("JWT", authToken, "Incorrect signature");

		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
			throw new InvalidTokenRequestException("JWT", authToken, "Malformed jwt token");

		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
			throw new ExpiredTokenRequestException("JWT", authToken, "Token expired. Refresh required");

		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
			throw new InvalidTokenRequestException("JWT", authToken, "Unsupported JWT token");

		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
			throw new InvalidTokenRequestException("JWT", authToken, "Illegal argument token");
		}
		return userId;
	}


}
