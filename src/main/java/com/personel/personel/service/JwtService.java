package com.personel.personel.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
// openssl rand -base64 32
	private static final String SECRET_KEY = "LyKMo78UAvMyjonEU9/kEPc5sO1xe8JZWoIy9ck1UTQ=";

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, username);

	}

	public String createToken(Map<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(new Date(System.currentTimeMillis() + 1000 * 60 * 30).toString())
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims=extractAllClaim(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaim(String token) {		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Key getSignKey() {
		byte[] ketBytes=Decoders.BASE64.decode(SECRET_KEY);		
		return Keys.hmacShaKeyFor(ketBytes);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}
	
	
	

}
