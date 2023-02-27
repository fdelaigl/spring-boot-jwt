package es.fernando.spring.app.constants;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Constants {
	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public static final Long EXPIRATION =  (long) (360000000 * 4);

	public static final String BEARER = "Bearer ";
	
	public static final String AUTHORITATION = "Authorization";
}
