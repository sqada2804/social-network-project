package com.example.social_network_project.config;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IJWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final IJWTService jwtService;
    private final IUserRepository userRepository;

    public JwtAuthFilter(IJWTService jwtService, IUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                logger.info("JWT Token received: " + jwtToken);

                String userId = String.valueOf(jwtService.extractedUserId(jwtToken));
                logger.info("User ID extracted from token: " + userId);

                if (userId != null) {
                    Optional<UserModel> userDetails = userRepository.findById(Long.valueOf(userId));
                    if (userDetails.isPresent()) {
                        logger.info("User details found: " + userDetails.get().getUsername());
                        request.setAttribute("X-User-Id", userDetails.get().getUserId());
                        processAuthentication(request, userDetails.get());
                    } else {
                        logger.warn("User not found in database for ID: " + userId);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error processing JWT token", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void processAuthentication(HttpServletRequest request, UserModel userDetails) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        Optional.of(jwtToken)
                .filter(token -> !jwtService.isExpired(token))
                .ifPresent(token -> {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
    }
}
