//package com.example.springSecImp.jwt;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDate;
//import java.util.Date;
//
//public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    // part - 1
//    // this is the part where client sends credential
//    // and the server validates the credential
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
//
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getUsername(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//
//            Authentication authenticate = authenticationManager.authenticate(authentication);
//            return authenticate;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    // we need to over right this method, this menthod wil be invoked after the attempt
//    // auth is successful
//    // hear we are going to create a token
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecure";
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities())
//                .setIssuedAt(new Date())
//                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
//                .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)))
//                .compact();
//
//        response.addHeader("Authorization", "Bearer " + token);
//    }
//}
