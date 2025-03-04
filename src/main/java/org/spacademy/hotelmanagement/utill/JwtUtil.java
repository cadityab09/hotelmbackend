package org.spacademy.hotelmanagement.utill;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtil {
	
	private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
		return Jwts.builder().setClaims(extraClaims).setSubject(details.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
				.signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
	
	private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	private Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Key getSigningKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode("YI669G85E57R57R756E66DF9TCIGRR4E46556RFT79955VRDR43"); 
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
