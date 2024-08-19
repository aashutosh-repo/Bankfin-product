package com.fin.bancs.audit;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Implement your logic to retrieve the current user
        // This could be retrieved from the security context or a session
        return Optional.of("TestUser"); // Replace with actual logic
    }
}
