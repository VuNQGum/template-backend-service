package com.evnit.ttpm.stdv.util;

import com.evnit.ttpm.stdv.security.RSAKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class JwtTokenProvider {
	/**
	 * Generates a token from a principal object. Embed the refresh token in the jwt
	 * so that a new jwt can be created
	 */
	public static String generateToken(String userName, String email, Integer idDonvi, Long nsId) {
		String privateKey = "";

		try (InputStream input = JwtTokenProvider.class.getClassLoader().getResourceAsStream("security.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);
//
//            // get the property value and print it out
//            System.out.println(prop.getProperty("app.jwt.publickey"));
//            System.out.println(prop.getProperty("app.jwt.privatekey"));

			privateKey = prop.getProperty("app.jwt.privatekey");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Instant expiryDate = Instant.now().plusMillis(60 * 60 * 1000);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");

		Claims claims = Jwts.claims().setSubject(userName);

		claims.put("email", email != null ? email : "NULL");
		claims.put("provide", "EVNID");

		claims.put("IDDONVI", idDonvi);
		claims.put("NSID", nsId);

		return Jwts.builder().setClaims(claims).setHeaderParams(headers).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(expiryDate))
				.signWith(SignatureAlgorithm.RS256, RSAKey.getPrivateKey(privateKey)).compact();
	}
}
