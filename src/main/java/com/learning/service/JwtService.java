package com.learning.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "ThisIsASuperLongSecretKeyForJWT1234567890";
	
	public String generateToken(String username) {
		return Jwts.builder().subject(username)
						.issuedAt(new Date())
						.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
						.claims(new HashMap<>())
						.signWith(getSignKey()).compact();
	}
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	} 
	
	public Claims verifySignatureAndExtractAllClaims(String token) {
		return  Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public String extractUsername(String token) {
		return verifySignatureAndExtractAllClaims(token).getSubject();
	}
	
	public Date extractExpirationTime(String token) {
		return verifySignatureAndExtractAllClaims(token).getExpiration();
	}
	
	public boolean isTokenExpired (String token) {
		return extractExpirationTime(token).before(new Date());
	}


}
