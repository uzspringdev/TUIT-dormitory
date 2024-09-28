package com.pro.tuitdormitory.security;

import com.pro.tuitdormitory.dto.request.LoginRequest;
import com.pro.tuitdormitory.service.LmsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

/*@Service*/
@RequiredArgsConstructor
public class TokenService {
    @Value("${json.web.token.secret}")
    private String secret;

    @Value("${json.web.token.validation-time}")
    private Long validationTime;

    @Value("${json.web.token.refresh-time}")
    private Long refreshTime;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDetailsService userDetailsService;
    private final LmsService lmsService;


    public String generateToken(LoginRequest loginRequest) {
        lmsService.authenticateLmsUser(loginRequest);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String roles = authenticate.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Claims claims = Jwts.claims();
        claims.setSubject(loginRequest.getUsername());
        claims.put("roles", roles);
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + this.validationTime);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .compact();

    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + this.refreshTime);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .compact();
    }

    public Boolean shouldRefresh(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Instant now = Instant.now();
            Instant expirationDate = claims.getExpiration().toInstant();
            Instant refreshTime = expirationDate.minusMillis(this.refreshTime);  //20.04.2023 22:00 4 hours 20.04.2023 18:00
            return now.isAfter(refreshTime);                                    //20.04.2023 16:38
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Date now = new Date();
            Date expiratioDate = claims.getExpiration();
            return !expiratioDate.before(now);  //20.10.2023 19.10.2023
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        }
        return false;

    }

    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        if (isValid(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }
        return null;
    }



}
