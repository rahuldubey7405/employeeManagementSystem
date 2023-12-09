package com.springBootProject.security.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springBootProject.model.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component

public class JwtTokenHelper {
//	private static Category log = FleetLogger.getLogger();
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private final String secret = "jwtTokenKeyjwtTokenKeyjwtTokenKeyjwtTokenKeyjwtTokenKey";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		boolean isExpired = expiration.before(new Date());
		return isExpired;
	}

	public String generateToken(AuthUser user) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, user.getEmail());
	}

	public String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 240))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		System.out.println("VelidateToken: " + isTokenExpired(token));
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

//	public RefreshToken createRefreshToken(int userId) {
//		RefreshToken refreshToken = new RefreshToken();
//
//// refreshToken.setUser(userRepository.findById(userId).get());
//		refreshToken.setRefrshTokenExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 720));
//		refreshToken.setRefreshToken(UUID.randomUUID().toString());
//		log.debug("RefreshToken__ " + refreshToken.getRefreshToken());
//		OTPAndTokenUtils.AddRefreshToken(refreshToken, userId);
//
//		return refreshToken;
//	}

//	public static boolean verifyRefreshTokenExpiration(RefreshToken token, String exDate) {
//		log.debug("exDate: " + exDate); // 2023-10-31 11:20:31,
//		boolean isExpired = false;
//		try {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			LocalDateTime inputDateTime = LocalDateTime.parse(exDate, formatter);
//			LocalDateTime currentDateTime = LocalDateTime.now();
//			long datsDifference = ChronoUnit.DAYS.between(inputDateTime, currentDateTime);
//			log.debug(datsDifference);
//// Date tokanTime = new Date(System.currentTimeMillis() + 1000 * 60 * 2);
//			if (datsDifference >= 30) {
//				OTPAndTokenUtils.DeleteRefreshToken(token);
//				log.debug("Refresh token was expired. Please make a new signin request");
//				return true;
//			}
//
//		} catch (Exception e) {
//			log.error("Error in verifyRefreshTokenExpiration :" + e);
//		}
//		return false;
//	}

}