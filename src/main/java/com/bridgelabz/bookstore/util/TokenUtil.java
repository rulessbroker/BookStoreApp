package com.bridgelabz.bookstore.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {
	// Everytime you add data,need to create token and will be used multiple times .
	private static final String TOKEN_SECRET = "Secure";

	// Token Encode part
	public String createToken(long id) {
		try {
			// to set algorithm
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			String token = JWT.create().withClaim("id", id).sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
			// log Token Signing Failed
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Token Decode part
	public int decodeToken(String token) {
		int userid;
		Verification verification = null; // for verification algorithm
		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		JWTVerifier jwtverifier = verification.build();
		// to decode token
		DecodedJWT decodedjwt = jwtverifier.verify(token);
		Claim claim = decodedjwt.getClaim("id");
		userid = claim.asInt();
		return userid;
	}
}
