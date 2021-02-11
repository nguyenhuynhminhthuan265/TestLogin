package com.test.tbcm.security;

import com.test.tbcm.utils.LoggerHelperUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        System.out.println("===================>>>>>>>>>>>>>>>>>>>> doFilterInternal");
        try {
            String header = request.getHeader(jwtTokenUtil.getHeaderString());
            if (header == null || !header.startsWith(jwtTokenUtil.getTokenPrefix())) {
                chain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LoggerHelperUtils.logger(getClass().getSimpleName(), "authentication: " + authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            LoggerHelperUtils.logger(getClass().getSimpleName(), "doFilterInternal: " + e.getMessage());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            System.out.println("===================>>>>>>>>>>>>>>>>>>>> getAuthentication");
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtTokenUtil.getSecret())
                    .parseClaimsJws(token.replace(jwtTokenUtil.getTokenPrefix(), ""));
            String email = claims.getBody().getSubject();
            LoggerHelperUtils.logger(getClass().getSimpleName(), "email: " + email);
            UserDetails appUser = userDetailsServiceImpl.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(email, null, appUser.getAuthorities());
        } catch (Exception e) {
            LoggerHelperUtils.logger(getClass().getSimpleName(), "getAuthentication: " + e.getMessage());
            return null;
        }
    }
}