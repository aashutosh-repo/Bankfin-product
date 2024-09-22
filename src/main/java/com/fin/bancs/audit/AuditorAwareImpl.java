package com.fin.bancs.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Return the username (principal name)
        if (authentication != null) {
            return authentication.getName().describeConstable();  // This is the username of the authenticated user
        }
        return "TestUser".describeConstable();  // If no user is authenticated
        // Implement your logic to retrieve the current user
        // This could be retrieved from the security context or a session
    }
}
