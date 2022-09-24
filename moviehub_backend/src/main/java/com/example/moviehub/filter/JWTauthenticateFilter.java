package com.example.moviehub.filter;

import com.example.moviehub.service.Impl.UserServiceImpl;
import com.example.moviehub.util.JWTtokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTauthenticateFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);
        String jwtToken = null;
        String userEmail = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            System.out.println(jwtToken);
            try{
                userEmail = JWTtokenUtil.getUserEmail(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            logger.warn("JWT Token not exist or does not begin with Bearer String");
        }

        if (userEmail != null){
            System.out.println(userEmail);
            if (SecurityContextHolder.getContext().getAuthentication() == null){

                //load userDetail with the email
                UserDetails userDetails = this.userService.loadUserByUsername(userEmail);
                System.out.println(userDetails.getUsername() + "\n" + userDetails.getPassword());

                System.out.println(JWTtokenUtil.validateToken(jwtToken));
                if (JWTtokenUtil.validateToken(jwtToken)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
